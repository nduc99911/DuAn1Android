package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.quanlybanhang.Database.KhachHangDAO;
import vn.poly.quanlybanhang.Model.KhachHang;

import com.example.duan1android.R;

public class ChiTietKhachHangActivity extends AppCompatActivity {
    TextView tvTen, tvSDT, tvDiaChi, tvEmail, tvTienNo, tvTienDaMua;
    KhachHangDAO khachHangDAO;
    Button btnUpdate, btnDelete, btnSuaNo;
    KhachHang khachHang;
    Context context = this;
    EditText edTen, edSoDienThoai, edEmail, edDiaChi, edTienNo, edTienDaMua;
    ImageView imgLuu;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khach_hang);
        anhXaView();
        Intent intent = getIntent();
        phone = intent.getStringExtra("phoneNumber");
        khachHang = khachHangDAO.getKhachHangBySDT(phone);
        tvTen.setText("Tên khách hàng : " + khachHang.getTen());
        tvSDT.setText("Số điện thoại : " + khachHang.getSoDienThoai());
        tvDiaChi.setText("Địa chỉ : " + khachHang.getDiaChi());
        tvEmail.setText("Email : " + khachHang.getEmail());
        tvTienNo.setText("Tiền còn nợ : " + Math.round(khachHang.getTienNo()) + " VNĐ");
        tvTienDaMua.setText("Tiền đã thanh toán : " + Math.round(khachHang.getTienDaMua()) + " VNĐ");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long chk = khachHangDAO.deleteKhachHang(khachHang.getSoDienThoai());
                if (chk > 0) {
                    Toast.makeText(ChiTietKhachHangActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChiTietKhachHangActivity.this, KhachHangActivity.class));
                } else {
                    Toast.makeText(ChiTietKhachHangActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.dialog_sua_khach_hang);
                dialog.show();
                anhXaViewDia(dialog);
                edTen.setText("" + khachHang.getTen());
                edSoDienThoai.setText("" + khachHang.getSoDienThoai());
                edDiaChi.setText("" + khachHang.getDiaChi());
                edEmail.setText("" + khachHang.getEmail());
                edTienNo.setText("" + Math.round(khachHang.getTienNo()));
                imgLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ten = edTen.getText().toString();
                        String soDienThoai = edSoDienThoai.getText().toString();
                        String diaChi = edDiaChi.getText().toString();
                        String email = edEmail.getText().toString();
                        String tienNo = edTienNo.getText().toString();
                        if (ten.equalsIgnoreCase("") || soDienThoai.equalsIgnoreCase("")) {
                            Toast.makeText(context, "Tên , số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        KhachHang khachHang = new KhachHang(ten, email, soDienThoai, diaChi, Integer.parseInt(tienNo), khachHangDAO.getKhachHangBySDT(phone).getTienDaMua());
                        try {
                            khachHangDAO.updateKhacHang(khachHang, phone);
                            Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            startActivity(new Intent(ChiTietKhachHangActivity.this,KhachHangActivity.class));
                        } catch (Exception e) {
                            Toast.makeText(context, "Lưu thất bại , số điện thoại này đã tồn tại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        btnSuaNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.dialog_sua_no);
                dialog.show();
                Button btnLuu = dialog.findViewById(R.id.btnLuuNo);
                Button btnnHuy = dialog.findViewById(R.id.btnHuySuaNo);
                final EditText edSuaNo = (EditText) dialog.findViewById(R.id.edSuaTienNoRieng);
                btnnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String no = edSuaNo.getText().toString();
                        khachHang.setTienNo(Double.parseDouble(no));
                        long chk = khachHangDAO.updateTienNo(khachHang, phone);
                        if (chk > 0) {
                            Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            tvTienNo.setText("Tiền còn nợ : " + no + " VNĐ");
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void anhXaView() {
        tvTen = findViewById(R.id.tvTenKH);
        tvDiaChi = findViewById(R.id.tvDiaChiKH);
        tvEmail = findViewById(R.id.tvEmailKH);
        tvTienNo = findViewById(R.id.tvTienNoKH);
        tvTienDaMua = findViewById(R.id.tvTienDaMuaKH);
        tvSDT = findViewById(R.id.tvSDTKH);
        khachHangDAO = new KhachHangDAO(this);
        btnUpdate = findViewById(R.id.btnUpdateKH);
        btnDelete = findViewById(R.id.btnXoaKH);
        btnSuaNo = findViewById(R.id.btnSuaNo);
    }

    private void anhXaViewDia(Dialog dialog) {
        edTen = (EditText) dialog.findViewById(R.id.edSuaHoTenKH);
        edSoDienThoai = (EditText) dialog.findViewById(R.id.edSuaSoDienThoaiKH);
        edEmail = (EditText) dialog.findViewById(R.id.edSuaEmailKH);
        edDiaChi = (EditText) dialog.findViewById(R.id.edSuaDiaChiKH);
        edTienNo = (EditText) dialog.findViewById(R.id.edSuaTienNo);
        edTienDaMua = (EditText) dialog.findViewById(R.id.edSuaTienDaMua);
        imgLuu = dialog.findViewById(R.id.imgLuuKH);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChiTietKhachHangActivity.this, KhachHangActivity.class));
    }
}