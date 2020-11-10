package com.example.duan1android.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1android.R;


public class FragmentThem extends Fragment {
    TextView tvMatHang,tvPhanLoai,tvDonViTinh,tvNguoiDung;
    ImageView imgMatHang,imgPhanLoai,imgDonViTinh,imgNguoiDung;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_them, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
    }

    public void anhXaView(View view){
        tvMatHang = view.findViewById(R.id.tvMatHang);
        tvPhanLoai = view.findViewById(R.id.tvPhanLoai);
        tvDonViTinh = view.findViewById(R.id.tvDonViTinh);
        tvNguoiDung = view.findViewById(R.id.tvNguoiDung);
        imgMatHang = view.findViewById(R.id.imgMatHang);
        imgPhanLoai = view.findViewById(R.id.imgPhanLoai);
        imgDonViTinh = view.findViewById(R.id.imgDonViTinh);
        imgNguoiDung = view.findViewById(R.id.imgNguoiDung);
    }
}