package com.example.duan1android.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1android.Adapter.SanPhamAdapter;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.Fragment.FragmentThem;
import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgSanPham;
    ListView lvList;
    EditText edTimKiem;
    SanPhamDAO sanPhamDAO;
    List<SanPham> list;
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
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SanPhamActivity.this,ChiTietSanPhamActivity.class);
                SanPham sanPham = list.get(i);
                intent.putExtra("sanPham",sanPham.getMaSanPham());
                intent.putExtra("pos",i);
                startActivity(intent);
            }
        });
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

    @Override
    public void onBackPressed() {
        onBackPressed();
    }
}