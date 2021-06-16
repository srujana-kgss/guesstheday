package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class scoreactivity extends AppCompatActivity {
    TextView textView,textView2;
    int lastscore;
    int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreactivity);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastscore = preferences.getInt("lastscore", 0);
        highscore = preferences.getInt("highscore", 0);

        if (lastscore > highscore) {
            highscore = lastscore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", highscore);
            editor.apply();
        }

        textView.setText("recentscore=" + lastscore);
        textView2.setText("highscore=" + highscore);


    }




}