package com.example.duan1android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duan1android.R;
import com.facebook.CallbackManager;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassWord;
    Button btnLogin;
    TextView tvForgotPassword, tvRegister;
    Intent intent;
CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);

        setupFloatingLabelError();

    }

//    private void logibFB() {
//        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                thongtin();
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//    }

//    private void thongtin() {
//        GraphRequest graphRequest=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try {
//                    email=object.getString("email");
//                    name=object.getString("name");
//                    firstname=object.getString("first_name");
//                    Log.d("JSON",response.getJSONObject().toString());
//                    Bundle bundle=new Bundle();
//                    Intent intent=new Intent(LoginActivity.this,MatHangActivity.class);
//                    bundle.putString("key",name);
//                    intent.putExtra("name",intent);
//                    startActivity(intent);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        Bundle bundle=new Bundle();
//        bundle.putString("fields","name,email,first_name");
//        graphRequest.setParameters(bundle);
//        graphRequest.executeAsync();
//
//    }


    //Đăng Nhập
    public void Login(View view) {

        intent = new Intent(LoginActivity.this, MatHangActivity.class);

        startActivity(intent);
    }

    public void ForgotPassword(View view) {
        intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    //Validate EdtUserName
    private void setupFloatingLabelError() {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        final TextInputLayout textInputLayoutPassWord = (TextInputLayout) findViewById(R.id.textInputLayoutPassWord);
        textInputLayoutUserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    textInputLayoutUserName.setError("Username is required");
                    textInputLayoutUserName.setErrorEnabled(true);

                } else if (charSequence.length() < 5) {
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
                if (charSequence.length() == 0) {
                    textInputLayoutPassWord.setError("PassWord is required");
                    textInputLayoutPassWord.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}