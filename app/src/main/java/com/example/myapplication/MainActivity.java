package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      button =findViewById(R.id.button);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent=new Intent(MainActivity.this,scoreactivity.class);
              startActivity(intent);
          }
      });
    }

    public void clicktostart(View view){
        Intent i = new Intent(this,quizz.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        return;
    }

}