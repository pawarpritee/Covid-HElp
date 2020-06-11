package com.example.covidhelp.splashscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.covidhelp.R;

public class Splash3 extends AppCompatActivity {
    private ImageView splashicon;
    private static int splashtimeout=1500;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        splashicon = findViewById(R.id.sp3);

        if(getIntent()!=null)
            data=getIntent().getStringExtra("data");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash3.this,Splash4.class);
                intent.putExtra("data",data);
                startActivity(intent);
                finish();
            }
        },splashtimeout);
        Animation myanime = AnimationUtils.loadAnimation(this,R.anim.mysplash);     //using animation in splash activity
        splashicon.startAnimation(myanime);

    }
}