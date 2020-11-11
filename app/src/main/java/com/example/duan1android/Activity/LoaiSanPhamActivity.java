package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1android.R;

public class LoaiSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgLoaiSanPhamThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_danh_muc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    }
    public void LoaiSanPhamThem(View view) {
        Intent intent=new Intent(LoaiSanPhamActivity.this,ThemLoaiSanPham.class);
        startActivity(intent);
    }
}