package vn.poly.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.poly.quanlybanhang.Activity.MatHangActivity;

import com.example.duan1android.R;

public class GioiThieu3Acitivity extends AppCompatActivity {

    Button btnApp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        btnApp3=findViewById(R.id.btnBegin);
        btnApp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GioiThieu3Acitivity.this, MatHangActivity.class);
                startActivity(intent);
            }
        });

    }
}