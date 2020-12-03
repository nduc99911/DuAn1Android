package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.R;

import java.text.ParseException;
import java.util.List;

import vn.poly.quanlybanhang.Database.HoaDonChiTietDAO;

public class HoaDonChiTiet extends AppCompatActivity {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    List<vn.poly.quanlybanhang.Model.HoaDonChiTiet> hoaDonChiTiets;
    String mahoadon;
    TextView tvDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        anhXaView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mahoadon = bundle.getString("mahoadon");
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvDonHang = findViewById(R.id.tvDonHangHDCT);
        Toast.makeText(getApplicationContext(),""+mahoadon,Toast.LENGTH_SHORT).show();
        try {
            hoaDonChiTiets = hoaDonChiTietDAO.getAllHDCT(mahoadon);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"lỗi"+e,Toast.LENGTH_SHORT).show();
            Log.d("Lỗi",e.toString());
        }

    }

    public void anhXaView() {
        tvDonHang = findViewById(R.id.tvDonHangHDCT);
    }
}