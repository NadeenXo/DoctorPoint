package com.example.doctorpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private View mSignInLinkId;
    private EditText email, name,password,ph_number;
    private Button signUp;
    DBHelper mDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDBHelper= new DBHelper(this);
        email = (EditText) findViewById(R.id.editTextEmailAddress_signup) ;
        name = (EditText) findViewById(R.id.editTextName) ;
        password = (EditText) findViewById(R.id.editTextPassword_signup) ;
        ph_number = (EditText) findViewById(R.id.editTextPhone_phone_num) ;

        signUp = (Button) findViewById(R.id.sign_up_confirm_btn) ;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userPass = password.getText().toString();
                String userEmail = email.getText().toString();
                String userNumber = ph_number.getText().toString();

                if(userName.equals("")||userEmail.equals("")||userPass.equals("")||userNumber.equals("")) {
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                        Boolean checkuser = mDBHelper.isExistedEmail(userEmail);
                        Boolean checkEmail = mDBHelper.validateEmail(userEmail);
                        Boolean checkPass = mDBHelper.validatePassword(userPass);

                        if(checkuser==false){
                            if(checkEmail==true) {
                                if(checkPass==true) {
                                    Boolean insert = mDBHelper.insertData(userName, userPass, userEmail, userNumber);
                                    if (insert == true) {
                                        Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(SignUp.this, "at least 4 chars with 1 letter", Toast.LENGTH_SHORT).show();

                                }
                            }else {
                                Toast.makeText(SignUp.this, "Not valid email", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });

        mSignInLinkId = findViewById(R.id.signin_link);
        mSignInLinkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });
//        mSignIn.signWithApp();

    }
}