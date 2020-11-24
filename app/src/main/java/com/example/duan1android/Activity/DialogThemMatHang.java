package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class DialogThemMatHang extends AppCompatActivity {
    ImageView imgThoat;
    ListView lvDiaLogThemMatHang;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    List<SanPham> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_them_mat_hang);
        imgThoat=findViewById(R.id.imgDiaLogThemMatHangThoat);
        lvDiaLogThemMatHang=findViewById(R.id.lvDiaLogThemMatHang);
        sanPhamDAO=new SanPhamDAO(this);
        list=sanPhamDAO.getAllSanPham();
        sanPhamAdapter=new SanPhamAdapter(this,list);
        lvDiaLogThemMatHang.setAdapter(sanPhamAdapter);


    }
}