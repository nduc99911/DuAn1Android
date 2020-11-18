package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgSanPham;
    ListView lvList;
    EditText edTimKiem;
    SanPhamDAO sanPhamDAO;
    List list;
    SanPhamAdapter sanPhamAdapter;
    EditText edMa, edTen, edSoLuong, edGiaBan, edGiaNhap;
    String ma, ten, soLuong, giaBan, giaNhap, donViTinh, theLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sanPhamDAO = new SanPhamDAO(this);
        list = new ArrayList<>();
        list = sanPhamDAO.getAllSanPham();
        sanPhamAdapter = new SanPhamAdapter(this, list);
        lvList.setAdapter(sanPhamAdapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemClickEvent();
            }
        });
    }

    private void onItemClickEvent() {
        Context context;
        Dialog dialog = new Dialog(this, android.R.style.Theme_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.activity_sua_san_pham);
        dialog.show();
        ImageView imgSuaSanPham = dialog.findViewById(R.id.imgLuuSuaSanPham);

    }

    public void anhXaView() {
        imgSanPham = findViewById(R.id.imgThemSanPhamLuu);
        lvList = findViewById(R.id.lvSanPham_actSP);
        edTimKiem = findViewById(R.id.edTimKiemSanPham);
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
    //chuyển sang activity thêm sản phẩm
    public void SanPhamThem(View view) {
        Intent intent = new Intent(this,ThemSanPhamActivity.class);
        startActivity(intent);

    }
}