package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


//this is the first version of punchMyTime login page
public class MainActivity extends AppCompatActivity {


    //Defining Elements
    Button LoginButton;
    TextView newUserText;
    TextView forgotPassword;
    EditText usernameTxt;
    EditText passwordTxt;

    //Defining Variables
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning Layout to Java
        LoginButton = findViewById(R.id.LoginBtn);
        newUserText = findViewById(R.id.newUserTextLoginPage);
        forgotPassword = findViewById(R.id.forgotButtonTxtLoginPage);
        usernameTxt = findViewById(R.id.usernameInputLoginPage);
        passwordTxt = findViewById(R.id.passwordInputLoginPage);






        //Clickable Text New User - Once Clicked the app goes to Register Page
        newUserText.setOnClickListener((View view)  ->{

            Intent goTouserRegisterActivity = new Intent(MainActivity.this,NewUserRegisterActivity.class);
            startActivity(goTouserRegisterActivity);

        });

        //Clickable Forgot Password - Once Clicked the app goes to ForgotPassword Page
        forgotPassword.setOnClickListener((View view) ->{

            Intent goToForgotPasswordActivity = new Intent(MainActivity.this,ForgotPasswordActivity.class);
            startActivity(goToForgotPasswordActivity);

        });

        // Login button - Once clicked the app goes to Home Page
        // Assigned input values to string variable
        // --Missing-- Send username and password to DB and check correctness
        LoginButton.setOnClickListener((View view) -> {
            Intent LogIntoTheApplication = new Intent(MainActivity.this, HomePageActivity.class);


            try {

                username = usernameTxt.getText().toString();
                password = passwordTxt.getText().toString();

                if(username.equals("priya") & password.equals("priya")){
                    startActivity(LogIntoTheApplication);
                } else {
                    Toast.makeText(this, "Wrong Username and Password", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Smth Wrong", Toast.LENGTH_SHORT).show();
            }

        });



    }
}
