package com.example.punchmytimebgv01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PunchMyTime.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_punchtime";

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String COMPANY_NAME = "company_name";
    private static final String ROLE = "role";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String HOURLY_RATE = "hourly_rate";
    private static final String HOURS = "hours";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USERNAME + " VARCHAR, " +
                        EMAIL + " VARCHAR, " +
                        PASSWORD + " VARCHAR, " +
                        NAME + " VARCHAR, " +
                        SURNAME + " VARCHAR, " +
                        PHONE_NUMBER + " VARCHAR);" ;
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, userModel.getUsername());
        cv.put(EMAIL, userModel.getEmail());
        cv.put(PASSWORD, userModel.getPassword());
        cv.put(NAME, userModel.getName());
        cv.put(SURNAME, userModel.getSurname());
        cv.put(PHONE_NUMBER, userModel.getPhoneNumber());

        long result = db.insert(TABLE_NAME,null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "User added succesfully", Toast.LENGTH_SHORT).show();
        }
    }//end of adduser

}
