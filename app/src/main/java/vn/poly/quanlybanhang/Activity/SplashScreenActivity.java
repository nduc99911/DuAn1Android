package vn.poly.quanlybanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.quanlybanhang.GioiThieuActivity;
import com.example.duan1android.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    private ImageView imglogo,fly;

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
        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 4000) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
                Intent intent = new Intent(SplashScreenActivity.this, GioiThieuActivity.class);
                startActivity(intent);
            }
        };
        mThread.start();
    }
}