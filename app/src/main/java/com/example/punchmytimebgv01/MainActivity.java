package com.example.punchmytimebgv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


//this is the first version of punchMyTime login page
public class MainActivity extends AppCompatActivity {


    //Defining Elements
    Button LoginButton;
    TextView newUserText;
    TextView forgotPassword;
    EditText usernameTxt;
    EditText passwordTxt;



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

                    String username = usernameTxt.getText().toString();
                    String password = passwordTxt.getText().toString();

                    String nameFromDB="";
                    String passwordFromDB="";


                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    List<UserModel> user = databaseHelper.getAllData(username);

                    //Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

                    Intent LogIntoTheApplication = new Intent(MainActivity.this, HomePageActivity.class);

                    //Check if the databaase has the username that user entered to the login input area
                    for (int i = 0; i<user.size(); i++){
                        if(user.get(i).getUsername().toString().equals(username)){
                            nameFromDB = user.get(i).getUsername().toString();
                            passwordFromDB = user.get(i).getPassword().toString().trim();
                        }
                    }//end of for loop


                    //check if the passwords are matching, if yes, log in, if not, throw a message
                    if (username.equals("") || password.equals("")) {
                        Toast.makeText(this, "Please enter you username and password", Toast.LENGTH_SHORT).show();
                    } else {
                        if (nameFromDB.equals(username) && passwordFromDB.equals(password.trim())) {

                            //to export the username to the home page
                            Bundle bundle = new Bundle();
                            bundle.putString("USERNAME", usernameTxt.getText().toString());
                            LogIntoTheApplication.putExtras(bundle);

                            startActivity(LogIntoTheApplication);
                        }else{
                            Toast.makeText(this, "wrong username/password", Toast.LENGTH_SHORT).show();
                        }
                    }//end of if else




        });



    }
}

