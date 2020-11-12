package com.example.duan1android.Fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1android.Activity.DonHangActivity;
import com.example.duan1android.Activity.ThemNguoiDungActivity;
import com.example.duan1android.Activity.ThemSanPhamActivity;
import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentBanHang extends Fragment {


    public FragmentBanHang() {
        // Required empty public constructor
    }

    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    String danhSachLC[] = {"Theo tên","Giá ↑","Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO  sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_ban_hang, container, false);
       toolbar=(Toolbar)view.findViewById(R.id.toolbar_ban_hang);
       imageView=view.findViewById(R.id.imgBanHang);
        drawerLayout=view.findViewById(R.id.drawerLayoutBanHang);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
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
        sanPhamAdapter = new SanPhamAdapter(getContext(),list);
        lvList.setAdapter(sanPhamAdapter);
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,danhSachLC);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocDanhSach.setAdapter(adapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DonHangActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    public void anhXaView(View view){
        edTimKiem = view.findViewById(R.id.edTimKiemMatHang);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_ban_hang, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
}