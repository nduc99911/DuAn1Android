package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.duan1android.Fragment.FragmentHoaDon;
import com.example.duan1android.MatHangActivity;
import com.example.duan1android.R;

public class BoLocActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    RadioButton rdoTatCa,rdoHomNay,rdoHomQua,rdoTuanNay,rdoTuanTruoc,rdoThangNay,rdoThangTruoc,rdoTatCaHd,rdoChuaThanhToan,rdoDaThanhToan;

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

    //luuw bộ lọc
    public void ToolBarBoLocLuu(View view){
//        Bundle bundle = new Bundle();
//        bundle.putString("dataBoLoc",luuChon());
//        FragmentHoaDon fragmentHoaDon = new FragmentHoaDon();
//        fragmentHoaDon.setArguments(bundle);

        onBackPressed();

    }
    public String luuChon(){
        String luaChon = null;
        if(rdoTatCa.isChecked()){
            luaChon = "Tất cả";
        }else if(rdoHomQua.isChecked()){
            luaChon = "Hôm qua";
        }else if(rdoHomNay.isChecked()){
            luaChon = "Hôm nay";
        }else if(rdoThangNay.isChecked()){
            luaChon = "Tháng này";
        }else if(rdoThangTruoc.isChecked()){
            luaChon = "Tháng trước";
        }else if(rdoTuanTruoc.isChecked()){
            luaChon = "Tuần trước";
        }else if(rdoTuanNay.isChecked()){
            luaChon = "Tuần này";
        }
        return luaChon;
    }
    public void anhXaView(){
        rdoTatCa = findViewById(R.id.radTatCaThoiGian);
        rdoHomNay = findViewById(R.id.radHomNay);
        rdoHomQua = findViewById(R.id.radHomQua);
        rdoTuanNay = findViewById(R.id.radTuanNay);
        rdoTuanTruoc = findViewById(R.id.radTuanTruoc);
        rdoThangNay = findViewById(R.id.radThangNay);
        rdoThangTruoc = findViewById(R.id.radThangTruoc);
        rdoTatCaHd = findViewById(R.id.radTatCaHoaDon);
        rdoChuaThanhToan = findViewById(R.id.radChuaThanhToan);
        rdoDaThanhToan = findViewById(R.id.radDaThanhToan);
    }


}