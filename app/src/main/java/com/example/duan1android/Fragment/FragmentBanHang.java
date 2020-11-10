package com.example.duan1android.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1android.R;


public class FragmentBanHang extends Fragment {
    androidx.appcompat.widget.Toolbar toolbar;

    public FragmentBanHang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_ban_hang, container, false);
       toolbar=(Toolbar)view.findViewById(R.id.toolbar_ban_hang);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       return view;
    }
}