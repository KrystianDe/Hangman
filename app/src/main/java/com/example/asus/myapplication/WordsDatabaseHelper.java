package com.example.asus.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class WordsDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "WORDEES";
    private static final int DB_VERSION = 1;


    public WordsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE WORDEES " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT);");
        insertWord(db, "MONKEY");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<String> getWords(SQLiteDatabase db) {


        List<String> words = new ArrayList<>();
        String sql = "SELECT WORD FROM WORDEES";

        Cursor query = db.rawQuery(sql, null);


        if (query.moveToFirst()) {
            do {

                try {
                    String word = query.getString(0);

                    System.out.println(word);
                    words.add(word);
                } catch (Exception e) {
                    System.out.println("mm " + e.getMessage());
                }

            } while (query.moveToNext());
        }

        query.close();


        return words;

    }

    public void insertWord(SQLiteDatabase db, String word) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("WORD", word);


        db.insert("WORDEES", null, contentValues);




    }
}
