package com.example.quiz_app;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Python extends AppCompatActivity {
    TextView tv, tv1;
    Button submit, quit;
    RadioGroup radio_g;
    RadioButton rr1, rr2, rr3, rr4;
    DBHelper db;
    TextView score;
    String questions[] = {
            "1. What is the maximum possible length of an identifier?",
            "2. Who developed the Python language?",
            "3. In which year was the Python language developed?",
            "4. In which language is Python written?",
            "5. Which one of the following is the correct extension of the Python file?",
            "6. In which year was the Python 3.0 version developed?",
            "7. What do we use to define a block of code in Python language?",
            "8. Which character is used in Python to make a single line comment?",
            "9. What is the method inside the class in python language?",
            "10. Which of the following declarations is incorrect?",
    };
    String answers[] = {"None of these above", "Guido van Rossum", "1989", "C", ".py", "2008", "Indentation", "#", "Function", "None of these"};
    String opt[] = {
            "16", "32", "64", "None of these above",
            "Zim Den", "Guido van Rossum", "Niene Stom", "Wick van Rossum",
            "1995", "1972", "1981", "1989",
            "English", "PHP", "C", "All of the above",
            ".py", ".python", ".p", "None of these",
            "2008", "2000", "2010", "2005",
            "Key", "Brackets", "Indentation", "None of these",
            "/", "//", "#", "!",
            "Object", "Function", "Attribute", "Argument",
            "X=2", "x=3", "xyz_=5", "None of these"

    };
    int flag = 0;
    public static int marks = 0, correct = 0, wrong = 0;
    public String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);
        score = (TextView) findViewById(R.id.textView4);
        submit = (Button) findViewById(R.id.bnext);
        quit = (Button) findViewById(R.id.bquit);
        tv = (TextView) findViewById(R.id.tvque);
        radio_g = (RadioGroup) findViewById(R.id.answersgrp);
        rr1 = (RadioButton) findViewById(R.id.r1);
        rr2 = (RadioButton) findViewById(R.id.r2);
        rr3 = (RadioButton) findViewById(R.id.r3);
        rr4 = (RadioButton) findViewById(R.id.r4);
        tv.setText(questions[flag]);
        rr1.setText(opt[0]);
        rr2.setText(opt[1]);
        rr3.setText(opt[2]);
        rr4.setText(opt[3]);
        tv1 = (TextView) findViewById(R.id.text);
        db = new DBHelper(this);
        username = getIntent().getStringExtra("username");
        correct = 0;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }
                flag++;
                if (score != null)
                    score.setText(""+correct);
                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rr1.setText(opt[flag*4]);
                    rr2.setText(opt[flag*4 +1]);
                    rr3.setText(opt[flag*4 +2]);
                    rr4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    db.updatePython(username,correct);
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    in.putExtra("username",username);
                    startActivity(in);
                    finish();
                }
                radio_g.clearCheck();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                db.updatePython(username, correct);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}