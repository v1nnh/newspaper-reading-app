package com.example.docbaorss.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.docbaorss.Class.Bao;
import com.example.docbaorss.Class.DanhMucBao;
import com.example.docbaorss.Class.DanhSachBao;
import com.example.docbaorss.Adapter.CustomDanhMucBao;
import com.example.docbaorss.R;
import com.example.docbaorss.URL.url_24h;
import com.example.docbaorss.URL.url_vnexpress;
import com.example.docbaorss.URL.url_thanhthien;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {

    Toolbar nToolbar;
    DrawerLayout drawer;
    GridView grvDanhSachBao;

    NavigationView navigationView;


    SharedPreferences sharedPreferences;

    private boolean isGrey = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);


//        View childView = getLayoutInflater().inflate(R.layout.item_listview_dsbao, null);

//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout par = findViewById(R.id.lvbaibao);
//        par.addView(childView);

//        ViewGroup rootview = findViewById(R.id.drawer);
//        @SuppressLint("ResourceType") View merg = getLayoutInflater().inflate(R.id.lvbaibao,rootview, true);
//        tv_tenbao = findViewById(R.id.textview_tendanhmuc);



        anhXa();
        ActionDanhMucBao();
        actionToolbar();
    }

    private void ActionDanhMucBao() {
        DanhSachBao arrayDanhSachBaoBao = new DanhSachBao();
        ArrayList<Bao> baos = arrayDanhSachBaoBao.LayDanhSachBao();
        CustomDanhMucBao customDanhMucBao = new CustomDanhMucBao(this,R.layout.item_listview_dsbao,baos);
        grvDanhSachBao.setAdapter(customDanhMucBao);
        grvDanhSachBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    Intent intent = new Intent(TrangChu.this, Danhsachbaibao.class);
                    ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
                    arrDanhMucBao.add(new DanhMucBao("Cộng Đồng", url_vnexpress.urlCongDong));
                    arrDanhMucBao.add(new DanhMucBao("Giải Trí", url_vnexpress.urlGiaiTri));
                    arrDanhMucBao.add(new DanhMucBao("Thời Sự", url_vnexpress.urlThoiSu));
                    arrDanhMucBao.add(new DanhMucBao("Giáo Dục", url_vnexpress.urlGiaoDuc));
                    arrDanhMucBao.add(new DanhMucBao("Du Lịch", url_vnexpress.urlDuLich));
                    arrDanhMucBao.add(new DanhMucBao("Khoa Học", url_vnexpress.urlKhoaHoc));
                    arrDanhMucBao.add(new DanhMucBao("Gia Đình", url_vnexpress.urlGiaDinh));
                    arrDanhMucBao.add(new DanhMucBao("Kinh Doanh", url_vnexpress.urlKinhDoanh));
                    arrDanhMucBao.add(new DanhMucBao("Pháp Luật", url_vnexpress.urlPhapLuat));
                    arrDanhMucBao.add(new DanhMucBao("Số Hóa", url_vnexpress.urlSoHoa));
                    arrDanhMucBao.add(new DanhMucBao("Startup", url_vnexpress.urlStartUp));
                    arrDanhMucBao.add(new DanhMucBao("Sức Khỏe", url_vnexpress.urlSucKhoe));
                    arrDanhMucBao.add(new DanhMucBao("Tâm Sự", url_vnexpress.urlTamSu));
                    arrDanhMucBao.add(new DanhMucBao("Thế Giới", url_vnexpress.urlTheGioi));
                    arrDanhMucBao.add(new DanhMucBao("Thể Thao", url_vnexpress.urlTheThao));
                    arrDanhMucBao.add(new DanhMucBao("Xe", url_vnexpress.urlXe));
                    arrDanhMucBao.add(new DanhMucBao("Cười", url_vnexpress.urlCuoi));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DANHMUCBAOBUNDLE", arrDanhMucBao);
                    intent.putExtra("LISTDANHMUCBAO", bundle);
                    startActivity(intent);
                }
                else if(i == 1){
                    Intent intent = new Intent(TrangChu.this, Danhsachbaibao.class);
                    ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
                    arrDanhMucBao.add(new DanhMucBao("Tin tức trong ngày", url_24h.urlTrongNgay));
                    arrDanhMucBao.add(new DanhMucBao("Bóng đá", url_24h.urlBongda));
                    arrDanhMucBao.add(new DanhMucBao("An ninh - Hình sự", url_24h.urlAnninh));
                    arrDanhMucBao.add(new DanhMucBao("Thời trang", url_24h.urlThoitrang));
                    arrDanhMucBao.add(new DanhMucBao("Tài chính – Bất động sản", url_24h.urlTaichinh));
                    arrDanhMucBao.add(new DanhMucBao("Ẩm thực", url_24h.urlAmthuc));
                    arrDanhMucBao.add(new DanhMucBao("Làm đẹp", url_24h.urlLamdep));
                    arrDanhMucBao.add(new DanhMucBao("Phim", url_24h.urlPhim));
                    arrDanhMucBao.add(new DanhMucBao("Giáo dục - du học", url_24h.urlGiaoduc));
                    arrDanhMucBao.add(new DanhMucBao("Thể thao", url_24h.urlThethao));
                    arrDanhMucBao.add(new DanhMucBao("Công nghệ thông tin", url_24h.urlCNTT));
                    arrDanhMucBao.add(new DanhMucBao("Ô tô", url_24h.urlOto));
                    arrDanhMucBao.add(new DanhMucBao("Du lịch", url_24h.urlDulich));
                    arrDanhMucBao.add(new DanhMucBao("Sức khỏe đời sống", url_24h.urlSucKhoe));
                    arrDanhMucBao.add(new DanhMucBao("Cười 24h", url_24h.urlCuoi));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DANHMUCBAOBUNDLE", arrDanhMucBao);
                    intent.putExtra("LISTDANHMUCBAO", bundle);
                    startActivity(intent);
                }
                else if(i == 2) {
                    Intent intent = new Intent(TrangChu.this, Danhsachbaibao.class);
                    ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
                    arrDanhMucBao.add(new DanhMucBao("Thời sự ", url_thanhthien.urlThoisu));
                    arrDanhMucBao.add(new DanhMucBao("Thế giới", url_thanhthien.urlThegioi));
                    arrDanhMucBao.add(new DanhMucBao("Kinh tế ", url_thanhthien.urlKinhte));
                    arrDanhMucBao.add(new DanhMucBao("Đời sống", url_thanhthien.urlDoisong));
                    arrDanhMucBao.add(new DanhMucBao("Sức khỏe", url_thanhthien.urlSuckhoe));
                    arrDanhMucBao.add(new DanhMucBao("Giới trẻ", url_thanhthien.urlGioitre));
                    arrDanhMucBao.add(new DanhMucBao("Tiêu dùng thông minh", url_thanhthien.urlTieudung));
                    arrDanhMucBao.add(new DanhMucBao("Giáo dục", url_thanhthien.urlGiaoduc));
                    arrDanhMucBao.add(new DanhMucBao("Du lịch", url_thanhthien.urlDulich));
                    arrDanhMucBao.add(new DanhMucBao("Văn hóa", url_thanhthien.urlVanhoa));
                    arrDanhMucBao.add(new DanhMucBao("Giải trí", url_thanhthien.urlGiaitri));
                    arrDanhMucBao.add(new DanhMucBao("Thể thao", url_thanhthien.urlThethao));
                    arrDanhMucBao.add(new DanhMucBao("Công nghệ - Game", url_thanhthien.urlCNgame));
                    arrDanhMucBao.add(new DanhMucBao("Xe", url_thanhthien.urlXe));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DANHMUCBAOBUNDLE", arrDanhMucBao);
                    intent.putExtra("LISTDANHMUCBAO", bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void actionToolbar() {
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

                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent = new Intent(TrangChu.this, DocBaoOffline.class);
                                startActivity(intent);
                                break;
                            case R.id.chedotoi:
                                    if (isGrey) {
                                        grvDanhSachBao.setBackgroundColor(getResources().getColor(android.R.color.white));
                                        nToolbar.setBackgroundColor(getResources().getColor(R.color.white));
                                        isGrey = false;
                                    }
                                    else {
                                        grvDanhSachBao.setBackgroundColor(getResources().getColor(R.color.custom_black  ));
                                        nToolbar.setBackgroundColor(getResources().getColor(R.color.custom_black));
                                        nToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                                        isGrey = true;
                                    }
                                break;

                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
    }




    private void anhXa() {
        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        grvDanhSachBao = findViewById(R.id.grvDanhSachBao);

        navigationView = (NavigationView) findViewById(R.id.navigationview);


    }
}
