package com.example.punchmytimebgv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FloatingActionButton addnewHours;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //navigation drawer properties
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        //Toolbar
       setSupportActionBar(toolbar);

        //navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);



        userName =findViewById(R.id.txtHomePageGreeting);

        //import username to the homepage
        try {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("USERNAME", "mate");
            userName.setText("Hello " + username + "!");
        }catch (Exception e){
            e.printStackTrace();
        }


        //codes for adding new hours page
        addnewHours  = findViewById(R.id.addNewShift);

        addnewHours.setOnClickListener((View view)  ->{

            Intent goToAddNewHours = new Intent(HomePageActivity.this,NewWorkingHours.class);


            //to import the username to the new hours page
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("USERNAME", "mate");
            bundle.putString("USERNAME", username);
            goToAddNewHours.putExtras(bundle);

            startActivity(goToAddNewHours);

        });

    }

    public void onBackpressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }//end of onBackPressed




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //to export the username to the profile page
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "mate");
        Bundle usernameBundle = new Bundle();
        bundle.putString("USERNAME", username);
        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.Logout:
                Intent logoutIntent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(logoutIntent);
                break;
            case R.id.profile:
                Intent ProfileIntent = new Intent(HomePageActivity.this, ProfileInfoActivity.class);

                ProfileIntent.putExtras(bundle);

                startActivity(ProfileIntent);
                break;

            case R.id.punchHistory:
                Intent punchHistoryIntent = new Intent(HomePageActivity.this, PunchLogsActivvity.class);

                punchHistoryIntent.putExtras(bundle);

                startActivity(punchHistoryIntent);
                break;

        }
        return true;
    }
}