package com.example.asus.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddWordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);


    }

    public void clickAddWord(View view) {


        EditText wordEditText = findViewById(R.id.AddWordEditText);

        String word = wordEditText.getText().toString();

        boolean match = Pattern.compile(word).matcher("([A-Za-z]{3,})").matches();

        System.out.println("Mathc: " + match);



        if(!word.equals("") && match) {
            WordsDatabaseHelper dbHelper = DataHolder.getDate();

            SQLiteDatabase db = dbHelper.getReadableDatabase();


            dbHelper.insertWord(db, word.toUpperCase());


            this.finish();
        }else {
            wordEditText.getText().clear();
            Toast.makeText(this,"Niepoprawna wartosc", Toast.LENGTH_SHORT).show();
        }

    }


}
