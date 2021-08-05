package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class Android extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    DBHelper db;


    String questions[] = {
            "1. Which method can be defined only once in a program?",
            "2.Which of these is not a bitwise operator?",
            "3. Which keyword is used by method to refer to the object that invoked it?",
            "4. Which of these keywords is used to define interfaces in Java?",
            "5. Which of these access specifiers can be used for an interface?",
            "6. Which of the following is correct way of importing an entire package ‘pkg’?",
            "7. What is the return type of Constructors?",
            "8. Which of the following package stores all the standard java classes?",
            "9. Which of these method of class String is used to compare two String objects for their equality?",
            "10. An expression involving byte, int, & literal numbers is promoted to which of these?"
    };
    String answers[] = {"main method","<=","this","interface","public","import pkg.*","None of the mentioned","java","equals()","int"};
    String opt[] = {
            "finalize method","main method","static method","private method",
            "&","&=","|=","<=",
            "import","this","catch","abstract",
            "Interface","interface","intf","Intf",
            "public","protected","private","All of the mentioned",
            "Import pkg.","import pkg.*","Import pkg.*","import pkg.",
            "int","float","void","None of the mentioned",
            "lang","java","util","java.packages",
            "equals()","Equals()","isequal()","Isequal()",
            "int","long","byte","float"
    };
    int flag=0;
    public static int marks=0,correct,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        final TextView score = (TextView)findViewById(R.id.textView4);


        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
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
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    db.updateAndroid(username,correct);
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
                db.updateAndroid(username,correct);
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}