package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.DonViTinhAdapter;
import com.example.duan1android.Database.DonViTinhDAO;
import com.example.duan1android.Model.DonViTinh;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class DonViTinhActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgDonViTinhThem;
    ListView lvList;
    DonViTinhAdapter donViTinhAdapter;
    DonViTinhDAO donViTinhDAO;
    List<DonViTinh>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_vi_tinh);
        toolbar = findViewById(R.id.toolbar_don_vi_tinh);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXaView();
        list = donViTinhDAO.getAllDonViTinh2();
        donViTinhAdapter = new DonViTinhAdapter(this,list);
        lvList.setAdapter(donViTinhAdapter);
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
        imgDonViTinhThem=findViewById(R.id.imgDonViTinhThem);
        lvList = findViewById(R.id.lvDonViTinh);
        donViTinhDAO = new DonViTinhDAO(this);
        list = new ArrayList<>();
    }
    public void DonViTinhThem(View view){
        Intent intent=new Intent(DonViTinhActivity.this,ThemDonViTinh.class);
        startActivity(intent);
    }

}