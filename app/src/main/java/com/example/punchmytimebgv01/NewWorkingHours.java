package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

    EditText startOfpunch, endOfPunch, dateofPunch;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;


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

        //choose date for the working hours entered

        dateofPunch = findViewById(R.id.editTextTextDateInput);
        dateofPunch.requestFocus();
        dateofPunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
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
    }//end of openTimePickerFrom

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
    }//end of openTimePicker To


    private void openDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(NewWorkingHours.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                int month = monthOfYear + 1;
                String strMonth = ""+ month;
                String strDayOfMonth= "" + dayOfMonth;

                if (month<10){
                    strMonth="0"+strMonth;
                }

                if (dayOfMonth<10){
                    strDayOfMonth="0"+strDayOfMonth;
                }

                String date= strDayOfMonth + "/" + strMonth + "/" + year;
                dateofPunch.setText(date );
            }
        },year, month,day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }//end of opendatepicker
}