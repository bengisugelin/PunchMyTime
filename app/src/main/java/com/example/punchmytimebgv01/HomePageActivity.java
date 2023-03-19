package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePageActivity extends AppCompatActivity {

    FloatingActionButton addnewHours;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userName =findViewById(R.id.txtHomePageGreeting);

        //import username to the homepage
        try {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("USERNAME", "mate");
            userName.setText("Hello " + username + "!");
        }catch (Exception e){
            e.printStackTrace();
        }


        addnewHours  = findViewById(R.id.addNewShift);

        addnewHours.setOnClickListener((View view)  ->{

            Intent goToAddNewHours = new Intent(HomePageActivity.this,NewWorkingHours.class);

            startActivity(goToAddNewHours);

        });
    }
}