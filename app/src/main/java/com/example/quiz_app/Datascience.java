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

public class Datascience extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rbb1,rbb2,rbb3,rbb4;
    DBHelper db;


    String questions[] = {
            "1. Data science is the process of diverse set of data through ?",
            "2. The modern conception of data science as an independent discipline is sometimes attributed to?",
            "3. Which of the following language is used in Data science?",
            "4. Which of the following is one of the key data science skills?",
            "5. Raw data should be processed only one time.",
            "6. Which of the following is correct skills for a Data Scientist?",
            "7. Which of the following are correct component for data science?",
            "8. Which of the following is not a part of data science process?",
            "9. Which of the following are the Data Sources in data science?",
            "10. Which of the following is not a application for data science?"
    };
    String answers[] = {"All of the above","William S","R","All of the above","False","All of the above","All of the above","Communication Building","Both A and B","Privacy Checker"};
    String opt[] = {
            "organizing data","processing data","analysing data","All of the above",
            "William S","John McCarthy","Arthur Samuel","Satoshi Nakamoto",
            "C","C++","R","Ruby",
            "Statistics","Machine Learning","Data Visualization","All of the above",
            " True","False","Can be true or false","Can not say",
            "Probability & Statistics","Machine Learning / Deep Learning","Data Wrangling","All of the above",
            "Data Engineering","Advanced Computing","Domain expertise","All of the above",
            " Discovery","Model Planning","Communication Building","Operationalize",
            "Structured","UnStructured","Both A and B","None Of the above",
            "Recommendation Systems","Image & Speech Recognition","Online Price Comparison","Privacy Checker"

    };
    int flag=0;

    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datascience);

        final TextView score = (TextView)findViewById(R.id.textView4);


        submitbutton=(Button)findViewById(R.id.bnext);
        quitbutton=(Button)findViewById(R.id.bquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rbb1=(RadioButton)findViewById(R.id.rad1);
        rbb2=(RadioButton)findViewById(R.id.rad2);
        rbb3=(RadioButton)findViewById(R.id.rad3);
        rbb4=(RadioButton)findViewById(R.id.rad4);
        tv.setText(questions[flag]);
        rbb1.setText(opt[0]);
        rbb2.setText(opt[1]);
        rbb3.setText(opt[2]);
        rbb4.setText(opt[3]);
        db=new DBHelper(this);
        String username=getIntent().getStringExtra("username");
        correct=0;
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

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
                    rbb1.setText(opt[flag*4]);
                    rbb2.setText(opt[flag*4 +1]);
                    rbb3.setText(opt[flag*4 +2]);
                    rbb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    db.updateDatascience(username,correct);
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    in.putExtra("username",username);
                    startActivity(in);
                    finish();
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateDatascience(username,correct);
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }

}