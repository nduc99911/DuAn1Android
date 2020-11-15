package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duan1android.Database.DonViTinhDAO;
import com.example.duan1android.Model.DonViTinh;
import com.example.duan1android.R;
import com.google.android.material.snackbar.Snackbar;

public class ThemDonViTinh extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemDonViTinhLuu;
    EditText edThemDonViTinh;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_don_vi_tinh);
        toolbar = findViewById(R.id.toolbar_them_don_vi_tinh);
        anhXaView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void anhXaView() {
        imgThemDonViTinhLuu=findViewById(R.id.imgThemDonViTinhLuu);
        edThemDonViTinh=findViewById(R.id.edThemDonViTinh);
        linearLayout=findViewById(R.id.lnThemDonViTinh);

    }
    public void ThemDonViTinhLuu(View view){
        String donVi=edThemDonViTinh.getText().toString();
        if(donVi.equalsIgnoreCase("")){
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Vui lòng điền đầy đủ thông tin", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        DonViTinhDAO donViTinhDAO=new DonViTinhDAO(this);
        if(donViTinhDAO.addDonVi(donVi)>0){
            Toast.makeText(getApplicationContext(),"Thêm đơn vị thành công",Toast.LENGTH_LONG).show();
            finish();

        }
        else {
            Toast.makeText(getApplicationContext(),"Thêm Không thành công",Toast.LENGTH_LONG).show();
        }
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
}