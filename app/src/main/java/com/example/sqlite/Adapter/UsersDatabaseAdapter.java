package com.example.sqlite.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.models.User;

import java.util.ArrayList;

public class UsersDatabaseAdapter {
    public static final String DATABASE_NAME = "UsersDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "USERS";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT, phone TEXT, email TEXT);";

    public UsersDatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public UsersDatabaseAdapter open() throws SQLException {
        if (db == null || !db.isOpen()) {
            db = dbHelper.getWritableDatabase();
        }
        return this;
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance(){
        return db;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_name TEXT, phone TEXT, email TEXT)";
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void insertEntry(String username, String phone, String email) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", username);
        values.put("phone", phone);
        values.put("email", email);
        db.insert(TABLE_NAME, null, values);
//        db.close();
    }

    public ArrayList<User> getRows() {
        ArrayList<User> users = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("user_name")));
            user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            users.add(user);
        }
        cursor.close();
//        db.close();
        return users;
    }

    public void deleteEntry(String id) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{id});
//        db.close();
    }

    public void deleteAll() {
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
//        db.close();
    }

    public int getCount() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
//        db.close();
        return count;
    }

    public void updateEntry(User user) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(user.getId())});
//        db.close();
    }
}