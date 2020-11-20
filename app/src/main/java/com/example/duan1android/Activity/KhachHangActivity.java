package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.KhachHangAdapter;
import com.example.duan1android.Database.KhachHangDAO;
import com.example.duan1android.Model.KhachHang;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class KhachHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgNguoiDungThem;
    EditText edTimKiem;
    ListView lvList;
    List<KhachHang> list;
    KhachHangDAO khachHangDAO;
    KhachHangAdapter khachHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        toolbar = findViewById(R.id.toolbar_nguoi_dung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXaView();
        list = khachHangDAO.getAllKhachHang();
        khachHangAdapter = new KhachHangAdapter(this,list);
        lvList.setAdapter(khachHangAdapter);
    }
    public void NguoiDungThem(View view){
        Intent intent=new Intent(KhachHangActivity.this,ThemKhachHangActivity.class);
        startActivity(intent);
    }
    public void anhXaView() {
       imgNguoiDungThem=findViewById(R.id.imgNguoiDungThem);
       edTimKiem = findViewById(R.id.edTimKiemNguoiDung);
       lvList = findViewById(R.id.lvNguoiDung);
       list = new ArrayList<>();
       khachHangDAO = new KhachHangDAO(this);
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
}