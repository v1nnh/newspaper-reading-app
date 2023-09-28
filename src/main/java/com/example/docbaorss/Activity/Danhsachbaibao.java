package com.example.docbaorss.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.docbaorss.Class.DanhMucBao;
import com.example.docbaorss.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class Danhsachbaibao extends AppCompatActivity {

    private boolean isGrey = true;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Toolbar nToolbar;
    DrawerLayout drawer;
    TextView tv_tieudebaibao;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    ArrayList<DanhMucBao> arrDanhMucBaoVNExpress;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenslide_activity);
        Bundle bundle = getIntent().getBundleExtra("LISTDANHMUCBAO");
        arrDanhMucBaoVNExpress = (ArrayList<DanhMucBao>) bundle.getSerializable("DANHMUCBAOBUNDLE");
        AnhXa();

        View parentView = getLayoutInflater().inflate(R.layout.item_listview_dsbaibao, null);
        tv_tieudebaibao = parentView.findViewById(R.id.tv_tieudebaibao);

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
        addControls();
    }


    private void addControls() {
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(mPagerAdapter);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(Danhsachbaibao.this, TrangChu.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent1 = new Intent(Danhsachbaibao.this, DocBaoOffline.class);
                                startActivity(intent1);
                                break;
                            case R.id.chedotoi:
                                if (isGrey) {
                                    tabLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
                                    viewPager.setBackgroundColor(getResources().getColor(R.color.white));
                                    nToolbar.setBackgroundColor(getResources().getColor(R.color.white));
                                    isGrey = false;
                                }
                                else {
                                    tabLayout.setBackgroundColor(getResources().getColor(R.color.custom_black  ));
                                    viewPager.setBackgroundColor(getResources().getColor(R.color.custom_black));
                                    nToolbar.setBackgroundColor(getResources().getColor(R.color.custom_black));
                                    nToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                                    tv_tieudebaibao.setTextColor(getResources().getColor(R.color.white));


//                                        }
//                                    }
                                    isGrey = true;
                                }
                                break;
                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
    }

    private void AnhXa() {
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tablayout);
        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment().create(position,arrDanhMucBaoVNExpress.get(position).getUrlDanhMuc());
        }

        @Override
        public int getCount() {
            return arrDanhMucBaoVNExpress.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arrDanhMucBaoVNExpress.get(position).getTendanhmuc();
        }
    }

    public ArrayList<DanhMucBao> getArrRSSVNExpress() {
        return arrDanhMucBaoVNExpress;
    }
}
