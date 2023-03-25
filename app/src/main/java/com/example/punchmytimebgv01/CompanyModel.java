package com.example.punchmytimebgv01;

public class CompanyModel {
    private String username;
    private String companyName;
    private String role;
    private double hourlyRate;

    public CompanyModel(String username, String companyName, String role, double hourlyRate) {
        this.username = username;
        this.companyName = companyName;
        this.role = role;
        this.hourlyRate = hourlyRate;
    }

    public CompanyModel() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
                "username='" + username + '\'' +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}
