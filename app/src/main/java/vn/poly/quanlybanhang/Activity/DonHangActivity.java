package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import vn.poly.quanlybanhang.Adapter.DonHangAdapter;
import vn.poly.quanlybanhang.Adapter.SanPhamAdapter;
import vn.poly.quanlybanhang.Database.SanPhamDAO;
import vn.poly.quanlybanhang.Model.SanPham;

import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DonHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ListView lvList;
    EditText edMaDonHang, edKhachHang;
    TextView tvChietKhau, tvTongTien;
    ImageView imgChietKhau, imgXoaDonHang;
    Button btnThanhToan;
    DonHangAdapter donHangAdapter;
    ToggleButton toggleButton;
    EditText edChietKhau;
    int tongTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_ban_hang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        donHangAdapter = new DonHangAdapter(DonHangActivity.this,MatHangActivity.gioHangList);
        lvList.setAdapter(donHangAdapter);
        getTongTien();
        addChietKhau();
        xoaDonHang();
    }


    public void anhXaView() {
        lvList = findViewById(R.id.lvListHang_GH);
        edMaDonHang = findViewById(R.id.edMaDonHang_GH);
        edKhachHang = findViewById(R.id.edKhachHang_GH);
        tvChietKhau = findViewById(R.id.tvChietKhau_GH);
        tvTongTien = findViewById(R.id.tvTongTien_GH);
        imgChietKhau = findViewById(R.id.imgChietKhau_GH);
        btnThanhToan = findViewById(R.id.btnThanhToan_GH);
        imgXoaDonHang = findViewById(R.id.imgxoaDonHang);

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
    private void addChietKhau(){
        imgChietKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DonHangActivity.this);
                dialog.setContentView(R.layout.chiet_khau_dialog);
                dialog.show();

                toggleButton = dialog.findViewById(R.id.toggleButton);
                edChietKhau = dialog.findViewById(R.id.edChietKhau);
                Button btnOk = dialog.findViewById(R.id.btnOkCK);
                Button btnHuy = dialog.findViewById(R.id.btnHuyCK);
            //   toogle true = tiền mặt ; false = %
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edChietKhau.getText().toString().equalsIgnoreCase("")){
                            tvChietKhau.setText("0");
                            dialog.dismiss();
                            return;
                        }
                        if(toggleButton.isChecked()){
                            if(Integer.parseInt(edChietKhau.getText().toString()) > tongTien){
                                Toast.makeText(DonHangActivity.this,"Chiết khẩu không được lớn hơn số tiền thanh toán",Toast.LENGTH_SHORT).show();
                                edChietKhau.setText("");
                                return;
                            }else{
                                tvChietKhau.setText(""+edChietKhau.getText().toString()+ " VNĐ");
                                tvTongTien.setText(""+(tongTien-Integer.parseInt(edChietKhau.getText().toString()))+ " VNĐ");
                            }
                        }else {
                            if(Integer.parseInt(edChietKhau.getText().toString())< 0 || Integer.parseInt(edChietKhau.getText().toString())> 100){
                                Toast.makeText(DonHangActivity.this,"Chiết khẩu phải từ 0-100%",Toast.LENGTH_SHORT).show();
                                edChietKhau.setText("");
                                return;
                            }else{
                                tvChietKhau.setText(""+edChietKhau.getText().toString() +" %");
                                tvTongTien.setText(""+(tongTien-(tongTien*(Double.parseDouble(edChietKhau.getText().toString())/100))) + " VNĐ");
                            }
                        }
                        dialog.dismiss();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }
    private void getTongTien(){
        for (int i = 0;i<MatHangActivity.gioHangList.size();i++){
            tongTien +=(MatHangActivity.gioHangList.get(i).getGia()*MatHangActivity.gioHangList.get(i).getSoLuong());
        }
        tvTongTien.setText(""+tongTien+ " VNĐ");
    }
    private void xoaDonHang(){
        imgXoaDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DonHangActivity.this);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc muốn xóa đơn hàng này ?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        MatHangActivity.gioHangList.clear();
                        DonHangAdapter donHangAdapter = new DonHangAdapter(DonHangActivity.this,MatHangActivity.gioHangList);
                        lvList.setAdapter(donHangAdapter);
                    }
                });
                builder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }


//    public void ThemDonHang(View view) {
//
//        LayoutInflater inflater = getLayoutInflater();
//        final View alertLayout = inflater.inflate(R.layout.activity_dialog_them_mat_hang, null);
//        final ImageView imgThoat = alertLayout.findViewById(R.id.imgDiaLogThemMatHangThoat);
//        final ListView lvDiaLogThemMatHang = alertLayout.findViewById(R.id.lvDiaLogThemMatHang);
//        sanPhamDAO = new SanPhamDAO(this);
//        final ArrayList<String> maSanPham = new ArrayList<>();
//        list = sanPhamDAO.getAllSanPham();
//        final SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(this, list);
//        lvDiaLogThemMatHang.setAdapter(sanPhamAdapter);
//        lvDiaLogThemMatHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //cập nhật lại số lượng
//                maSanPham.add(list.get(i).getMaSanPham());
//                String ma = list.get(i).getMaSanPham();
//                String maloai = list.get(i).getMaLoai();
//                String ten = list.get(i).getTen();
//                String donViTinh = list.get(i).getDonViTinh();
//                double giaNhap = list.get(i).getGiaNhap();
//                double giaBan = list.get(i).getGiaBan();
//                byte[] image = list.get(i).getImage();
//                int soluong = list.get(i).getSoLuong() - 1;
//                SanPham sanPham = new SanPham(ma, maloai, ten, donViTinh, soluong, giaNhap, giaBan, image);
//                sanPhamDAO.updateSanPham(sanPham, ma);
//                list = sanPhamDAO.getAllSanPham();
//                SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getApplication(), list);
//                lvDiaLogThemMatHang.setAdapter(sanPhamAdapter);
//            }
//        });
//        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setView(alertLayout);
//        alert.setCancelable(false);
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
////xử lý số lượng sản phẩm
//                maSanPham.addAll(ds);
//                Set<String> set = new HashSet<>(maSanPham);
//                ArrayList<String> temp_array = new ArrayList<>();
//                temp_array.addAll(set);
//                for (int i = 0; i < temp_array.size(); i++) {
//                    con = Collections.frequency(maSanPham, temp_array.get(i));
//                    Toast.makeText(getApplicationContext(), "" + con, Toast.LENGTH_SHORT).show();
//                    String ma = list.get(i).getMaSanPham();
//                    String maloai = list.get(i).getMaLoai();
//                    String ten = list.get(i).getTen();
//                    String donViTinh = list.get(i).getDonViTinh();
//                    double giaNhap = list.get(i).getGiaNhap();
//                    double giaBan = list.get(i).getGiaBan();
//                    byte[] image = list.get(i).getImage();
//                    int soluong = list.get(i).getSoLuong();
//                    SanPham sanPham = new SanPham(ma, maloai, ten, donViTinh, soluong, giaNhap, giaBan, image, con);
//                    sanPhamDAO.updateSanPham(sanPham, ma);
//                }
//                //xóa các phần tử trùng
//                ArrayList<String> arrTemp = new ArrayList<>();
//                // thêm các phần tử của arrListNumber vào arrTemp
//                // nếu trong arrTemp đã tồn tại phần tử giống trong arrListNumber
//                // thì không thêm vào, ngược lại thêm bình thường
//                for (int i = 0; i < maSanPham.size(); i++) {
//                    if (!arrTemp.contains(maSanPham.get(i))) {
//                        arrTemp.add(maSanPham.get(i));
//                    }
//                }
//                // xóa các phần tử của arrListNumber
//                maSanPham.clear();
//                // thêm tất cả các phần tử của arrTemp vào arrListNumber
//                // lúc này ta sẽ có 1 ArrayList arrListNumber
//                // không chứa các phần tử trùng nhau
//                maSanPham.addAll(arrTemp);
//
//
//                sanPhamDAO = new SanPhamDAO(getApplicationContext());
//                for (int i = 0; i < maSanPham.size(); i++) {
//                    String ma = maSanPham.get(i);
//                    list2.add(sanPhamDAO.getSanPhamTheoMa(ma));
//                }
//
//                Toast.makeText(getApplicationContext(), "" + list2.size(), Toast.LENGTH_SHORT).show();
//                donHangAdapter = new DonHangAdapter(getApplicationContext(), list2);
//                lvList.setAdapter(donHangAdapter);
//
//
//            }
//        });
//
//        final AlertDialog dialog = alert.create();
//        imgThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }


}