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
import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MatHangActivity extends AppCompatActivity {
    ViewPager viewPager;
   public TabLayout tabLayout;
   public static List<GioHang> gioHangList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_hang);
        tabLayout=findViewById(R.id.TabLayout);
        viewPager=findViewById(R.id.viewPage);
        if(gioHangList == null){
            gioHangList = new ArrayList<>();
        }


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