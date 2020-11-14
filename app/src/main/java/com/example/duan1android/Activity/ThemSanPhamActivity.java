package com.example.duan1android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1android.Database.DonViTinhDAO;
import com.example.duan1android.Database.LoaiSanPhamDAO;
import com.example.duan1android.Database.SanPhamDAO;
import com.example.duan1android.Model.SanPham;
import com.example.duan1android.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemAnh;
    EditText edMa,edTen,edSoLuong,edGiaBan,edGiaNhap;
    Spinner spnDonViTinh,spnDanhMuc;
    String ma,ten,soLuong,giaBan,giaNhap,donViTinh,theLoai;
    List<String> listDonVi,listTheLoai;
    SanPhamDAO sanPhamDAO ;
    LinearLayout lnThem;
    byte[] hinhAnh;
    int REQUEST_CODE_FOLDER = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_them_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sanPhamDAO = new SanPhamDAO(this);
        //Đổ dữu liệu cho spinner:
        listDonVi = new ArrayList<>();
        listTheLoai = new ArrayList<>();
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        DonViTinhDAO donViTinhDAO = new DonViTinhDAO(this);
        listDonVi = donViTinhDAO.getAllDonViTinh();
        listTheLoai = loaiSanPhamDAO.getAllTenLoaiSanPham();
        ArrayAdapter adapterDonVi = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listDonVi);
        adapterDonVi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDonViTinh.setAdapter(adapterDonVi);
        ArrayAdapter adapterTheLoai = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listTheLoai);
        adapterDonVi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDanhMuc.setAdapter(adapterTheLoai);
        spnDonViTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donViTinh = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                theLoai = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //sự kiện load ảnh
        imgThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==REQUEST_CODE_FOLDER &&  resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream =  getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgThemAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //sự kiên action bar
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
    //ánh xạ
    public void anhXaView() {
        imgThemAnh = findViewById(R.id.imgThemMatHang);
        edMa = findViewById(R.id.edThemMaMatHang);
        edTen = findViewById(R.id.edThemTenMatHang);
        edSoLuong = findViewById(R.id.edThemSoLuong);
        edGiaBan = findViewById(R.id.edThemGiaBan);
        edGiaNhap = findViewById(R.id.edThemGiaNhap);
        spnDonViTinh = findViewById(R.id.spnThemDonViTinh);
        spnDanhMuc = findViewById(R.id.spnThemDanhMuc);
        lnThem = findViewById(R.id.lnThem);
    }

    public void ThemSanPhamLuu(View view) {
        ma = edMa.getText().toString();
        ten = edTen.getText().toString();
        soLuong = edSoLuong.getText().toString();
        giaBan = edGiaBan.getText().toString();
        giaNhap = edGiaNhap.getText().toString();
        if(ma.equalsIgnoreCase("") || ten.equalsIgnoreCase("")){
            Snackbar snackbar = Snackbar
                    .make(lnThem, "Vui lòng điền chính xác thông tin", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgThemAnh.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            hinhAnh = byteArray.toByteArray();
        }catch (Exception e){

        }
        SanPham sanPham = new SanPham(ma,theLoai,ten,donViTinh,Integer.parseInt(soLuong),Double.parseDouble(giaNhap),Double.parseDouble(giaBan),hinhAnh);
        long chk = sanPhamDAO.addSanPham(sanPham);
        if(chk>0) {
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,SanPhamActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Thêm thất bại , mã sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }
}