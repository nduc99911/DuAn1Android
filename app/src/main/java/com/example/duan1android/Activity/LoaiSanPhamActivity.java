package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.LoaiSanPhamAdapter;
import com.example.duan1android.Database.LoaiSanPhamDAO;
import com.example.duan1android.Model.LoaiSanPham;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgLoaiSanPhamThem;
    EditText edTimKiem;
    ListView lvList;
    List<LoaiSanPham> list;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    LoaiSanPhamDAO loaiSanPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_danh_muc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = new ArrayList<>();
        loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        list = loaiSanPhamDAO.getAllLoaiSanPham();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(this,list);
        lvList.setAdapter(loaiSanPhamAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void anhXaView() {
       imgLoaiSanPhamThem=findViewById(R.id.imgLoaiSanPhamThem);
       lvList = findViewById(R.id.lvLoaiSanPham);
       edTimKiem = findViewById(R.id.edTimKiemLoaiSanPham);

    }
    public void LoaiSanPhamThem(View view) {
        Intent intent=new Intent(LoaiSanPhamActivity.this,ThemLoaiSanPham.class);
        startActivity(intent);
    }
}