package vn.poly.quanlybanhang.Fragment;

import android.app.Dialog;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;

import vn.poly.quanlybanhang.Adapter.HoaDonAdapter;
import vn.poly.quanlybanhang.Database.HoaDonDAO;


public class FragmentBaoCao extends Fragment {
    TextView tvSoHoaDon,tvGiaTriHoaDon,tvTienBan,tvTienVon,tvLoaiLoc,tvTimeLoaiLoc;
    TextView tvLoiNhuan, tvDoanhThu,tvLuuBoLoc;
    ImageView imgBoLoc;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    HoaDonDAO hoaDonDAO;
    private LineChart mChart;
    RadioButton rdoTatCa, rdoHomNay, rdoHomQua, rdoTuanNay, rdoTuanTruoc, rdoThangNay, rdoThangTruoc, rdoTatCaHd, rdoChuaThanhToan, rdoDaThanhToan;
    double thang1,thang2,thang3,thang4,thang5,thang6,thang7,thang8,thang9,thang10,thang11,thang12;
    public FragmentBaoCao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bao_cao, container, false);
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
        thang1=hoaDonDAO.getDoanhThuTheoThang("01");
        thang2=hoaDonDAO.getDoanhThuTheoThang("02");
        thang3=hoaDonDAO.getDoanhThuTheoThang("03");
        thang4=hoaDonDAO.getDoanhThuTheoThang("04");
        thang5=hoaDonDAO.getDoanhThuTheoThang("05");
        thang6=hoaDonDAO.getDoanhThuTheoThang("06");
        thang7=hoaDonDAO.getDoanhThuTheoThang("07");
        thang8=hoaDonDAO.getDoanhThuTheoThang("08");
        thang9=hoaDonDAO.getDoanhThuTheoThang("09");
        thang10=hoaDonDAO.getDoanhThuTheoThang("10");
        thang11=hoaDonDAO.getDoanhThuTheoThang("11");
        thang12=hoaDonDAO.getDoanhThuTheoThang("12");
        Log.d("Tháng",""+thang1);
        bieuDo();
        getBaoCao();
        //chọn bộ lọc
        imgBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_bo_loc);
                dialog.show();
                anhXaViewDia(dialog);
                tvLuuBoLoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            tvLoaiLoc.setText( luuChonHoaDon());
                            tvTimeLoaiLoc.setText( luuChonTime());
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    public void anhXaView(View view){
        tvSoHoaDon = view.findViewById(R.id.tvSoHoaDon);
        tvGiaTriHoaDon = view.findViewById(R.id.tvGiaTriHoaDon);
        tvTienBan = view.findViewById(R.id.tvTienBan);
        tvTienVon = view.findViewById(R.id.tvTienVon);
        tvLoaiLoc = view.findViewById(R.id.tvLoaiLocBC);
        tvLoiNhuan = view.findViewById(R.id.btnLoiNhuan);
        tvDoanhThu = view.findViewById(R.id.btnDoanhThu);
        imgBoLoc = view.findViewById(R.id.imgBoLocBaoCao);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_bao_cao);
        drawerLayout = view.findViewById(R.id.drawerLayoutBaoCao);
        mChart =  view.findViewById(R.id.combinedChart);
        hoaDonDAO = new HoaDonDAO(getContext());
        tvTimeLoaiLoc = view.findViewById(R.id.tvTimeLoaiLocBC);
    }
    private void getBaoCao(){
        tvDoanhThu.setText(""+Math.round(hoaDonDAO.getDoanhThu())+ "\nDoanh thu");
        tvGiaTriHoaDon.setText(""+Math.round(hoaDonDAO.getDoanhThu())+ " VNĐ");
        tvTienBan.setText(""+Math.round(hoaDonDAO.getDoanhThu())+ " VNĐ");
        tvSoHoaDon.setText(""+hoaDonDAO.getSoHoaDon());
        tvTienVon.setText(""+hoaDonDAO.getSoTienVon()+ " VNĐ");
        tvLoiNhuan.setText(""+(Math.round(hoaDonDAO.getDoanhThu())-hoaDonDAO.getSoTienVon())+ "\nLợi nhuận");
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

    public String luuChonHoaDon() {
        String luaChon = null;
        if (rdoTatCaHd.isChecked()) {
            luaChon = "Tất cả";
        } else if (rdoChuaThanhToan.isChecked()) {
            luaChon = "Chưa Thanh Toán";
        } else if (rdoDaThanhToan.isChecked()) {
            luaChon = "Đã thanh toán";
        }
        return luaChon;
    }
    public void anhXaViewDia(Dialog dialog) {
        rdoTatCa = dialog.findViewById(R.id.radTatCaThoiGian);
        rdoHomNay = dialog.findViewById(R.id.radHomNay);
        rdoHomQua = dialog.findViewById(R.id.radHomQua);
        rdoTuanNay = dialog.findViewById(R.id.radTuanNay);
        rdoTuanTruoc = dialog.findViewById(R.id.radTuanTruoc);
        rdoThangNay = dialog.findViewById(R.id.radThangNay);
        rdoThangTruoc = dialog.findViewById(R.id.radThangTruoc);
        rdoTatCaHd = dialog.findViewById(R.id.radTatCaHoaDon);
        rdoChuaThanhToan = dialog.findViewById(R.id.radChuaThanhToan);
        rdoDaThanhToan = dialog.findViewById(R.id.radDaThanhToan);
        tvLuuBoLoc = dialog.findViewById(R.id.tvLuuBoLocHD);

    }
    private void bieuDo(){
        LineDataSet lineDataSet=new LineDataSet(dataValue(),"Data set 1" );
//        LineDataSet lineDataSet1=new LineDataSet(dataValue1(),"Data set 2" );
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);
//        dataSets.add(lineDataSet1);

        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.RED);

        mChart.setBackgroundColor(Color.parseColor("#2c3c49"));
        mChart.setNoDataText("No Data");
        mChart.setDrawBorders(true);
        mChart.setBorderColor(Color.parseColor("#37f1ec"));

        Description description=new Description();
        description.setText("Tháng");
        description.setTextSize(15);
        mChart.setDescription(description);

        Legend legend=mChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(15);
        legend.setTextColor(Color.RED);

        LineData data=new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();

    }

    private ArrayList<Entry> dataValue(){

        ArrayList <Entry> data=new ArrayList<>();
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
    private ArrayList<Entry> dataValue1(){
        ArrayList <Entry> data=new ArrayList<>();
        data.add(new Entry(0,10));
        data.add(new Entry(2,74));
        data.add(new Entry(3,20));
        data.add(new Entry(8,80));
        data.add(new Entry(9,40));
        data.add(new Entry(5,80));
        return data;
    }

}