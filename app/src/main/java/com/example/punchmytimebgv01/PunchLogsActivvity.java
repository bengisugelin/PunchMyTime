package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PunchLogsActivvity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<String> date, time, company_name;
    ArrayList<Double>  hourly_rate, total_work_hours, total_earnings;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_logs_activvity);

        recyclerView = findViewById(R.id.newLogRecyclerView);
        databaseHelper=new DatabaseHelper(PunchLogsActivvity.this);



        //to export the username to the new hours page
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "mate");
        //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        List<LogModel> logModels = databaseHelper.getLogData(username);
        Toast.makeText(this, logModels.get(0).getCompanyName(), Toast.LENGTH_SHORT).show();
        //round to 2 decimal degrees
        DecimalFormat df = new DecimalFormat("#.##");

        date = new ArrayList<>();
        time = new ArrayList<>();
        company_name = new ArrayList<>();
        hourly_rate = new ArrayList<>();
        total_work_hours = new ArrayList<>();
        total_earnings = new ArrayList<>();

        for (int i=0; i<logModels.size(); i++){

            date.add(logModels.get(i).getDate());
            time.add(logModels.get(i).getBeginning_hour() + "-" + logModels.get(i).getEnding_hour());
            company_name.add(logModels.get(i).getCompanyName());
            hourly_rate.add(Double.valueOf(String.format("%.2f", logModels.get(i).getHourlyRate())));
            total_work_hours.add(logModels.get(i).getHoursWorked());
            total_earnings.add(Double.valueOf(String.format("%.2f",logModels.get(i).getHourlyRate() * logModels.get(i).getHoursWorked())));
        }


        customAdapter = new CustomAdapter(PunchLogsActivvity.this,
                date, time,company_name,hourly_rate,total_work_hours,total_earnings);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PunchLogsActivvity.this));



    }


}