package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.duan1android.Fragment.FragmentHoaDon;
import com.example.duan1android.MatHangActivity;
import com.example.duan1android.R;

public class BoLocActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_loc);
        toolbar = findViewById(R.id.toolbar_bo_loc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    //luuw bộ lọc
    public void ToolBarBoLocLuu(View view){
//        FragmentHoaDon fragment = new FragmentHoaDon(); // replace your custom fragment class
//        Bundle bundle = new Bundle();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        bundle.putString("key","value"); // use as per your need
//        fragment.setArguments(bundle);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.replace(R.id.id,fragment);
//        fragmentTransaction.commit();



    }

public String myData(){
        return "Duc";
}

}