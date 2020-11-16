package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1android.Adapter.NguoiDungAdapter;
import com.example.duan1android.Database.NguoiDungDAO;
import com.example.duan1android.Model.NguoiDung;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgNguoiDungThem;
    EditText edTimKiem;
    ListView lvList;
    List<NguoiDung> list;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter nguoiDungAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        toolbar = findViewById(R.id.toolbar_nguoi_dung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXaView();
        list = nguoiDungDAO.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDungAdapter(this,list);
        lvList.setAdapter(nguoiDungAdapter);

    }
    public void NguoiDungThem(View view){
        Intent intent=new Intent(NguoiDungActivity.this,ThemNguoiDungActivity.class);
        startActivity(intent);
    }
    public void anhXaView() {
       imgNguoiDungThem=findViewById(R.id.imgNguoiDungThem);
       edTimKiem = findViewById(R.id.edTimKiemNguoiDung);
       lvList = findViewById(R.id.lvNguoiDung);
       list = new ArrayList<>();
       nguoiDungDAO = new NguoiDungDAO(this);
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