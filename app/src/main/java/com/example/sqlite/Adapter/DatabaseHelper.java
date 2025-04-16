package com.example.sqlite.Adapter;

import static com.example.sqlite.Adapter.UsersDatabaseAdapter.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try {
            db.execSQL(UsersDatabaseAdapter.DATABASE_CREATE);
        }catch (Exception e){
            Log.e("Error", "exception");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ "SEMESTER1");

        onCreate(db);
    }
}
