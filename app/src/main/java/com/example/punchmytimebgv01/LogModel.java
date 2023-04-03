package com.example.punchmytimebgv01;

public class LogModel {
    private String username;
    private String companyName;
    private double hourlyRate;
    private String date;
    private String beginning_hour;
    private String ending_hour;
    private double hoursWorked;

    public LogModel() {
    }

    public LogModel(String username, String companyName, double hourlyRate, String date, String beginning_hour, String ending_hour, double hoursWorked) {
        this.username = username;
        this.companyName = companyName;
        this.hourlyRate = hourlyRate;
        this.date = date;
        this.beginning_hour = beginning_hour;
        this.ending_hour = ending_hour;
        this.hoursWorked = hoursWorked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBeginning_hour() {
        return beginning_hour;
    }

    public void setBeginning_hour(String beginning_hour) {
        this.beginning_hour = beginning_hour;
    }

    public String getEnding_hour() {
        return ending_hour;
    }

    public void setEnding_hour(String ending_hour) {
        this.ending_hour = ending_hour;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "username='" + username + '\'' +
                ", companyName='" + companyName + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", date='" + date + '\'' +
                ", beginning_hour='" + beginning_hour + '\'' +
                ", ending_hour='" + ending_hour + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }
}
