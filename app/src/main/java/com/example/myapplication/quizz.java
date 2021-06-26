package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class quizz extends AppCompatActivity {
    TextView question,scoretext,format,timer;
    Button generatebutton,startbutton;
    Button option1,option2,option3,option4;
    ConstraintLayout currentlayout;
    Vibrator vibrator;
    boolean wrongoption=(false);
    boolean forgeneratebutton;
    int weekno;

    int score=0,day1,day2,day3,year,dayofyear;
    int secondsremaining = 120;
    long milliseconds=120000;

    Timer backgroundchanger;

    String[] array= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    String[] array1= {"SUNDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    String[] array2= {"MONDAY","SUNDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    String[] array3= {"MONDAY","TUESDAY","SUNDAY","THURSDAY","FRIDAY","SATURDAY"};
    String[] array4= {"MONDAY","TUESDAY","WEDNESDAY","SUNDAY","FRIDAY","SATURDAY"};
    String[] array5= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","SUNDAY","SATURDAY"};
    String[] array6= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SUNDAY"};

    CountDownTimer timers = new CountDownTimer(milliseconds,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsremaining--;
            timer.setText(Integer.toString(secondsremaining));
            if (wrongoption){
               secondsremaining=secondsremaining-5;
                wrongoption=(false);
            }
                if(secondsremaining<=0){
                    timers.cancel();
                    secondsremaining=0;
                    if(secondsremaining==0){
                    SharedPreferences preferences = getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lastscore",score);
                    editor.apply();
                Intent intent=new Intent(quizz.this,scoreactivity.class);
                    startActivity(intent);
                    finish();}
            }

        }

        @Override
        public void onFinish() {
            Intent intent=new Intent(quizz.this,scoreactivity.class);
            intent.putExtra("score","score="+score);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        question =  findViewById(R.id.question);option1 = findViewById(R.id.Button1);option2 = findViewById(R.id.Button2);option3 = findViewById(R.id.Button3);
        option4 = findViewById(R.id.Button4);format=findViewById(R.id.format);
        scoretext=findViewById(R.id.scoretext);startbutton=findViewById(R.id.startbutton);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        generatebutton =  findViewById(R.id.generatebutton);
        currentlayout =  findViewById(R.id.main_layout);

        timer=findViewById(R.id.timer);
        timer.setText("0sec");

        generatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgeneratebutton=true;
                timers.start();
                scoretext.setText("SCORE="+score);
                question.setText(generateRandomDate());
                format.setText("Format = YYYY/DD/MM");
               generatebutton.setVisibility(View.INVISIBLE);


            }
        });
    }


    private String generateRandomDate() {
        // for getting unique random options
         day1 = randBetween(0,5);
         day2 = randBetween(0,5);
        day3 =randBetween(0,5);
        while (day1==day2)
        {day2 = randBetween(0, 5);}
        while (day1==day3 || day2==day3)
        {  day3= randBetween(0,5); }

        // for random date
            GregorianCalendar gc = new GregorianCalendar();
             year = randBetween(1900, 2050);
            gc.set(GregorianCalendar.YEAR, year);
            dayofyear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
            gc.set(GregorianCalendar.DAY_OF_YEAR, dayofyear);
                    weekno   =gc.get(GregorianCalendar.DAY_OF_WEEK);
            if (weekno == 1){
                option1.setText("SUNDAY");option2.setText(array[day1]);option3.setText(array[day2]);option4.setText(array[day3]);
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);


                    }});
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  question.setText(generateRandomDate());vibrator.vibrate(300);
                      score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);
                    scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);


                    }});
            }else if (weekno == 2){

                option2.setText("MONDAY");option1.setText(array1[day1]);option3.setText(array1[day2]);option4.setText(array1[day3]);
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true);
                        currentlayout.setBackgroundColor(Color.RED);
                     scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);
                       scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true);currentlayout.setBackgroundColor(Color.RED);
                       scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});

            }else if (weekno == 3){

                option3.setText(R.string.tuesday_text);option1.setText(array2[day1]);option2.setText(array2[day2]);option4.setText(array2[day3]);
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true);currentlayout.setBackgroundColor(Color.RED);
                      scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                       score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);
                       scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);
                     scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
            }else if (weekno == 4){
                option4.setText("WEDNESDAY");option1.setText(array3[day1]);option2.setText(array3[day2]);option3.setText(array3[day3]);
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--; wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
            }else if (weekno == 5){
                option1.setText("THURSDAY");option2.setText(array4[day1]);option3.setText(array4[day2]);option4.setText(array4[day3]);
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                       score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED);scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true);  currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  question.setText(generateRandomDate());vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});

            }else if (weekno == 6){
                option2.setText("FRIDAY");option1.setText(array5[day1]);option3.setText(array5[day2]);option4.setText(array5[day3]);
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);
                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate()); vibrator.vibrate(300);
                       score--; wrongoption=(true);currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  question.setText(generateRandomDate());vibrator.vibrate(300);
                       score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                       score--;wrongoption=(true);  currentlayout.setBackgroundColor(Color.RED);scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
            }else {
                option1.setText(array6[day1]);option2.setText(array6[day2]);option3.setText(array6[day3]);option4.setText("SATURDAY");
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());scoretext.setText("SCORE="+score);}});
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate()); vibrator.vibrate(300);
                        score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate());vibrator.vibrate(300);
                       score--;wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);

                    }});
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { question.setText(generateRandomDate()); vibrator.vibrate(300);
                        score--; wrongoption=(true); currentlayout.setBackgroundColor(Color.RED); scoretext.setText("SCORE="+score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }),200);
                    }});
            }

            return (gc.get(GregorianCalendar.YEAR))
                    + "/" + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH) + 1);
        }


            private static int randBetween(int start, int end){
                return start+ (int) Math.round(Math.random() * (end-start));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArray("array",array);
        outState.putStringArray("array1",array1);
        outState.putStringArray("array2",array2);
        outState.putStringArray("array3",array3);
        outState.putStringArray("array4",array4);
        outState.putStringArray("array5",array5);
        outState.putStringArray("array6",array6);
        outState.putString("option1",option1.getText().toString());
        outState.putString("option2",option2.getText().toString());
        outState.putString("option3",option3.getText().toString());
        outState.putString("option4",option4.getText().toString());
        outState.putString("question",question.getText().toString());
        outState.putString("format",format.getText().toString());
        outState.putString("scoretext",scoretext.getText().toString());
        outState.putInt("score",score);
        outState.putString("timer",timer.getText().toString());
        outState.putString("generate",generatebutton.getText().toString());
        outState.putLong("milliseconds",milliseconds);
        outState.putBoolean("forgeneratebutton",forgeneratebutton);
        outState.putInt("secondsremaining",secondsremaining);
        outState.putInt("day1",day1);
        outState.putInt("day2",day2);
        outState.putInt("day3",day3);
        outState.putInt("year",year);
        outState.putInt("dayofyear",dayofyear);
        outState.putInt("weekno",weekno);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        option1.setText(savedInstanceState.getString("option1"));
        option2.setText(savedInstanceState.getString("option2"));
        option3.setText(savedInstanceState.getString("option3"));
        option4.setText(savedInstanceState.getString("option4"));
        question.setText(savedInstanceState.getString("question"));
        format.setText(savedInstanceState.getString("format"));
        scoretext.setText(savedInstanceState.getString("scoretext"));
        timer.setText(savedInstanceState.getString("timer"));
        generatebutton.setText(savedInstanceState.getString("generate"));
        secondsremaining = savedInstanceState.getInt("secondsremaining");
        milliseconds = savedInstanceState.getLong("milliseconds");
        day1 = savedInstanceState.getInt("day1");
        day2 = savedInstanceState.getInt("day2");
        day3 = savedInstanceState.getInt("day3");
        year = savedInstanceState.getInt("year");
        score=savedInstanceState.getInt("score");
        dayofyear = savedInstanceState.getInt("dayofyear");
        weekno=savedInstanceState.getInt("weekno");
        array =savedInstanceState.getStringArray("array");
        array1 =savedInstanceState.getStringArray("array1");
        array2 =savedInstanceState.getStringArray("array2");
        array3 =savedInstanceState.getStringArray("array3");
        array4 =savedInstanceState.getStringArray("array4");
        array5 =savedInstanceState.getStringArray("array5");
        array6 =savedInstanceState.getStringArray("array6");
        forgeneratebutton=savedInstanceState.getBoolean("forgeneratebutton");
        if(forgeneratebutton) {
            generatebutton.setVisibility(View.INVISIBLE);
            timers.start();

            if (weekno == 1) {
                option1.setText("SUNDAY");
                option2.setText(array[day1]);
                option3.setText(array[day2]);
                option4.setText(array[day3]);
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);


                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);


                    }
                });
            } else if (weekno == 2) {

                option2.setText("MONDAY");
                option1.setText(array1[day1]);
                option3.setText(array1[day2]);
                option4.setText(array1[day3]);
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });

            } else if (weekno == 3) {

                option3.setText(R.string.tuesday_text);
                option1.setText(array2[day1]);
                option2.setText(array2[day2]);
                option4.setText(array2[day3]);
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
            } else if (weekno == 4) {
                option4.setText("WEDNESDAY");
                option1.setText(array3[day1]);
                option2.setText(array3[day2]);
                option3.setText(array3[day3]);
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
            } else if (weekno == 5) {
                option1.setText("THURSDAY");
                option2.setText(array4[day1]);
                option3.setText(array4[day2]);
                option4.setText(array4[day3]);
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });

            } else if (weekno == 6) {
                option2.setText("FRIDAY");
                option1.setText(array5[day1]);
                option3.setText(array5[day2]);
                option4.setText(array5[day3]);
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
            } else {
                option1.setText(array6[day1]);
                option2.setText(array6[day2]);
                option3.setText(array6[day3]);
                option4.setText("SATURDAY");
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                        currentlayout.setBackgroundColor(Color.GREEN);
                        question.setText(generateRandomDate());
                        scoretext.setText("SCORE=" + score);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);

                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question.setText(generateRandomDate());
                        vibrator.vibrate(300);
                        score--;
                        wrongoption = (true);
                        currentlayout.setBackgroundColor(Color.RED);
                        scoretext.setText("SCORE=" + score);
                        backgroundchanger = new Timer();
                        backgroundchanger.schedule((new TimerTask() {
                            @Override
                            public void run() {
                                currentlayout.setBackgroundColor(Color.BLACK);
                            }
                        }), 200);
                    }
                });
            }
        }



    }

}