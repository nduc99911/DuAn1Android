package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import vn.poly.quanlybanhang.Adapter.DonHangAdapter;
import vn.poly.quanlybanhang.Adapter.KhachHangAdapter;
import vn.poly.quanlybanhang.Adapter.SanPhamAdapter;
import vn.poly.quanlybanhang.Database.HoaDonChiTietDAO;
import vn.poly.quanlybanhang.Database.HoaDonDAO;
import vn.poly.quanlybanhang.Database.KhachHangDAO;
import vn.poly.quanlybanhang.Database.SanPhamDAO;
import vn.poly.quanlybanhang.Model.HoaDon;
import vn.poly.quanlybanhang.Model.HoaDonChiTiet;
import vn.poly.quanlybanhang.Model.KhachHang;
import vn.poly.quanlybanhang.Model.SanPham;

import com.example.duan1android.R;

import java.sql.Savepoint;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DonHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ListView lvList;
    EditText edMaDonHang;
    TextView tvChietKhau, tvTongTien, tvKhachHang;
    ImageView imgChietKhau, imgXoaDonHang, imgChonKhachHang;
    Button btnThanhToan;
    DonHangAdapter donHangAdapter;
    ToggleButton toggleButton;
    EditText edChietKhau;
    int tongTien = 0;
    String khachHang;
    List<KhachHang> listKH;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    int chietKhau = 0;
    int tienFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_ban_hang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(MatHangActivity.gioHangList.size()>0) {
            donHangAdapter = new DonHangAdapter(DonHangActivity.this, MatHangActivity.gioHangList);
            lvList.setAdapter(donHangAdapter);
        }
        getTongTien();
        addChietKhau();
        xoaDonHang();
    }


    public void anhXaView() {
        lvList = findViewById(R.id.lvListHang_GH);
        edMaDonHang = findViewById(R.id.edMaDonHang_GH);
        tvKhachHang = findViewById(R.id.edKhachHang_GH);
        tvChietKhau = findViewById(R.id.tvChietKhau_GH);
        tvTongTien = findViewById(R.id.tvTongTien_GH);
        imgChietKhau = findViewById(R.id.imgChietKhau_GH);
        btnThanhToan = findViewById(R.id.btnThanhToan_GH);
        imgXoaDonHang = findViewById(R.id.imgxoaDonHang);
        imgChonKhachHang = findViewById(R.id.imgChonKhachHang);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
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

    private void addChietKhau() {
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
                        if (edChietKhau.getText().toString().equalsIgnoreCase("")) {
                            tvChietKhau.setText("0");
                            dialog.dismiss();
                            return;
                        }
                        if (toggleButton.isChecked()) {
                            if (Integer.parseInt(edChietKhau.getText().toString()) > tongTien) {
                                Toast.makeText(DonHangActivity.this, "Chiết khẩu không được lớn hơn số tiền thanh toán", Toast.LENGTH_SHORT).show();
                                edChietKhau.setText("");
                                return;
                            } else {
                                chietKhau = Integer.parseInt(edChietKhau.getText().toString());
                                tvChietKhau.setText("" + edChietKhau.getText().toString() + " VNĐ");
                                tvTongTien.setText("" + (tongTien - Integer.parseInt(edChietKhau.getText().toString())) + " VNĐ");
                                tienFinal = (tongTien - Integer.parseInt(edChietKhau.getText().toString()));
                            }
                        } else {
                            if (Integer.parseInt(edChietKhau.getText().toString()) < 0 || Integer.parseInt(edChietKhau.getText().toString()) > 100) {
                                Toast.makeText(DonHangActivity.this, "Chiết khẩu phải từ 0-100%", Toast.LENGTH_SHORT).show();
                                edChietKhau.setText("");
                                return;
                            } else {
                                tvChietKhau.setText("" + edChietKhau.getText().toString() + " %");
                                tvTongTien.setText("" + (tongTien - (tongTien * (Double.parseDouble(edChietKhau.getText().toString()) / 100))) + " VNĐ");
                                tienFinal = (int) (tongTien - (tongTien * (Double.parseDouble(edChietKhau.getText().toString()) / 100)));
                                chietKhau = (int) (tongTien * (Double.parseDouble(edChietKhau.getText().toString()) / 100));
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

    private void getTongTien() {
        for (int i = 0; i < MatHangActivity.gioHangList.size(); i++) {
            tongTien += (MatHangActivity.gioHangList.get(i).getGia() * MatHangActivity.gioHangList.get(i).getSoLuong());
        }
        tvTongTien.setText("" + tongTien + " VNĐ");
        tienFinal = tongTien;
    }

    private void xoaDonHang() {
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
                        DonHangAdapter donHangAdapter = new DonHangAdapter(DonHangActivity.this, MatHangActivity.gioHangList);
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

    public void ThanhToanDonHang(View view) {
        String mahoadon = edMaDonHang.getText().toString();
        if(mahoadon.equalsIgnoreCase("")){
            Toast.makeText(DonHangActivity.this,"Mã đơn hàng không dược trống",Toast.LENGTH_SHORT).show();
            return;
        }
        final Dialog dialog = new Dialog(DonHangActivity.this, android.R.style.Theme);
        dialog.setContentView(R.layout.dialog_thanh_toan);
        dialog.show();
        final TextView tvTongTien = (TextView) dialog.findViewById(R.id.tvTongTien);
        final EditText edTienTra = (EditText) dialog.findViewById(R.id.edThanhToan);
        final TextView tvTienTra = (TextView) dialog.findViewById(R.id.tvTienTra);
        Button btnThanhToan = (Button) dialog.findViewById(R.id.btnThanhToan);
        Button btnHuy = dialog.findViewById(R.id.btnHuyCK);
        final Date c = Calendar.getInstance().getTime();
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = df.format(c);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long chk = -1;
                HoaDonDAO hoaDonDAO = new HoaDonDAO(getApplicationContext());
                String mahoadon = edMaDonHang.getText().toString();
                try {
                    HoaDon hoaDon = new HoaDon(mahoadon, khachHang, df.parse(formattedDate));
                    if (hoaDonDAO.addHoaDon(hoaDon) > 0) {
                        SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
                        List<SanPham> sanPhamList = new ArrayList<>();
                        sanPhamList = sanPhamDAO.getAllSanPham();
                        //cập nhật lại số lượng đã bán
                        for (int i = 0; i < MatHangActivity.gioHangList.size(); i++) {
                            if (MatHangActivity.gioHangList.get(i).getMa().equalsIgnoreCase(sanPhamList.get(i).getMaSanPham())) {
                                int soluong = sanPhamList.get(i).getSoLuong() - MatHangActivity.gioHangList.get(i).getSoLuong();
                                sanPhamDAO.updateSLSanPham(soluong, sanPhamList.get(i).getMaSanPham());
                                Log.e("Số lượng sau khi bán:", "" + sanPhamList.get(i).getSoLuong());
                                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(mahoadon, MatHangActivity.gioHangList.get(i));
                                chk = hoaDonChiTietDAO.addHDCT(hoaDonChiTiet);
                            }
                        }
                        if (chk > 0) {
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            hoaDon.setKhachTra(Integer.parseInt(edTienTra.getText().toString()));
                            hoaDon.setTraLai(Integer.parseInt(tvTienTra.getText().toString()));
                            hoaDon.setChietKhau(chietKhau);
                            hoaDon.setTongTien(tongTien);
                            hoaDonDAO.updateHoaDon(hoaDon,hoaDon.getMaHD());
                            Log.d("HoaDon",""+hoaDon);
                            dialog.dismiss();
                            Intent intent = new Intent(DonHangActivity.this,MatHangActivity.class);
                            startActivity(intent);
                            MatHangActivity.gioHangList.clear();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DonHangActivity.this,"Mã hóa đơn đã tồn tại",Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        tvTongTien.setText("" + tienFinal);
        edTienTra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int tientra = Integer.parseInt(edTienTra.getText().toString())-tienFinal;
                    tvTienTra.setText("" + tientra);
                } catch (Exception e) {

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void ChonKhachHang(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_khach_hang, null);
        final ListView listView = (ListView) alertLayout.findViewById(R.id.lvKhachHang);

        KhachHangDAO khachHangDAO = new KhachHangDAO(this);
        listKH = new ArrayList<>();
        listKH = khachHangDAO.getAllKhachHang();
        KhachHangAdapter khachHangAdapter = new KhachHangAdapter(this, listKH);
        listView.setAdapter(khachHangAdapter);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setTitle("Danh Sach Khach Hàng");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                khachHang = listKH.get(i).getTen();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvKhachHang.setText(khachHang);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


}