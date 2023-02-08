package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCompanyRoleRate extends AppCompatActivity {

    Button AddCoRoRaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_company_role_rate);

        AddCoRoRaButton = findViewById(R.id.btnNeCoRoRaAdd);

        AddCoRoRaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goBackToNewWorkingHours = new Intent(NewCompanyRoleRate.this, NewWorkingHours.class);
                startActivity(goBackToNewWorkingHours);
            }
        });

    }
}