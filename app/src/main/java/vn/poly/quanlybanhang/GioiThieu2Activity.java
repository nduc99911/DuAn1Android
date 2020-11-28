package vn.poly.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duan1android.R;

public class GioiThieu2Activity extends AppCompatActivity {


    Button btnApp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnApp2=findViewById(R.id.btnApp2);
        btnApp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GioiThieu2Activity.this,GioiThieu3Acitivity.class);
                startActivity(intent);
            }
        });
    }

}