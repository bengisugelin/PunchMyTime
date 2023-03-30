package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class NewWorkingHours extends AppCompatActivity {

    //properties of drawer

    TextView NewCoRoRaTxt;
    Button SubmitNewHour;


    Spinner spinnerSelectCompany;

    EditText startOfpunch, endOfPunch;
    TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_working_hours);

        NewCoRoRaTxt = findViewById(R.id.txtNewCoRoRa);
        SubmitNewHour = findViewById(R.id.btnAddNewHours);
        spinnerSelectCompany = findViewById(R.id.spnnerEmployerName);

        //to choose the time for "from" section
        startOfpunch=findViewById(R.id.editTxtTimeNewHours);
        startOfpunch.requestFocus();
        startOfpunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerFrom();
            }
        });
        //choose time for "to" section
        endOfPunch=findViewById(R.id.editTxtTimeNewHoursTo);
        endOfPunch.requestFocus();
        endOfPunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerTo();
            }
        });


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

    private void openTimePickerFrom() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        timePickerDialog= new TimePickerDialog(NewWorkingHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minuteofHour) {
                String hour = "" + hourofDay;
                String minute = "" + minuteofHour;

                if(hourofDay<10){
                    hour = "0" + hour;
                }

                if(minuteofHour<10){
                    minute = "0" + minute;
                }

                String time = hour + ":" + minute;
                startOfpunch.setText(time);

            }
        },hour,minute,false);

        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }

    private void openTimePickerTo() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        timePickerDialog= new TimePickerDialog(NewWorkingHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minuteofHour) {
                String hour = "" + hourofDay;
                String minute = "" + minuteofHour;

                if(hourofDay<10){
                    hour = "0" + hour;
                }

                if(minuteofHour<10){
                    minute = "0" + minute;
                }

                String time = hour + ":" + minute;
                endOfPunch.setText(time);

            }
        },hour,minute,false);

        timePickerDialog.setTitle("Select End Time");
        timePickerDialog.show();
    }
}