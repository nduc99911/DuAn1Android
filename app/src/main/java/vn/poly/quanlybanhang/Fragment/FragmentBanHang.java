package vn.poly.quanlybanhang.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.quanlybanhang.Activity.DonHangActivity;
import vn.poly.quanlybanhang.Activity.HoaDonChiTiet;
import vn.poly.quanlybanhang.Activity.MatHangActivity;
import vn.poly.quanlybanhang.Adapter.SanPhamAdapter;
import vn.poly.quanlybanhang.Database.SanPhamDAO;
import vn.poly.quanlybanhang.Model.GioHang;
import vn.poly.quanlybanhang.Model.HoaDon;
import vn.poly.quanlybanhang.Model.SanPham;
import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FragmentBanHang extends Fragment {


    public FragmentBanHang() {
    }

    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    String[] danhSachLC = {"Theo tên", "Giá ↑", "Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    TextView tvNull;
    int soLuong;
    static int tong = 0;
    TextView tvSoLuongBanHang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ban_hang, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_ban_hang);
        imageView = view.findViewById(R.id.imgBanHang);
        navigationView = view.findViewById(R.id.NavigationViewBanHang);
        drawerLayout = view.findViewById(R.id.drawerLayoutBanHang);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        //onClick item navigatinonView
        navigationView.getMenu().getItem(0).setIcon(R.drawable.ic_delete);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_sendEmail:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");
                        intent.putExtra(Intent.EXTRA_EMAIL, "nduc99911@gmail.com");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "Phản Hồi Ứng Dụng");
                        startActivity(Intent.createChooser(intent, "Phản Hồi"));
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DonHangActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        doDuLieu();
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danhSachLC);
        spnLocDanhSach.setAdapter(adapter);

        timKiem();
        themSanPhamVaoGio();
    }
    private void themSanPhamVaoGio(){
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if(list.get(i).getSoLuong()<=0){
                    Toast.makeText(getContext(),"Mặt hàng này đã hết",Toast.LENGTH_SHORT).show();
                    return;
                }
                final Dialog dialog = new Dialog(getContext(),android.R.style.Theme);
                dialog.setContentView(R.layout.chon_so_luong_dialog);
                dialog.show();
                TextView tvTen = dialog.findViewById(R.id.tvTenSanPhamSL);
                TextView tvSoLuongSP = dialog.findViewById(R.id.tvSoLuongSanPhamSL);
                TextView tvGia = dialog.findViewById(R.id.tvGiaSanPhamSL);
                ImageView imgCong = dialog.findViewById(R.id.imgCongSoLuong);
                ImageView imgTru = dialog.findViewById(R.id.imgTruSoLuong);
                Button btnOk = dialog.findViewById(R.id.btnThemVaoGio);
                Button btnHuy = dialog.findViewById(R.id.btnHuyThemVaoGio);
                soLuong = 1;
                //số lượng là số lượng riêng của mỗi item  => settext cho tvSoLuong ben trong dialog
                // tổng là tổng số lượng của tất cả các item được chọn => settext cho tv giỏ hàng ngoài fragment
                final TextView tvSoLuongMua = dialog.findViewById(R.id.tvSoLuongChonMua);
                tvTen.setText(""+list.get(i).getTen());
                tvSoLuongSP.setText("Còn :"+list.get(i).getSoLuong());
                tvGia.setText(""+list.get(i).getGiaBan()+ " VNĐ");
                imgCong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(soLuong>=list.get(i).getSoLuong()){
                            soLuong=list.get(i).getSoLuong();
                            tvSoLuongMua.setText(""+soLuong);
                            Toast.makeText(getContext(),"Đã đạt giới hạn số lượng",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            soLuong++;
                            tvSoLuongMua.setText("" + soLuong);
                        }
                    }
                });
                imgTru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(soLuong<=0){
                            soLuong = 0;
                        }else {
                            soLuong--;

                        }
                        tvSoLuongMua.setText(""+soLuong);
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tong+=soLuong;
                        if(tong>0) {
                            tvSoLuongBanHang.setText("" + tong);
                            tvSoLuongBanHang.setVisibility(View.VISIBLE);
                            boolean chk = false;
                            if(MatHangActivity.gioHangList.size()>0){
                                for(int j = 0;j<MatHangActivity.gioHangList.size();j++){
                                    if(MatHangActivity.gioHangList.get(j).getMa() == list.get(i).getMaSanPham()){
                                        MatHangActivity.gioHangList.get(j).setSoLuong(MatHangActivity.gioHangList.get(j).getSoLuong()+soLuong);
                                        chk=true;
                                    }
                                }
                                if(chk==false){
                                    SanPham sanPham = list.get(i);
                                    GioHang gioHang = new GioHang();
                                    gioHang.setMa(sanPham.getMaSanPham());
                                    gioHang.setTen(sanPham.getTen());
                                    gioHang.setGia(sanPham.getGiaBan());
                                    gioHang.setSoLuong(soLuong);
                                    MatHangActivity.gioHangList.add(gioHang);
                                }
                            }else{
                                SanPham sanPham = list.get(i);
                                GioHang gioHang = new GioHang();
                                gioHang.setMa(sanPham.getMaSanPham());
                                gioHang.setTen(sanPham.getTen());
                                gioHang.setGia(sanPham.getGiaBan());
                                gioHang.setSoLuong(soLuong);
                                MatHangActivity.gioHangList.add(gioHang);
                            }



                        }else {
                            tvSoLuongBanHang.setVisibility(View.INVISIBLE);
                        }
                        list.get(i).setSoLuong(list.get(i).getSoLuong()-soLuong);
                        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getContext(),list);
                        lvList.setAdapter(sanPhamAdapter);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void timKiem(){
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<SanPham> list = sanPhamDAO.getAllSanPhamTheoMa(edTimKiem.getText().toString());
                SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
                lvList.setAdapter(sanPhamAdapter);
                tvNull.setVisibility(View.INVISIBLE);
                if(edTimKiem.getText().toString().equalsIgnoreCase("")){
                    doDuLieu();
                }
                if(list.size()<=0){
                    tvNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void anhXaView(View view) {
        edTimKiem = view.findViewById(R.id.edTimKiemSanPham);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);
        tvNull = view.findViewById(R.id.tvNull);
        tvSoLuongBanHang = view.findViewById(R.id.tvSoLuongBanHang);

    }

    public void doDuLieu(){
        list = sanPhamDAO.getAllSanPham();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        lvList.setAdapter(sanPhamAdapter);
    }
}