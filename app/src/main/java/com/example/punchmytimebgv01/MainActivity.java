package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button LoginButton;
    TextView newUserText;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginButton = findViewById(R.id.LoginBtn);
        newUserText = findViewById(R.id.newUserTextLoginPage);
        forgotPassword = findViewById(R.id.forgotButtonTxtLoginPage);

        newUserText.setOnClickListener((View view)  ->{

            Intent goTouserRegisterActivity = new Intent(MainActivity.this,NewUserRegisterActivity.class);

            startActivity(goTouserRegisterActivity);

        });

        forgotPassword.setOnClickListener((View view) ->{

            Intent goToForgotPasswordActivity = new Intent(MainActivity.this,ForgotPasswordActivity.class);
            startActivity(goToForgotPasswordActivity);
        });


    }
}