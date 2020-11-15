package com.example.duan1android.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.Activity.BoLocActivity;
import com.example.duan1android.MatHangActivity;
import com.example.duan1android.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class FragmentHoaDon extends Fragment {
    public TextView tvLoaiLoc;
    ImageView imgBoLoc;
    ListView lvListHoaDon;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
String a;
    public FragmentHoaDon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        anhXaView(view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//final MatHangActivity matHangActivity=new MatHangActivity();
//
//matHangActivity.tabLayout.findViewById(R.id.TabLayout);
//matHangActivity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        if(tab.getPosition()==1){
//            String a=matHangActivity.sendata();
////Toast.makeText(getActivity(),"ed"+a,Toast.LENGTH_SHORT).show();
//            if(a!=null){
////     Toast.makeText(getActivity(),"ed"+a,Toast.LENGTH_SHORT).show();
//                tvLoaiLoc.setText(""+a);
//            }
//        }
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//});



        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        //chọn bộ lọc
        imgBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_bo_loc);
                dialog.show();
            }
        });

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Bundle bundle = getArguments();
//        if(bundle==null){
//            Log.e("Bunder","Nullll");
//            return;
//        }else {
//            String data = bundle.getString("dataBoLoc");
//            tvLoaiLoc.setText(data);
//        }
//    }


    public void anhXaView(View view) {
        tvLoaiLoc = view.findViewById(R.id.tvLoaiLoc);
        imgBoLoc = view.findViewById(R.id.imgChonLoaiLoc);
        lvListHoaDon = view.findViewById(R.id.lvListHoaDon);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_hoa_don);
        drawerLayout = view.findViewById(R.id.drawerLayoutHoaDon);
    }

}