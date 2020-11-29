package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ListView;
import vn.poly.quanlybanhang.Adapter.ViewPageAdapter;
import vn.poly.quanlybanhang.Fragment.FragmentBanHang;
import vn.poly.quanlybanhang.Fragment.FragmentBaoCao;
import vn.poly.quanlybanhang.Fragment.FragmentHoaDon;
import vn.poly.quanlybanhang.Fragment.FragmentThem;
import vn.poly.quanlybanhang.Model.GioHang;
import vn.poly.quanlybanhang.Model.SanPham;

import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MatHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
   public TabLayout tabLayout;
   public static List<GioHang> gioHangList;
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
        if(gioHangList!= null){

        }else {
            gioHangList = new ArrayList<>();
        }
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