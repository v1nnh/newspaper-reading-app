package com.example.docbaorss.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.docbaorss.Class.OfflineRSSItem;
import com.example.docbaorss.Adapter.AdapterListBaiBaoOffline;
import com.example.docbaorss.Database.DBOfflineRSSItem;
import com.example.docbaorss.Database.DBPosts;
import com.example.docbaorss.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class DocBaoOffline extends AppCompatActivity {

    ListView lvDanhSachBaiBaoOffline;
    DBOfflineRSSItem dbOfflineRSSItem;
    Toolbar nToolbar;
    DrawerLayout drawer;
    List<OfflineRSSItem> offlineRSSItems = new ArrayList<>();
    AdapterListBaiBaoOffline adapterListBaiBaoOffline;
    NavigationView navigationView;


    DBPosts dbPosts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docbaooffline);

        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        setSupportActionBar(nToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nToolbar.setNavigationIcon(R.drawable.ic_menu111_foreground);
        nToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
        );

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(DocBaoOffline.this, TrangChu.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                break;

                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
        lvDanhSachBaiBaoOffline = findViewById(R.id.lvDSBaiBaoOffLine);
        registerForContextMenu(lvDanhSachBaiBaoOffline);

        dbOfflineRSSItem = new DBOfflineRSSItem(DocBaoOffline.this);
        dbPosts = new DBPosts(DocBaoOffline.this);

        offlineRSSItems = dbOfflineRSSItem.getAlLOffLineItemRss();
        adapterListBaiBaoOffline = new AdapterListBaiBaoOffline(DocBaoOffline.this,R.layout.item_listview_dsbaibao,offlineRSSItems);
        lvDanhSachBaiBaoOffline.setAdapter(adapterListBaiBaoOffline);

        lvDanhSachBaiBaoOffline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DocBaoOffline.this,NoiDungBao.class);
                intent.putExtra("ndBaiBao",offlineRSSItems.get(i).getContent());
                startActivity(intent);
            }
        });

        lvDanhSachBaiBaoOffline.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //an va giư
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int positon = i;

                final AlertDialog.Builder builder = new AlertDialog.Builder(DocBaoOffline.this);
                builder.setTitle("Bạn có muốn xóa 1 bài báo đã lưu?");
                builder.setMessage("Thao tác này sẽ xóa bài báo vĩnh viễn và không thể hủy được sau khi thực hiện");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbOfflineRSSItem.delete(offlineRSSItems.get(positon));
                        offlineRSSItems.clear();
                        offlineRSSItems.addAll(dbOfflineRSSItem.getAlLOffLineItemRss());
                        adapterListBaiBaoOffline.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

    }
}
