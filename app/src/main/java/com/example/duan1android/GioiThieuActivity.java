package com.example.duan1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GioiThieuActivity extends AppCompatActivity {

    Button btnApp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
btnApp1=findViewById(R.id.btnApp1);
btnApp1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(GioiThieuActivity.this, GioiThieu2Activity.class);
        startActivity(intent);
    }
});

   

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


}