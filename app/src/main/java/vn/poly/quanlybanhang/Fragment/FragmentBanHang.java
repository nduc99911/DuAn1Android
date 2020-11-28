package vn.poly.quanlybanhang.Fragment;

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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import vn.poly.quanlybanhang.Activity.DonHangActivity;
import vn.poly.quanlybanhang.Adapter.SanPhamAdapter;
import vn.poly.quanlybanhang.Database.SanPhamDAO;
import vn.poly.quanlybanhang.Model.SanPham;
import com.example.duan1android.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentBanHang extends Fragment {


    public FragmentBanHang() {
        // Required empty public constructor
    }

    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    String danhSachLC[] = {"Theo tên", "Giá ↑", "Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    Handler handler = new Handler();
    TextView tvNull;
    TextView tvSoLuongBanHang;
    ArrayList<String> maSanPham=new ArrayList<>();
    int click = 0;
    int soluong=0;
    int j=0;
    int sl2;
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
        time();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DonHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("key",maSanPham);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        list = sanPhamDAO.getAllSanPham();

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                maSanPham.add(list.get(i).getMaSanPham());
                tvSoLuongBanHang.setVisibility(View.VISIBLE);
                click = 1 + click;
               soluong=list.get(i).getSoLuong()-1;
               tvSoLuongBanHang.setText(""+click);
               //nếu sản phẩm <0
               if(soluong<=0){
                   sanPhamDAO.deleteSanPham(list.get(i).getMaSanPham());
                   sanPhamAdapter=new SanPhamAdapter(getActivity(),list);
                   lvList.setAdapter(sanPhamAdapter);

               }
               //cập nhật lại số lượng
               String ma=list.get(i).getMaSanPham();
               String maloai=list.get(i).getMaLoai();
               String ten=list.get(i).getTen();
                String donViTinh=list.get(i).getDonViTinh();
                double giaNhap=list.get(i).getGiaNhap();
                double giaBan=list.get(i).getGiaBan();
                byte[] image=list.get(i).getImage();
                SanPham sanPham=new SanPham(ma,maloai,ten,donViTinh,soluong,giaNhap,giaBan,image);
                sanPhamDAO.updateSanPham(sanPham,ma);
               list = sanPhamDAO.getAllSanPham();
               sanPhamAdapter=new SanPhamAdapter(getActivity(),list);
               lvList.setAdapter(sanPhamAdapter);

            }
        });
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

    public void time() {

        View headerView = navigationView.getHeaderView(0);
        ImageView img = (ImageView) headerView.findViewById(R.id.image);
        TextView navUsername = (TextView) headerView.findViewById(R.id.text);
        TextView tvName = headerView.findViewById(R.id.tvNameNavi);
        ConstraintLayout constraintLayoutt = headerView.findViewById(R.id.test);
        TextView tvNgay = headerView.findViewById(R.id.tvNgay);
        final TextView tvGio = headerView.findViewById(R.id.tvGio);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int mintute = c.get(Calendar.MINUTE);
        final int second = c.get(Calendar.SECOND);
        tvNgay.setText(date + "/" + month + "/" + year);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int mintute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                tvGio.setText(hour + "h:" + mintute + "m:" + second + "s");
                handler.postDelayed(this, 0);
            }
        };
        runnable.run();

        if (hour <= 12) {
            navUsername.setText("Chào Buổi Sáng");
            constraintLayoutt.setBackgroundResource(R.drawable.troisang);
        } else if (hour > 12) {
            navUsername.setText("Chào Buổi Chiều");
        } else if (hour > 18) {
            navUsername.setText("Chào Buổi Tối");
            constraintLayoutt.setBackgroundResource(R.drawable.mattrang);
        }
    }
    public void doDuLieu(){
        list = sanPhamDAO.getAllSanPham();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        lvList.setAdapter(sanPhamAdapter);
    }
}