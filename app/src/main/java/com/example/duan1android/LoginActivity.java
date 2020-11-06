package com.example.duan1android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    EditText edUserName,edPassWord;
    Button btnLogin;
    TextView tvForgotPassword,tvRegister;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       toolbar=findViewById(R.id.too);
       edUserName=findViewById(R.id.edUserName);
        edPassWord=findViewById(R.id.edPassWord);
        btnLogin=findViewById(R.id.btnLogin);
        tvForgotPassword=findViewById(R.id.tvForgotPassword);
        tvRegister=findViewById(R.id.tvRegister);
       setSupportActionBar(toolbar);


    }
    //Đăng Nhập
    public void Login(View view) {

    }

    public void ForgotPassword(View view) {
        intent=new Intent(LoginActivity.this,ForgotPassActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}