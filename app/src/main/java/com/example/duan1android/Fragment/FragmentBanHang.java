package com.example.duan1android.Fragment;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.duan1android.R;


public class FragmentBanHang extends Fragment {
    androidx.appcompat.widget.Toolbar toolbar;

    public FragmentBanHang() {
        // Required empty public constructor
    }



    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    String danhSachLC[] = {"Theo tên","Giá ↑","Giá ↓"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_ban_hang, container, false);
       toolbar=(Toolbar)view.findViewById(R.id.toolbar_ban_hang);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,danhSachLC);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocDanhSach.setAdapter(adapter);
    }
    public void anhXaView(View view){
        edTimKiem = view.findViewById(R.id.edTimKiemMatHang);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);
    }
}