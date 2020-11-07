package com.example.duan1android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.duan1android.Adapter.ViewPageAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MatHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_hang);
        toolbar=findViewById(R.id.too);
        navigationView=findViewById(R.id.NavigationView);
        listView=findViewById(R.id.listView);
        drawerLayout=findViewById(R.id.drawerLayout);
        tabLayout=findViewById(R.id.TabLayout);
        actionToolBar();
        viewPager=findViewById(R.id.viewPage);
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new FragmentBanHang(),"Bán Hàng");
        viewPageAdapter.addFragment(new FragmentHoaDon(),"Hóa Đơn");
        viewPageAdapter.addFragment(new FragmentBaoCao(),"Báo Cáo");
        viewPageAdapter.addFragment(new FragmentThem(),"Thêm");
        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconbanhangactivity);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconhoadonactivity);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconbaocaoactivity);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconthemactivity);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

}