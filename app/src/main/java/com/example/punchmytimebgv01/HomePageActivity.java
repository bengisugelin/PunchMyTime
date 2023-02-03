package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button addnewHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        addnewHours  = findViewById(R.id.btnAddNewHomePage);

        addnewHours.setOnClickListener((View view)  ->{

            Intent goToAddNewHours = new Intent(HomePageActivity.this,NewWorkingHours.class);

            startActivity(goToAddNewHours);

        });
    }
}