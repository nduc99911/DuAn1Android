package com.example.duan1android.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1android.R;


public class FragmentBaoCao extends Fragment {
    TextView tvSoHoaDon,tvGiaTriHoaDon,tvTienBan,tvTienVon,tvBoLoc;
    Button btnLoiNhuan,btnDoanhThu;
    ImageView imgBoLoc;


    public FragmentBaoCao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bao_cao, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
    }
    public void anhXaView(View view){
        tvSoHoaDon = view.findViewById(R.id.tvSoHoaDon);
        tvGiaTriHoaDon = view.findViewById(R.id.tvGiaTriHoaDon);
        tvTienBan = view.findViewById(R.id.tvTienBan);
        tvTienVon = view.findViewById(R.id.tvTienVon);
        tvBoLoc = view.findViewById(R.id.tvBoLocBaoCao);
        btnLoiNhuan = view.findViewById(R.id.btnLoiNhuan);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        imgBoLoc = view.findViewById(R.id.imgBoLocBaoCao);
    }
}