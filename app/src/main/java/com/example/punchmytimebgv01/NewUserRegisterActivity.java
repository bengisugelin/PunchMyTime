package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserRegisterActivity extends AppCompatActivity {

    EditText username_input, password_input, email_input, name_input, surname_input, phonenumber_input;
    Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_register);

        btnRegister = findViewById(R.id.btnRegisterButton);

        //input data for database
        username_input = findViewById(R.id.textUsernameRegisterPage);
        password_input = findViewById(R.id.textPasswordRegisterPage);
        email_input = findViewById(R.id.textemailRegisterPAge);
        name_input = findViewById(R.id.TextUserNameRegisterPage);
        surname_input = findViewById(R.id.textUserSurnameRegisterPage);
        phonenumber_input = findViewById(R.id.phoneRegisterPage);


        //After inputing data the button must send infprmation to Database
        // and return to HomePage

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //databahelper class
                DatabaseHelper punchMyTimeDB = new DatabaseHelper(NewUserRegisterActivity.this);
                //addUser method to add new user when register button clicked
                punchMyTimeDB.addUser(username_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        password_input.getText().toString().trim(),
                        name_input.getText().toString().trim(),
                        surname_input.getText().toString().trim(),
                        phonenumber_input.getText().toString().trim());




                Intent goToHomePage = new Intent(NewUserRegisterActivity.this, MainActivity.class);
                startActivity(goToHomePage);











            }
        });

    }
}