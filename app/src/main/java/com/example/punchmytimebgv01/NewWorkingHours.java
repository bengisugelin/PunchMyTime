package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NewWorkingHours extends AppCompatActivity {

    //properties of drawer

    TextView NewCoRoRaTxt;
    Button SubmitNewHour;

    Spinner spinnerSelectCompany;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_working_hours);

        NewCoRoRaTxt = findViewById(R.id.txtNewCoRoRa);
        SubmitNewHour = findViewById(R.id.btnAddNewHours);
        spinnerSelectCompany = findViewById(R.id.spnnerEmployerName);

        //to export the username to the new hours page
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "mate");

        NewCoRoRaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCoRoRaPage = new Intent(NewWorkingHours.this, NewCompanyRoleRate.class);

                bundle.putString("USERNAME", username);
                goToCoRoRaPage.putExtras(bundle);

                startActivity(goToCoRoRaPage);

            }
        });




        DatabaseHelper dbHelper = new DatabaseHelper(NewWorkingHours.this);

        List<CompanyModel> company = dbHelper.getCompanyData(username);
        // Toast.makeText(NewWorkingHours.this, company.get(1).getRole(), Toast.LENGTH_SHORT).show();
        ArrayAdapter<CompanyModel> adapter = new ArrayAdapter<CompanyModel>(NewWorkingHours.this, android.R.layout.simple_spinner_item,company);

        spinnerSelectCompany.setAdapter(adapter);


        spinnerSelectCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(NewWorkingHours.this,""+adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        SubmitNewHour.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent goToHomePage = new Intent(NewWorkingHours.this, HomePageActivity.class);
                                                 startActivity(goToHomePage);
                                             }
                                         }

        );


    }
}