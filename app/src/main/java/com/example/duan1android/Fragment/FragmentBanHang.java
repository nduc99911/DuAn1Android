package com.example.duan1android.Fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.Activity.DonHangActivity;
import com.example.duan1android.Activity.ThemNguoiDungActivity;
import com.example.duan1android.Activity.ThemSanPhamActivity;
import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.LoginActivity;
import com.example.duan1android.MatHangActivity;
import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentBanHang extends Fragment {


    public FragmentBanHang() {
        // Required empty public constructor
    }

    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    String danhSachLC[] = {"Theo tên", "Giá ↑", "Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    Handler handler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ban_hang, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_ban_hang);
        imageView = view.findViewById(R.id.imgBanHang);
        navigationView = view.findViewById(R.id.NavigationViewBanHang);
        drawerLayout = view.findViewById(R.id.drawerLayoutBanHang);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        //setText navHearder
//        View headerView = navigationView.getHeaderView(0);
//        TextView navUsername = (TextView) headerView.findViewById(R.id.text);
//        ImageView img = (ImageView) headerView.findViewById(R.id.image);
//        navUsername.setText("Your Text Here");
time();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        list = sanPhamDAO.getAllSanPham();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        lvList.setAdapter(sanPhamAdapter);
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danhSachLC);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocDanhSach.setAdapter(adapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DonHangActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void anhXaView(View view) {
        edTimKiem = view.findViewById(R.id.edTimKiemMatHang);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);


    }

    public void time() {

        View headerView = navigationView.getHeaderView(0);
        ImageView img = (ImageView) headerView.findViewById(R.id.image);
        TextView navUsername = (TextView) headerView.findViewById(R.id.text);
        TextView tvName=headerView.findViewById(R.id.tvNameNavi);
        ConstraintLayout constraintLayoutt=headerView.findViewById(R.id.test);
        TextView tvNgay=headerView.findViewById(R.id.tvNgay);
        final TextView tvGio=headerView.findViewById(R.id.tvGio);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        final int hour= c.get(Calendar.HOUR_OF_DAY);
        final int mintute = c.get(Calendar.MINUTE);
        final int second = c.get(Calendar.SECOND);
        tvNgay.setText(date+"/"+month+"/"+year);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Calendar c = Calendar.getInstance();
               int hour= c.get(Calendar.HOUR_OF_DAY);
                int mintute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                tvGio.setText(hour+"h:"+mintute+"m:"+second+"s");
                handler.postDelayed(this,0);
            }
        };
        runnable.run();

        if(hour<=12){
            navUsername.setText("Chào Buổi Sáng");
            constraintLayoutt.setBackgroundResource(R.drawable.troisang);

        }
        else if(hour>12) {
            navUsername.setText("Chào Buổi Chiều");
        }
        else if(hour>18) {
            navUsername.setText("Chào Buổi Tối");
            constraintLayoutt.setBackgroundResource(R.drawable.mattrang);
        }
    }

}