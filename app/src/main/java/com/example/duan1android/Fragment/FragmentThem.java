package com.example.duan1android.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1android.Activity.DonViTinhActivity;
import com.example.duan1android.Activity.LoaiSanPhamActivity;
import com.example.duan1android.Activity.NguoiDungActivity;
import com.example.duan1android.Activity.SanPhamActivity;
import com.example.duan1android.R;


public class FragmentThem extends Fragment {
    TextView tvMatHang, tvPhanLoai, tvDonViTinh, tvNguoiDung;
    ImageView imgMatHang, imgPhanLoai, imgDonViTinh, imgNguoiDung;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);
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

        //chuyển sang sản phẩm activity
        imgMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(SanPhamActivity.class);
            }
        });
        tvMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(SanPhamActivity.class);
            }
        });

        //chuyển sang loại sản phẩm activity
        imgPhanLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(LoaiSanPhamActivity.class);
            }
        });
        tvPhanLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(LoaiSanPhamActivity.class);
            }
        });

        //chuyển sang đơn vị tính activity
        imgDonViTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(DonViTinhActivity.class);
            }
        });
        tvDonViTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(DonViTinhActivity.class);
            }
        });

        //-> người dùng act
        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(NguoiDungActivity.class);
            }
        });
        tvNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(NguoiDungActivity.class);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
    }

    public void anhXaView(View view) {
        tvMatHang = view.findViewById(R.id.tvMatHang);
        tvPhanLoai = view.findViewById(R.id.tvPhanLoai);
        tvDonViTinh = view.findViewById(R.id.tvDonViTinh);
        tvNguoiDung = view.findViewById(R.id.tvNguoiDung);
        imgMatHang = view.findViewById(R.id.imgMatHang);
        imgPhanLoai = view.findViewById(R.id.imgPhanLoai);
        imgDonViTinh = view.findViewById(R.id.imgDonViTinh);
        imgNguoiDung = view.findViewById(R.id.imgNguoiDung);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_thong_tin);
        drawerLayout = view.findViewById(R.id.drawerLayoutThem);
    }

    public void chuyenAct(Class aClass) {
        Intent intent = new Intent(getActivity(), aClass);
        startActivity(intent);
    }

}