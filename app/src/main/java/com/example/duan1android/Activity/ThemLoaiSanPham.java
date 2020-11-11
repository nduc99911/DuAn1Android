package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1android.R;

public class ThemLoaiSanPham extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemLoaiSanPhamLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_them_loai_san_pham);
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
        imgThemLoaiSanPhamLuu=findViewById(R.id.imgThemLoaiSanPhamLuu);

    }
    public void ThemLoaiSanPhamLuu(View view){
        Toast.makeText(getApplicationContext(),"Lưu Thành Công",Toast.LENGTH_SHORT).show();
    }
}