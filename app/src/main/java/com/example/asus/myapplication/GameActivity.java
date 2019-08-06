package com.example.asus.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView textView;

    private Integer counter = 1;
    private List<String> word;
    private String currentWord;

    private int correctAnswers;
    private int wrongAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        textView = findViewById(R.id.textWord);
        correctAnswers = 0;
        wrongAnswers = 0;


        WordsDatabaseHelper wdh = DataHolder.getDate();

        SQLiteDatabase db = wdh.getReadableDatabase();


        ArrayList<String> listOfWords = (ArrayList<String>) wdh.getWords(db);


        for (String s : listOfWords) {
            System.out.println(s);
        }

        currentWord = listOfWords.get(randomWord(listOfWords.size()));
        char[] tab = currentWord.toCharArray();

        drawGamePlan(tab);

        word = new ArrayList<>();


        for (char e : tab) {
            word.add(String.valueOf(e));
            word.add(" ");
        }


    }

    private void changeImage(ImageView imageView, int time) {

        switch (time) {
            case 1:
                imageView.setImageResource(R.mipmap.hang1);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.hang2);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.hangbeforelast);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.hanglast);
                break;
            case 5:
                this.finish();
                startActivity(new Intent(this, GameOverActivity.class));
                break;
            default:
                imageView.setImageResource(R.mipmap.hang0);
                break;
        }
    }

    private void drawGamePlan(char[] arr) {

        for (char anArr : arr) {
            textView.append("_ ");
        }
    }

    private void refreshTextView(String letter, List<String> word, TextView textView) {

        StringBuilder textFromView = new StringBuilder(textView.getText());
        textView.setText("");
        for (int i = 0; i < word.size(); i++) {
            String currentLetter = textFromView.substring(i, i + 1);
            if (!currentLetter.equalsIgnoreCase(" ")) {
                if (letter.equals(word.get(i))) {
                    textView.append(word.get(i));
                } else {

                    if (!currentLetter.equalsIgnoreCase("_")) {
                        textView.append(currentLetter);
                    } else {
                        textView.append("_");
                    }
                }
            } else {
                textView.append(" ");
            }
        }
    }

    private int randomWord(int size) {
        return (int) (Math.random() * size);
    }


    public void clickLetterButton(View view) {
        Button b = (Button) view;
        ImageView imageView = findViewById(R.id.imageHang);

        String letter = b.getText().toString();
        System.out.println(b.getText().subSequence(0, 1));
        try {
            if (word.contains(letter)) {
                refreshTextView(letter, word, textView);
                correctAnswers++;
            } else {

                changeImage(imageView, counter);
                counter += 1;
            }

            b.setEnabled(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String resultWord = textView.getText().toString().replace(" ", "");


        if (resultWord.equals(currentWord)) {
            Intent intent = new Intent(this, WinGameActivity.class);
            intent.putExtra("Correct", correctAnswers);
            intent.putExtra("Wrong", counter);

            this.finish();

            startActivity(intent);
        }


    }


}
