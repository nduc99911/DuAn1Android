package com.example.duan1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GioiThieu3Acitivity extends AppCompatActivity {
Button btnApp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
btnApp3=findViewById(R.id.btnBengin);
btnApp3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(GioiThieu3Acitivity.this,MatHangActivity.class);
        startActivity(intent);
    }
});
    }
}