package com.example.duan1android.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentBaoCao extends Fragment {
    TextView tvSoHoaDon,tvGiaTriHoaDon,tvTienBan,tvTienVon,tvBoLoc;
    Button btnLoiNhuan,btnDoanhThu;
    ImageView imgBoLoc;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    private LineChart mChart;
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

        LineDataSet lineDataSet=new LineDataSet(dataValue(),"Data set 1" );
        LineDataSet lineDataSet1=new LineDataSet(dataValue1(),"Data set 2" );
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);

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

    public void anhXaView(View view){
        tvSoHoaDon = view.findViewById(R.id.tvSoHoaDon);
        tvGiaTriHoaDon = view.findViewById(R.id.tvGiaTriHoaDon);
        tvTienBan = view.findViewById(R.id.tvTienBan);
        tvTienVon = view.findViewById(R.id.tvTienVon);
        tvBoLoc = view.findViewById(R.id.tvBoLocBaoCao);
        btnLoiNhuan = view.findViewById(R.id.btnLoiNhuan);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        imgBoLoc = view.findViewById(R.id.imgBoLocBaoCao);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_bao_cao);
        drawerLayout = view.findViewById(R.id.drawerLayoutBaoCao);
        mChart =  view.findViewById(R.id.combinedChart);
    }
    private ArrayList<Entry> dataValue(){
        ArrayList <Entry> data=new ArrayList<>();
        data.add(new Entry(0,20));
        data.add(new Entry(1,24));
        data.add(new Entry(2,2));
        data.add(new Entry(3,10));
        data.add(new Entry(4,30));
        data.add(new Entry(5,50));
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