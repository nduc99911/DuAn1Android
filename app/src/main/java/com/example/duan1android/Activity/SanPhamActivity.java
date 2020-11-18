package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.Fragment.FragmentThem;
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
                finish();
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