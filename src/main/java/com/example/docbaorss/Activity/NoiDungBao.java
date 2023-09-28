package com.example.docbaorss.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.example.docbaorss.Class.OfflineRSSItem;
import com.example.docbaorss.Database.DBOfflineRSSItem;
import com.example.docbaorss.R;


import java.util.Locale;


public class NoiDungBao extends AppCompatActivity {

    WebView noiDungBaiBao;
    ProgressBar progressBar;
    Toolbar nToolbar;
    private OfflineRSSItem offlineRSSItem;
    AppCompatImageView imgSave , imgOnMic , imgOffMic , imgNightLight;
    DBOfflineRSSItem itemRssController;
    private TextToSpeech textToSpeech;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noidungbao_activity);

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int result=textToSpeech.setLanguage(Locale.getDefault());

                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Ngôn ngữ không được hỗ trợ");
                    }
                }else{
                    Log.e("TTS","Language");
                }
            }
        });
        anhXa();
        Actions();
        itemRssController = new DBOfflineRSSItem(NoiDungBao.this);
        offlineRSSItem = (OfflineRSSItem) getIntent().getSerializableExtra("OfflineRSSItem");

    }


    private void Actions() {
        Intent intent = getIntent();
        String noidunglayduoc = intent.getStringExtra("ndBaiBao");
        final String url = intent.getStringExtra("URL");
        noiDungBaiBao.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;}</style></head><body>" + noidunglayduoc + "</body></html>", "text/html", "UTF-8");

        noiDungBaiBao.setVisibility(View.VISIBLE);
        noiDungBaiBao.getSettings().setJavaScriptEnabled(true);
        noiDungBaiBao.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                noiDungBaiBao.setVisibility(View.VISIBLE);
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NoiDungBao.this);
                builder.setTitle("Bạn muốn lưu tin?");
                builder.setMessage("Bài báo sẽ được lưu lại để đọc khi không có mạng");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long ok = itemRssController.Insert(offlineRSSItem);
                        if (ok > 0) {
                            Toast.makeText(NoiDungBao.this, "Lưu thành công! " , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create().show();
            }
        });

        imgOnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noiDungBaiBao.evaluateJavascript("(function() { return document.body.innerText; })();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                // Đọc nội dung thành tiếng.
                                textToSpeech.speak(value, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
            }
        });

        imgOffMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textToSpeech != null && textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }
            }
        });



        final boolean[] darkModeEnabled = {false};
        imgNightLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              toggleDarkMode();
            }
        });
    }
    private boolean isDarkMode = false;
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            noiDungBaiBao.loadUrl("javascript:(function() { document.body.style.backgroundColor = '#333333'; document.body.style.color = '#ffffff'; })()");
        } else {
            noiDungBaiBao.loadUrl("javascript:(function() { document.body.style.backgroundColor = '#ffffff'; document.body.style.color = '#000000'; })()");
        }
    }

    private void anhXa() {
        noiDungBaiBao = findViewById(R.id.webviewbaibao);
        progressBar = findViewById(R.id.progressbar);
        nToolbar = findViewById(R.id.toolbar_noidungbao);
        imgSave = findViewById(R.id.imgSave);
        imgOnMic = findViewById(R.id.imgDoc);
        imgOffMic = findViewById(R.id.imgOff);
        imgNightLight = findViewById(R.id.imgnightlight);



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
