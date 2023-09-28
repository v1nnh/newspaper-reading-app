package com.example.docbaorss.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.docbaorss.Class.OfflineRSSItem;
import com.example.docbaorss.Class.Post;
import com.example.docbaorss.Database.DBOfflineRSSItem;
import com.example.docbaorss.Database.DBPosts;
import com.example.docbaorss.R;


public class Chitietbaibao extends AppCompatActivity {

    WebView wvContentDetails;
    AppCompatImageView imgSave;

    DBPosts dbPosts;
    OfflineRSSItem offlineRSSItem;
    DBOfflineRSSItem dbOfflineRSSItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietbaibao);

        wvContentDetails = findViewById(R.id.wv_content_details);
        imgSave = findViewById(R.id.imgSave);

        final Intent intent = getIntent();
        String nd = intent.getStringExtra("noidungg");

        wvContentDetails.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;}</style></head><body>" + nd + "</body></html>", "text/html", "UTF-8");

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Chitietbaibao.this);
                builder.setTitle("Bạn muốn lưu tin?");
                builder.setMessage("Tin sẽ được lưu lại để đọc khi không có mạng");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbPosts = new DBPosts(Chitietbaibao.this);
                        dbOfflineRSSItem = new DBOfflineRSSItem(Chitietbaibao.this);
                        Post post = (Post) intent.getSerializableExtra("post");


                        String title = post.getPost_title();
                        String description = post.getPost_desc();
                        String content = post.getPost_content();
                        String urlImg = post.getPost_thumb();

                        offlineRSSItem = new OfflineRSSItem(title,description,content,urlImg);


                        long okk = dbOfflineRSSItem.Insert(offlineRSSItem);
                        if (okk > 0) {
                            Toast.makeText(Chitietbaibao.this, "Đã lưu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.create().show();
            }
        });

    }
}
