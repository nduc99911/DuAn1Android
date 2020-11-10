package com.example.duan1android.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1android.R;


public class FragmentHoaDon extends Fragment {
    TextView tvLoaiLoc;
    ImageView imgBoLoc;
    ListView lvListHoaDon;

    public FragmentHoaDon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hoa_don, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
    }

    public void anhXaView(View view){
        tvLoaiLoc = view.findViewById(R.id.tvLoaiLoc);
        imgBoLoc = view.findViewById(R.id.imgChonLoaiLoc);
        lvListHoaDon = view.findViewById(R.id.lvListHoaDon);

    }
}