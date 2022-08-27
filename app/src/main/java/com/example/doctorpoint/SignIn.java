package com.example.doctorpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signUpWithApp();

    }


    private void signUpWithApp() {
        View facebookV = findViewById(R.id.facebookimageView);
        View twV = findViewById(R.id.twitterimageView);
        View googleV = findViewById(R.id.googleimageView);
        View instV = findViewById(R.id.instaimageView);
        facebookV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://facebook.com"));
                startActivity(intent);
            }
        });
        twV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com"));
                startActivity(intent);
            }
        });
        googleV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://google.com"));
                startActivity(intent);
            }
        });
        instV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://instagram.com"));
                startActivity(intent);
            }
        });
    }
}