package com.example.punchmytimebgv01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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


    private static final String LOG_TABLE_NAME = "my_punchtime_logs";

    private static final String BEGINNING_HOUR = "beginning_hour";
    private static  final String ENDING_HOUR = "ending_hour";
    private static  final String HOURS_WORKED = "hours_worked";
    private static  final String DATE = "date";


    //
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



        String CREATE_LOG_TABLE = "CREATE TABLE IF NOT EXISTS " + LOG_TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME + " VARCHAR, " +
                COMPANY_NAME + " VARCHAR, " +
                HOURLY_RATE + " VARCHAR, " +
                DATE + " VARCHAR, " +
                BEGINNING_HOUR + " VARCHAR, " +
                ENDING_HOUR + " VARCHAR, " +
                HOURS_WORKED + " VARCHAR);" ;
        db.execSQL(CREATE_LOG_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMPANY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LOG_TABLE_NAME);
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

    public boolean addNewLog(LogModel logmodel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, logmodel.getUsername());
        cv.put(COMPANY_NAME, logmodel.getCompanyName());
        cv.put(HOURLY_RATE, logmodel.getHourlyRate());
        cv.put(DATE,logmodel.getDate());
        cv.put(BEGINNING_HOUR, logmodel.getBeginning_hour());
        cv.put(ENDING_HOUR, logmodel.getEnding_hour());
        cv.put(HOURS_WORKED, logmodel.getHoursWorked());

        long result = db.insert(LOG_TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context, "hours added succesfully", Toast.LENGTH_SHORT).show();
            return  true;
        }

    }// end of add new log

    public boolean deleteOneLog(LogModel logModel){

        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + LOG_TABLE_NAME + " WHERE username = ? AND date = ?" ;

        Cursor cursor =  db.rawQuery(deleteQuery,null);

        if(cursor.moveToFirst()){
            Toast.makeText(context, "punch deleted", Toast.LENGTH_SHORT).show();
            return true;
            
        }else{
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean deleteLog(LogModel logModel){
        SQLiteDatabase db = getWritableDatabase();
        String usernamechosed = logModel.getUsername();
        String datechosed = logModel.getDate();

        long result= db.delete(LOG_TABLE_NAME, "username= ? AND date = ?", new String[]{usernamechosed, datechosed} );

        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }




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

        List<CompanyModel> returnList = new ArrayList<>();

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
                returnList.add(newCompany);

            }while(cursor.moveToNext());
        }else{

            //failure. do not add anything to the list.
        }
        //close both the cursor and db when done
        cursor.close();
        db.close();

        return returnList;
    }//end of getCompanyData


    List<LogModel> getLogData(String username){
        List<LogModel> returnList = new ArrayList<>();

        //get data from database
        String query = "SELECT * FROM " + LOG_TABLE_NAME + " WHERE username = ?" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,new String[] {username});

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new user objects. Put then into the return list.
            do{

                String userName = cursor.getString(1);
                String companyName = cursor.getString(2);
                Double hourlyRate = Double.parseDouble(cursor.getString(3));
                String date = cursor.getString(4);
                String beginningHour = cursor.getString(5);
                String endingHour = cursor.getString(6);
                Double hoursWorked = Double.parseDouble(cursor.getString(7));


                LogModel logModel = new LogModel(userName,companyName,hourlyRate,date, beginningHour, endingHour,hoursWorked);
                returnList.add(logModel);

            }while(cursor.moveToNext());
        }else{

            //failure. do not add anything to the list.
        }
        //close both the cursor and db when done
        cursor.close();
        db.close();

        return returnList;
    }//end of getLogData


//
//    List<LogModel> getLogData(String username, String companyname){
//        List<LogModel> returnList = new ArrayList<>();
//
//        //get data from database
//        String query = "SELECT * FROM " + COMPANY_TABLE_NAME + " WHERE username = ? AND company_name = ?" ;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor =  db.rawQuery(query,new String[] {username, companyname});
//
//        if(cursor.moveToFirst()){
//            //loop through the cursor (result set) and create new user objects. Put then into the return list.
//            do{
//
//                String userName = cursor.getString(1);
//                String companyName = cursor.getString(2);
//                Double hourlyRate = Double.parseDouble(cursor.getString(3));
//                String date = cursor.getString(4);
//                String beginningHour = cursor.getString(5);
//                String endingHour = cursor.getString(6);
//                Double hoursWorked = Double.parseDouble(cursor.getString(7));
//
//
//                LogModel logModel = new LogModel(userName,companyName,hourlyRate,date, beginningHour, endingHour,hoursWorked);
//                returnList.add(logModel);
//
//            }while(cursor.moveToNext());
//        }else{
//
//            //failure. do not add anything to the list.
//        }
//        //close both the cursor and db when done
//        cursor.close();
//        db.close();
//
//        return returnList;
//    }//end of getLogData


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
