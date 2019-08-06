package com.example.asus.myapplication;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        WordsDatabaseHelper wdh = new WordsDatabaseHelper(this);

        DataHolder.setData(wdh);
    }

    public void clickAddWordBtn(View view) {

        Intent intent = new Intent(this, AddWordActivity.class);

        startActivity(intent);

    }

    public void clickStartBtn(View view) {

        Intent intent = new Intent(this, GameActivity.class);


        startActivity(intent);

    }


}
