package com.example.doctorpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private View mSignupLinkId;
    private EditText email,password;
    private Button signIn;
    DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mDBHelper = new DBHelper(this);
        email = (EditText) findViewById(R.id.editTextEmailAddress) ;
        password = (EditText) findViewById(R.id.editTextPassword) ;

        signIn = (Button) findViewById(R.id.sign_in_confirm_btn) ;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userPass = password.getText().toString();
                String userEmail = email.getText().toString();

                if(userEmail.equals("")||userPass.equals(""))
                    Toast.makeText(SignIn.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = mDBHelper.checkusernamepassword(userEmail,userPass);
                    if(checkuserpass==true){
                        Toast.makeText(SignIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(SignIn.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        signWithApp();

        mSignupLinkId = (View) findViewById(R.id.createtextView); //
        mSignupLinkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void signWithApp() {
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