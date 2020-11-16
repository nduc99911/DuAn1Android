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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1android.Database.NguoiDungDAO;
import com.example.duan1android.Model.NguoiDung;
import com.example.duan1android.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemNguoiDungActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemNguoiDungLuu,imgThemAnhNguoiDung;
    EditText edUserName,edPassword,edComfirmPassword,edEmail,edDienThoai,edHoTen;
    byte[] hinhAnh;
    int REQUEST_CODE_FOLDER = 456;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        toolbar = findViewById(R.id.toolbar_them_nguoi_dung);
        setSupportActionBar(toolbar);
        anhXaView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgThemAnhNguoiDung.setOnClickListener(new View.OnClickListener() {
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
                imgThemAnhNguoiDung.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void ThemNguoiDungLuu(View view){
        String userName = edUserName.getText().toString();
        String password = edPassword.getText().toString();
        String rePassword = edComfirmPassword.getText().toString();
        String email = edEmail.getText().toString();
        String phoneNumer = edDienThoai.getText().toString();
        String hoTen = edHoTen.getText().toString();
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgThemAnhNguoiDung.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            hinhAnh = byteArray.toByteArray();
        }catch (Exception e){

        }
        if(userName.equalsIgnoreCase("") || password.equalsIgnoreCase("") ||
            rePassword.equalsIgnoreCase("") || email.equalsIgnoreCase("") ||
            phoneNumer.equalsIgnoreCase("") || hoTen.equalsIgnoreCase("")){
                Toast.makeText(this,"Vui lòng nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
                return;
        }
        if(!password.equalsIgnoreCase(rePassword)){
            Toast.makeText(this,"Mật khẩu không giống nhau",Toast.LENGTH_SHORT).show();
            return;
        }
        String regexPhoneNumber = "^[0]{1}\\d{9}$";
        if(!phoneNumer.matches(regexPhoneNumber)){
            Toast.makeText(this,"Số điện thoại bắt đầu bằng 0 và có 10 kí tự",Toast.LENGTH_SHORT).show();
            return;
        }
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if(!email.matches(regexEmail)){
            Toast.makeText(this,"Email không đúng",Toast.LENGTH_SHORT).show();
            return;
        }
        NguoiDung nguoiDung = new NguoiDung(userName,password,phoneNumer,email,hoTen,hinhAnh);
        long chk = nguoiDungDAO.addNguoiDung(nguoiDung);
        if(chk>0){
            Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,NguoiDungActivity.class));
        }else{
            Toast.makeText(this,"Tài khoản đã tồn tại,vui lòng thử lại",Toast.LENGTH_SHORT).show();
        }
    }
    public void anhXaView() {
        imgThemNguoiDungLuu=findViewById(R.id.imgThemNguoiDungLuu);
        edUserName = findViewById(R.id.edThemTenDangNhap);
        edPassword = findViewById(R.id.edThemPassword);
        edComfirmPassword = findViewById(R.id.edThemRePassword);
        edEmail = findViewById(R.id.edThemEmail);
        edDienThoai = findViewById(R.id.edThemSoDienThoai);
        edHoTen = findViewById(R.id.edThemHoTen);
        imgThemAnhNguoiDung = findViewById(R.id.imgThemAnhNguoiDung);
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