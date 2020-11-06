package com.example.duan1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
EditText edUserName,edPassWord,edRePassWord,edHoTen,edEmail,edPhone;
Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUserName=findViewById(R.id.edUserName);
        edPassWord=findViewById(R.id.edPassWord);
        edRePassWord=findViewById(R.id.edRePassWord);
        edHoTen=findViewById(R.id.edHoTen);
        edEmail=findViewById(R.id.edEmail);
        edPhone=findViewById(R.id.edPhone);
btnRegister=findViewById(R.id.btnRegister);
    }
}