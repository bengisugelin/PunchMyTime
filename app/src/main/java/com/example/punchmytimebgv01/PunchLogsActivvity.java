package com.example.punchmytimebgv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
        customAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(PunchLogsActivvity.this));


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position =viewHolder.getAdapterPosition();

            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("USERNAME", "mate");
            List<LogModel> logModels = databaseHelper.getLogData(username);
            LogModel clickedlogmodel = logModels.get(position);
           // Toast.makeText(PunchLogsActivvity.this, clickedlogmodel.getUsername(), Toast.LENGTH_SHORT).show();
           // Toast.makeText(PunchLogsActivvity.this, clickedlogmodel.getDate(), Toast.LENGTH_SHORT).show();

            switch (direction){
                case ItemTouchHelper.LEFT:
                    AlertDialog.Builder builder = new AlertDialog.Builder(PunchLogsActivvity.this);
                    builder.setTitle("Delete the Punch");
                    builder.setMessage("You are deleting the work punched in" + clickedlogmodel.getDate() + "." );
                    builder.setIcon(R.drawable.baseline_delete_24);

                    builder.setCancelable(false);
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            databaseHelper.deleteLog(clickedlogmodel);
                            customAdapter.notifyItemRemoved(position);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customAdapter = new CustomAdapter(PunchLogsActivvity.this,
                                    date, time,company_name,hourly_rate,total_work_hours,total_earnings);
                            recyclerView.setAdapter(customAdapter);
                        }
                    });
                    builder.show();



                    break;
                case ItemTouchHelper.RIGHT:

                    break;


            }

        }
    };


}