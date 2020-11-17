package com.example.duan1android;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.duan1android.Fragment.FragmentHoaDon;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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