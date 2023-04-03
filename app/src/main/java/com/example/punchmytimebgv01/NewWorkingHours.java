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
    Button SubmitNewHour, GoToLogHistory;


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
        GoToLogHistory = findViewById(R.id.btnGoToLogHistory);

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


        //to import the username to the new hours page
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "mate");
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        bundle.putString("USERNAME", username);

        //get company database info to create



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


        String SelectedCompanyName = "";
        double SelectedHourlyRate=0;


        spinnerSelectCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String spinnerValue= adapterView.getItemAtPosition(i).toString();
                String[] values = spinnerValue.split(",");
                String SelectedCompanyName = values[0];
                String Role = values[1];
                double hourlyrate = Double.parseDouble(values[2]);
                Toast.makeText(NewWorkingHours.this, "You have selected " + SelectedCompanyName + " with the role of " + Role, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        SubmitNewHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerValue = spinnerSelectCompany.getSelectedItem().toString();
                String[] values = spinnerValue.split(",");
                String SelectedCompanyName = values[0];
                double hourlyrate = Double.parseDouble(values[2]);

                //calculate hours worked:

                String[] startvalues = startOfpunch.getText().toString().split(":");
                double startHour = Double.parseDouble(startvalues[0]);
                double convertmintohour = Double.parseDouble(startvalues[1])/60;
                double sumStart = startHour+ convertmintohour;

                String [] endValues = endOfPunch.getText().toString().split((":"));
                double endHour = Double.parseDouble(endValues[0]);
                double convertmintohourEnd = Double.parseDouble(endValues[1])/60;
                double sumend = endHour+convertmintohourEnd;


                double hoursWorked = sumend-sumStart;

               LogModel logModel = new LogModel(username,
                       SelectedCompanyName,
                       hourlyrate,
                       dateofPunch.getText().toString(),
                       startOfpunch.getText().toString(),
                       endOfPunch.getText().toString(),
                       hoursWorked );

                //databahelper class
                DatabaseHelper punchMyTimeDB = new DatabaseHelper(NewWorkingHours.this);
                Boolean success = punchMyTimeDB.addNewLog(logModel);

                Toast.makeText(NewWorkingHours.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            }
        }); // end of submitNewHour

        GoToLogHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTotheLogHistory = new Intent(NewWorkingHours.this, PunchLogsActivvity.class);
               // bundle.putString("USERNAME", username);
                goTotheLogHistory.putExtras(bundle);
                startActivity(goTotheLogHistory);
            }
        });
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


                String date= year + "-" + strMonth + "-" + strDayOfMonth;
                dateofPunch.setText(date );
            }
        },year, month,day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }//end of opendatepicker


}