package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1android.R;

public class DonHangActivity extends AppCompatActivity {
    ListView lvList;
    EditText edMaDonHang,edKhachHang;
    TextView tvChietKhau,tvTongTien;
    ImageView imgChietKhau;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        anhXaView();
    }
    public void anhXaView(){
        lvList = findViewById(R.id.lvListHang_GH);
        edMaDonHang = findViewById(R.id.edMaDonHang_GH);
        edKhachHang = findViewById(R.id.edKhachHang_GH);
        tvChietKhau = findViewById(R.id.tvChietKhau_GH);
        tvTongTien = findViewById(R.id.tvTongTien_GH);
        imgChietKhau = findViewById(R.id.imgChietKhau_GH);
        btnThanhToan = findViewById(R.id.btnThanhToan_GH);
    }
}