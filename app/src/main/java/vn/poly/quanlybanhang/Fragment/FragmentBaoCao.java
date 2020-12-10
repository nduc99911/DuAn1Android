package vn.poly.quanlybanhang.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.duan1android.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;

import vn.poly.quanlybanhang.Adapter.HoaDonAdapter;
import vn.poly.quanlybanhang.Database.HoaDonDAO;


public class FragmentBaoCao extends Fragment {
    TextView tvSoHoaDon, tvGiaTriHoaDon, tvTienBan, tvTienVon, tvLoaiLoc;
    TextView tvLoiNhuan, tvDoanhThu, tvLuuBoLoc;
    ImageView imgBoLoc;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    HoaDonDAO hoaDonDAO;
    private LineChart mChart;
    RadioButton rdoTatCa, rdoHomNay, rdoHomQua, rdoTuanNay, rdoTuanTruoc, rdoThangNay, rdoThangTruoc, rdoTatCaHd, rdoChuaThanhToan, rdoDaThanhToan;
    double thang1, thang2, thang3, thang4, thang5, thang6, thang7, thang8, thang9, thang10, thang11, thang12;
    String time;
    double doanhThu;
    public FragmentBaoCao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bao_cao, container, false);
        anhXaView(view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
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
        thang1 = hoaDonDAO.getDoanhThuTheoThang("01");
        thang2 = hoaDonDAO.getDoanhThuTheoThang("02");
        thang3 = hoaDonDAO.getDoanhThuTheoThang("03");
        thang4 = hoaDonDAO.getDoanhThuTheoThang("04");
        thang5 = hoaDonDAO.getDoanhThuTheoThang("05");
        thang6 = hoaDonDAO.getDoanhThuTheoThang("06");
        thang7 = hoaDonDAO.getDoanhThuTheoThang("07");
        thang8 = hoaDonDAO.getDoanhThuTheoThang("08");
        thang9 = hoaDonDAO.getDoanhThuTheoThang("09");
        thang10 = hoaDonDAO.getDoanhThuTheoThang("10");
        thang11 = hoaDonDAO.getDoanhThuTheoThang("11");
        thang12 = hoaDonDAO.getDoanhThuTheoThang("12");
        bieuDo();
        getBaoCao();
        //chọn bộ lọc
        imgBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.loc_bao_cao_dialog);
                dialog.show();
                anhXaViewDia(dialog);
                tvLuuBoLoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tvLoaiLoc.setText(luuChonTime());
                        getBaoCao();
                        dialog.dismiss();
                    }
                });

            }
        });
        //
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
                    case R.id.nav_thoat:
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                }
                return false;
            }
        });
    }

    public void anhXaView(View view) {
        tvSoHoaDon = view.findViewById(R.id.tvSoHoaDon);
        tvGiaTriHoaDon = view.findViewById(R.id.tvGiaTriHoaDon);
        tvTienBan = view.findViewById(R.id.tvTienBan);
        tvTienVon = view.findViewById(R.id.tvTienVon);
        tvLoiNhuan = view.findViewById(R.id.btnLoiNhuan);
        tvDoanhThu = view.findViewById(R.id.btnDoanhThu);
        imgBoLoc = view.findViewById(R.id.imgBoLocBaoCao);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_bao_cao);
        drawerLayout = view.findViewById(R.id.drawerLayoutBaoCao);
        mChart = view.findViewById(R.id.combinedChart);
        hoaDonDAO = new HoaDonDAO(getContext());
        tvLoaiLoc = view.findViewById(R.id.tvLoaiLocBC);
        tvLuuBoLoc = view.findViewById(R.id.tvLuuBoLocHD);
        navigationView = view.findViewById(R.id.NavigationViewBaoCao);
    }


    private void getBaoCao() {
        time = tvLoaiLoc.getText().toString();
        doanhThu = hoaDonDAO.getDoanhThu(time);
        tvDoanhThu.setText("" + Math.round(doanhThu) + "\nDoanh thu");
        tvGiaTriHoaDon.setText("" + Math.round(doanhThu) + " VNĐ");
        tvTienBan.setText("" + Math.round(doanhThu) + " VNĐ");
        tvSoHoaDon.setText("" + hoaDonDAO.getSoHoaDon(time));
        tvTienVon.setText("" + hoaDonDAO.getSoTienVon(time) + " VNĐ");
        tvLoiNhuan.setText("" + (Math.round(doanhThu) - hoaDonDAO.getSoTienVon(time)) + "\nLợi nhuận");
    }

    public String luuChonTime() {
        String luaChon = null;
        if (rdoTatCa.isChecked()) {
            luaChon = "Tất cả";
        } else if (rdoHomQua.isChecked()) {
            luaChon = "Hôm qua";
        } else if (rdoHomNay.isChecked()) {
            luaChon = "Hôm nay";
        } else if (rdoThangNay.isChecked()) {
            luaChon = "Tháng này";
        } else if (rdoThangTruoc.isChecked()) {
            luaChon = "Tháng trước";
        } else if (rdoTuanTruoc.isChecked()) {
            luaChon = "Tuần trước";
        } else if (rdoTuanNay.isChecked()) {
            luaChon = "Tuần này";
        }
        return luaChon;
    }


    public void anhXaViewDia(Dialog dialog) {
        rdoTatCa = dialog.findViewById(R.id.radTatCaThoiGianBC);
        rdoHomNay = dialog.findViewById(R.id.radHomNayBC);
        rdoHomQua = dialog.findViewById(R.id.radHomQuaBC);
        rdoTuanNay = dialog.findViewById(R.id.radTuanNayBC);
        rdoTuanTruoc = dialog.findViewById(R.id.radTuanTruocBC);
        rdoThangNay = dialog.findViewById(R.id.radThangNayBC);
        rdoThangTruoc = dialog.findViewById(R.id.radThangTruocBC);
        tvLuuBoLoc = dialog.findViewById(R.id.tvLuuBoLocHD);

    }

    private void bieuDo() {
        LineDataSet lineDataSet = new LineDataSet(dataValue(), "Data set 1");
//        LineDataSet lineDataSet1=new LineDataSet(dataValue1(),"Data set 2" );
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
//        dataSets.add(lineDataSet1);

        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.RED);

        mChart.setBackgroundColor(Color.parseColor("#2c3c49"));
        mChart.setNoDataText("No Data");
        mChart.setDrawBorders(true);
        mChart.setBorderColor(Color.parseColor("#37f1ec"));

        Description description = new Description();
        description.setText("Tháng");
        description.setTextSize(15);
        mChart.setDescription(description);

        Legend legend = mChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(15);
        legend.setTextColor(Color.RED);

        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();

    }

    private ArrayList<Entry> dataValue() {

        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(1, (float) thang1));
        data.add(new Entry(2, (float) thang2));
        data.add(new Entry(3, (float) thang3));
        data.add(new Entry(4, (float) thang4));
        data.add(new Entry(5, (float) thang5));
        data.add(new Entry(6, (float) thang6));
        data.add(new Entry(7, (float) thang7));
        data.add(new Entry(8, (float) thang8));
        data.add(new Entry(9, (float) thang9));
        data.add(new Entry(10, (float) thang10));
        data.add(new Entry(11, (float) thang11));
        data.add(new Entry(12, (float) thang12));
        return data;
    }

    private ArrayList<Entry> dataValue1() {
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0, 10));
        data.add(new Entry(2, 74));
        data.add(new Entry(3, 20));
        data.add(new Entry(8, 80));
        data.add(new Entry(9, 40));
        data.add(new Entry(5, 80));
        return data;
    }

}