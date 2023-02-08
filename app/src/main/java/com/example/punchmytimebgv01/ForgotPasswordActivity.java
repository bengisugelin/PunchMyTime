package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {


    Button btnResetPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

    btnResetPassword = findViewById(R.id.forgotPasswordresetButton);

    // Must add function to send email to user with password retrieved from Database

    //After resetting password, the user must return to login page

    btnResetPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent goToMainPage = new Intent(ForgotPasswordActivity.this, MainActivity.class);
            startActivity(goToMainPage);
        }
    });

    }



}