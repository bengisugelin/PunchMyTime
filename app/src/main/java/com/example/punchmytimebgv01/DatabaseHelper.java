package com.example.punchmytimebgv01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PunchMyTime.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE_NAME = "my_punchtime_user";

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PHONE_NUMBER = "phone_number";


    private static final String COMPANY_TABLE_NAME = "my_punchtime_company";
    private static final String COMPANY_NAME = "company_name";
    private static final String ROLE = "role";
    private static final String HOURLY_RATE = "hourly_rate";
    private static final String HOURS = "hours";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                        " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USERNAME + " VARCHAR, " +
                        EMAIL + " VARCHAR, " +
                        PASSWORD + " VARCHAR, " +
                        NAME + " VARCHAR, " +
                        SURNAME + " VARCHAR, " +
                        PHONE_NUMBER + " VARCHAR);" ;
        db.execSQL(CREATE_USER_TABLE);


        String CREATE_COMPANY_TABLE = "CREATE TABLE IF NOT EXISTS " + COMPANY_TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME + " VARCHAR, " +
                COMPANY_NAME + " VARCHAR, " +
                ROLE + " VARCHAR, " +
                HOURLY_RATE + " VARCHAR);" ;
        db.execSQL(CREATE_COMPANY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMPANY_TABLE_NAME);
        onCreate(db);
    }


    public boolean addUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, userModel.getUsername());
        cv.put(EMAIL, userModel.getEmail());
        cv.put(PASSWORD, userModel.getPassword());
        cv.put(NAME, userModel.getName());
        cv.put(SURNAME, userModel.getSurname());
        cv.put(PHONE_NUMBER, userModel.getPhoneNumber());

        long result = db.insert(USER_TABLE_NAME,null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context, "User added succesfully", Toast.LENGTH_SHORT).show();
            return  true;
        }
    }//end of adduser

    public boolean addCompany(CompanyModel companyModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, companyModel.getUsername());
        cv.put(COMPANY_NAME, companyModel.getCompanyName());
        cv.put(ROLE, companyModel.getRole());
        cv.put(HOURLY_RATE, companyModel.getHourlyRate());

        long result = db.insert(COMPANY_TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context, "Company added succesfully", Toast.LENGTH_SHORT).show();
            return  true;
        }
    }//end of add company


    public List<UserModel> getAllData(String username){

        List<UserModel> retunList = new ArrayList<>();

        //get data from the database
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ?" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,new String[] {username});

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new user objects. Put then into the return list.
            do{
                //int userId = cursor.getInt(0);
                String userName = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                String name = cursor.getString(4);
                String surname = cursor.getString(5);
                String phoneNumber = cursor.getString(6);
                UserModel newUser = new UserModel(userName,email,password,name,surname,phoneNumber);
                retunList.add(newUser);

            }while(cursor.moveToNext());
        }else{

            //failure. do not add anything to the list.

        }

        //close both the cursor and db when done
        cursor.close();
        db.close();


        return retunList;

    }//end of checkUserLogin

    List<CompanyModel> getCompanyData(String username){

        List<CompanyModel> retunList = new ArrayList<>();

        //get data from the database
        String query = "SELECT * FROM " + COMPANY_TABLE_NAME + " WHERE username = ?" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,new String[] {username});

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new user objects. Put then into the return list.
            do{

                String userName = cursor.getString(1);
                String companyName = cursor.getString(2);
                String role = cursor.getString(3);
                Double hourlyRate = Double.parseDouble(cursor.getString(4));
                CompanyModel newCompany = new CompanyModel(userName,companyName,role,hourlyRate);
                retunList.add(newCompany);

            }while(cursor.moveToNext());
        }else{

            //failure. do not add anything to the list.
        }
        //close both the cursor and db when done
        cursor.close();
        db.close();

        return retunList;
    }//end of getCompanyData


    public void  updateProfileData(String username, String email, String password, String name, String surname, String phoneNumber){

       //calling a method to get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL, email);
        cv.put(PASSWORD, password);
        cv.put(NAME, name);
        cv.put(SURNAME, surname);
        cv.put(PHONE_NUMBER, phoneNumber);
        
        long result = db.update(USER_TABLE_NAME,cv,"username=?", new String[]{username});
        db.close();
        if(result==-1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            
        }else{
            Toast.makeText(context, "Updated succesfully", Toast.LENGTH_SHORT).show();
        }
    }


}
