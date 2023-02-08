package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewUserRegisterActivity extends AppCompatActivity {

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_register);

        btnRegister = findViewById(R.id.btnRegisterButton);

        //After inputing data the button must send infprmation to Database
        // and return to HomePage

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomePage = new Intent(NewUserRegisterActivity.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });

    }
}