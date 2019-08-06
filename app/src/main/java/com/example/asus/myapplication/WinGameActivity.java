package com.example.asus.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class WinGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wingame_layout);
        TextView correctAnsTextView = findViewById(R.id.correctAnsTExtView);
        TextView wrongAnsTextView = findViewById(R.id.wongAnsTextView);

        Bundle extras = getIntent().getExtras();


        int correctAnswers = extras.getInt("Correct");
        int wrongAnswers = extras.getInt("Wrong");

        System.out.println(correctAnswers + " : " + wrongAnswers);

        correctAnsTextView.append(" " + correctAnswers);
        wrongAnsTextView.append(" " + wrongAnswers);

    }

    public void clickWinContinue(View view) {
        this.finish();
    }

}
