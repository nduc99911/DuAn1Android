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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.Activity.BoLocActivity;
import com.example.duan1android.MatHangActivity;
import com.example.duan1android.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class FragmentHoaDon extends Fragment {
    public TextView tvLoaiLoc,tvTimeLoaiLoc;
    ImageView imgBoLoc;
    ListView lvListHoaDon;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    RadioButton rdoTatCa,rdoHomNay,rdoHomQua,rdoTuanNay,rdoTuanTruoc,rdoThangNay,rdoThangTruoc,rdoTatCaHd,rdoChuaThanhToan,rdoDaThanhToan;
    TextView tvLuuBoLoc;

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
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_bo_loc);
                dialog.show();
                anhXaViewDia(dialog);
                tvLuuBoLoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        tvLoaiLoc.setText(""+luuChonHoaDon());
                        tvTimeLoaiLoc.setText(""+luuChonTime());
                        dialog.dismiss();
                    }
                });

            }
        });

    }


    public void anhXaView(View view) {
        tvLoaiLoc = view.findViewById(R.id.tvLoaiLoc);
        imgBoLoc = view.findViewById(R.id.imgChonLoaiLoc);
        lvListHoaDon = view.findViewById(R.id.lvListHoaDon);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_hoa_don);
        drawerLayout = view.findViewById(R.id.drawerLayoutHoaDon);
        tvTimeLoaiLoc=view.findViewById(R.id.tvTimeLoaiLoc);
    }
    public String luuChonTime(){
        String luaChon = null;
        if(rdoTatCa.isChecked()){
            luaChon = "Tất cả";
        }else if(rdoHomQua.isChecked()){
            luaChon = "Hôm qua";
        }else if(rdoHomNay.isChecked()){
            luaChon = "Hôm nay";
        }else if(rdoThangNay.isChecked()){
            luaChon= "Tháng này";
        }else if(rdoThangTruoc.isChecked()){
            luaChon = "Tháng trước";
        }else if(rdoTuanTruoc.isChecked()){
            luaChon = "Tuần trước";
        }else if(rdoTuanNay.isChecked()){
            luaChon = "Tuần này";
        }
        return luaChon;
    }
    public String luuChonHoaDon(){
        String luaChon = null;
        if(rdoTatCaHd.isChecked()){
            luaChon = "Tất cả";
        }else if(rdoChuaThanhToan.isChecked()){
            luaChon = "Chưa Thanh Toán";
        }else if(rdoDaThanhToan.isChecked()) {
            luaChon = "Đã thanh toán";
        }
        return luaChon;
    }
    public void anhXaViewDia(Dialog dialog){
        rdoTatCa = dialog.findViewById(R.id.radTatCaThoiGian);
        rdoHomNay = dialog.findViewById(R.id.radHomNay);
        rdoHomQua = dialog.findViewById(R.id.radHomQua);
        rdoTuanNay = dialog.findViewById(R.id.radTuanNay);
        rdoTuanTruoc = dialog.findViewById(R.id.radTuanTruoc);
        rdoThangNay = dialog.findViewById(R.id.radThangNay);
        rdoThangTruoc = dialog.findViewById(R.id.radThangTruoc);
        rdoTatCaHd = dialog.findViewById(R.id.radTatCaHoaDon);
        rdoChuaThanhToan = dialog.findViewById(R.id.radChuaThanhToan);
        rdoDaThanhToan = dialog.findViewById(R.id.radDaThanhToan);
        tvLuuBoLoc = dialog.findViewById(R.id.tvLuuBoLocHD);

    }
//    public void luuBoLoc(View view){
//        tvLoaiLoc.setText(""+luuChon());
//
//    }
}