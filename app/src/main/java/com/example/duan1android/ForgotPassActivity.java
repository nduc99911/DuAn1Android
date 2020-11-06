package com.example.duan1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassActivity extends AppCompatActivity {
EditText edEmail,edPhone,edUserName;
Button btnBack,btnForgotPass;
TextView tvForgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        edEmail=findViewById(R.id.edEmail);
        edPhone=findViewById(R.id.edPhone);
        edUserName=findViewById(R.id.edUserName);
        btnBack=findViewById(R.id.btnBack);
        btnForgotPass=findViewById(R.id.btnForgotPassword);
        tvForgotPass=findViewById(R.id.tvForgotPassword);
    }
//Quên Mật Khẩu
    public void ForgotPassword(View view) {
    }
//Trở lại LoginActivity
    public void Back(View view) {
        finish();
    }
}