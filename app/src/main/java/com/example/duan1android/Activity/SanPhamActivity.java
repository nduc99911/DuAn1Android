package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1android.R;

public class SanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void anhXaView() {
        imgSanPham = findViewById(R.id.imgThemSanPhamLuu);
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