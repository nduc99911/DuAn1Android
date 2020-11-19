package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ListView;
import com.example.duan1android.Adapter.ViewPageAdapter;
import com.example.duan1android.Fragment.FragmentBanHang;
import com.example.duan1android.Fragment.FragmentBaoCao;
import com.example.duan1android.Fragment.FragmentHoaDon;
import com.example.duan1android.Fragment.FragmentThem;
import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MatHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
   public TabLayout tabLayout;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_hang);
        navigationView=findViewById(R.id.NavigationView);
        listView=findViewById(R.id.listView);
        drawerLayout=findViewById(R.id.drawerLayout);
        tabLayout=findViewById(R.id.TabLayout);
        viewPager=findViewById(R.id.viewPage);
//        View headerView = navigationView.getHeaderView(1);
//        TextView navUsername = (TextView) headerView.findViewById(R.id.text);
//        navUsername.setText("Hi");
//        imageView=findViewById(R.id.imageView5);
////        imageView.setImageResource(R.drawable.anh1);

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



}