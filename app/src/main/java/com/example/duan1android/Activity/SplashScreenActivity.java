package com.example.duan1android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1android.LoginActivity;
import com.example.duan1android.MainActivity;
import com.example.duan1android.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    private ImageView imglogo,fly;
    private Thread mThread;
    TextView tvLoading;
    AnimationDrawable rocketAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
linearLayout=findViewById(R.id.splashScreen);
        setContentView(R.layout.activity_splash_screen);
        imglogo = (ImageView) findViewById(R.id.imageSplashScrenn);
        fly=findViewById(R.id.imgFlySplashScrenn);
        tvLoading= findViewById(R.id.tvSplashScren);
        startAnimation();
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd hhmmss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        Toast.makeText(getApplicationContext(),""+today,Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) ;
        int month= c.get(Calendar.MONTH) ;
        int date=c.get(Calendar.DATE);
        int sdk = Build.VERSION.SDK_INT;
        fly.setBackgroundResource(R.drawable.fly);
        rocketAnimation = (AnimationDrawable) fly.getBackground();
        rocketAnimation.start();

    }
    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        rotate.reset();
        translate.reset();

        imglogo.setAnimation(rotate);
        fly.setAnimation(translate);
        tvLoading.setAnimation(translate);
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 10000) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        mThread.start();
    }
}