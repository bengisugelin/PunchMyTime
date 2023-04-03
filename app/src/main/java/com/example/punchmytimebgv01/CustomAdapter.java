package com.example.punchmytimebgv01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList date, time, company_name, hourly_rate, total_work, total_earnings;
    CustomAdapter(Context context,
                  ArrayList date,
                  ArrayList time,
                  ArrayList company_name,
                  ArrayList hourly_rate,
                  ArrayList total_work,
                  ArrayList total_earnings){

        this.context = context;
        this.date = date;
        this.time=time;
        this.company_name = company_name;
        this.hourly_rate=hourly_rate;
        this.total_work=total_work;
        this.total_earnings=total_earnings;

    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.log_recylerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.txtDateofWork.setText(String.valueOf(date.get(position)));
        holder.txtTimeofWork.setText(String.valueOf(time.get(position)));
        holder.txtCompanyName.setText(String.valueOf(company_name.get(position)));
        holder.txtHourlyRate.setText(String.valueOf(hourly_rate.get(position)));
        holder.txtTotalWorkHours.setText(String.valueOf(total_work.get(position)));
        holder.TotalEarning.setText(String.valueOf(total_earnings.get(position)));
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtDateofWork,txtTimeofWork,txtCompanyName,txtHourlyRate,txtTotalWorkHours, TotalEarning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateofWork = itemView.findViewById(R.id.txtDateofWork);
            txtTimeofWork = itemView.findViewById(R.id.txtTimeofWork);
            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            txtHourlyRate=itemView.findViewById(R.id.txtHourlyRate);
            txtTotalWorkHours=itemView.findViewById(R.id.txtTotalWorkHours);
            TotalEarning=itemView.findViewById(R.id.TotalEarning);
        }
    }
}
