package com.example.doctorpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashSceen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);
        new Handler().postDelayed((Runnable) () -> {
            Intent intent =new Intent(SplashSceen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        ,3000);

    }
}