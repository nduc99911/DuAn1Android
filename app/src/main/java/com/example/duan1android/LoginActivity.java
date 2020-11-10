package com.example.duan1android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
        setupFloatingLabelError();

    }



    //Đăng Nhập
    public void Login(View view) {
intent=new Intent(LoginActivity.this,MatHangActivity.class);
startActivity(intent);
    }

    public void ForgotPassword(View view) {
        intent=new Intent(LoginActivity.this,ForgotPassActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    //Validate EdtUserName
    private void setupFloatingLabelError() {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        final TextInputLayout textInputLayoutPassWord = (TextInputLayout) findViewById(R.id.textInputLayoutPassWord);
        textInputLayoutUserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0 ) {
                    textInputLayoutUserName.setError("Username is required");
                    textInputLayoutUserName.setErrorEnabled(true);
                } else if (charSequence.length() < 5 ) {
                    textInputLayoutUserName.setError("Username is required and length must be >= 5");
                    textInputLayoutUserName.setErrorEnabled(true);
                } else {
                    textInputLayoutUserName.setErrorEnabled(false);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //validte password
        textInputLayoutPassWord.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0 ) {
                    textInputLayoutPassWord.setError("PassWord is required");
                    textInputLayoutPassWord.setErrorEnabled(true);
                } else if (charSequence.length() < 5 ) {
                    textInputLayoutPassWord.setError("Pass is required and length must be >= 5");
                    textInputLayoutPassWord.setErrorEnabled(true);
                } else {
                    textInputLayoutPassWord.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}