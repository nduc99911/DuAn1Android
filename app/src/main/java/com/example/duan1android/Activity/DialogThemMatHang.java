package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1android.R;

public class DialogThemMatHang extends AppCompatActivity {
    ImageView imgThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_them_mat_hang);

        imgThoat=findViewById(R.id.imgDiaLogThemMatHangThoat);


    }
}