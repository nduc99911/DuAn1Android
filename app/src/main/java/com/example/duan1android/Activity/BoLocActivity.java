package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1android.R;

public class BoLocActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView  imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_loc);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_bo_loc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void anhXaView(){
        imageView=findViewById(R.id. imgToolBarBoLocLuu);


    }
    //luuw bộ lọc
    public void ToolBarBoLocLuu(View view){
        Toast.makeText(getApplicationContext(),"Bạn đã lưu thành công",Toast.LENGTH_SHORT).show();
    }
}