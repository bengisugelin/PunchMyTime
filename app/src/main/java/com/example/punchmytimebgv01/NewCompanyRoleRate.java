package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCompanyRoleRate extends AppCompatActivity {

    EditText companyName;
    EditText role;
    EditText hourlyRate;
    Button AddCoRoRaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_company_role_rate);

        companyName = findViewById(R.id.editTextNewCompName);
        role = findViewById(R.id.editTextNewRole);
        hourlyRate=findViewById(R.id.editTextNewRate);
        AddCoRoRaButton = findViewById(R.id.btnNeCoRoRaAdd);


        //getting username
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "mate");

        AddCoRoRaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CompanyModel companyModel = new CompanyModel(username,
                        companyName.getText().toString(),
                        role.getText().toString(),
                        Double.parseDouble(hourlyRate.getText().toString()));

                //databahelper class
                DatabaseHelper punchMyTimeDB = new DatabaseHelper(NewCompanyRoleRate.this);
                boolean success = punchMyTimeDB.addCompany(companyModel);



                Intent goBackToNewWorkingHours = new Intent(NewCompanyRoleRate.this, NewWorkingHours.class);
                startActivity(goBackToNewWorkingHours);
            }
        });

    }
}