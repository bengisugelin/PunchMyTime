package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ProfileInfoActivity extends AppCompatActivity {

    TextView username;
    @SuppressLint("MissingInflatedId")
    EditText email;
    EditText password;
    EditText firstname;
    EditText lastname;
    EditText phonenumber;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        username=findViewById(R.id.txtProfileUsername);
        email = findViewById(R.id.editTextProfileEmail);
        password=findViewById(R.id.editTextProfilePassword);
        firstname=findViewById(R.id.editTextProfilefisrtname);
        lastname=findViewById(R.id.editTextProfilelastName);
        phonenumber=findViewById(R.id.editTextProfilephone);


        Bundle bundle = getIntent().getExtras();
        String usernamePassed = bundle.getString("USERNAME", "mate");

        DatabaseHelper databaseHelper = new DatabaseHelper(ProfileInfoActivity.this);
        List<UserModel> user = databaseHelper.getAllData(usernamePassed);


        String usernameFromDB="";
        String emailFromDB="";
        String passwordFromDB="";
        String firstnameFromDB="";
        String lastnameFromDB="";
        String phoneFromDB="";



        for (int i = 0; i<user.size(); i++){
            if(user.get(i).getUsername().toString().equals(usernamePassed)){
                usernameFromDB = user.get(i).getUsername().toString();
                emailFromDB = user.get(i).getEmail().toString();
                passwordFromDB = user.get(i).getPassword().toString().trim();
                firstnameFromDB=user.get(i).getName().toString();
                lastnameFromDB=user.get(i).getSurname().toString();
                phoneFromDB=user.get(i).getPhoneNumber().toString();
            }
        }//end of for loop

        username.setText(usernameFromDB);
        email.setText(emailFromDB);
        password.setText(passwordFromDB);
        firstname.setText(firstnameFromDB);
        lastname.setText(lastnameFromDB);
        phonenumber.setText(phoneFromDB);


    }
}