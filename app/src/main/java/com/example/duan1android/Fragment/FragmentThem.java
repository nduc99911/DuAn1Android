package com.example.duan1android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1android.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentThem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentThem extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_them, container, false);
        return view;
    }
}