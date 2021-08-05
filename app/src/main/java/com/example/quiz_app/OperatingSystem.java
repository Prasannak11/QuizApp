package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OperatingSystem extends AppCompatActivity {
    TextView t;
    Button submibutton, quibutton;
    RadioGroup radio_ggg;
    RadioButton rbbb1,rbbb2,rbbb3,rbbb4;
    DBHelper db;

    String questions[] = {
            "1. Which of the following is not an operating system?",
            "2. What is the maximum length of the filename in DOS?",
            "3. When was the first operating system developed?",
            "4. When were MS windows operating systems proposed?",
            "5. What else is a command interpreter called?",
            "6. What is the full name of FAT?",
            "7. BIOS is used?",
            "8. What is the mean of the Booting in the operating system?",
            "9. Banker's algorithm is used?",
            "9. When you delete a file in your computer, where does it go?"
    };
    String answers[] = {"Oracle","8","1950","1985","shell","File allocation table","By operating system"," Restarting computer","To prevent deadlock"," Recycle bin"};
    String opt[] = {
            "Windows","Linux","Oracle","DOS",
            "4","5","8","12",
            "1948","1949","1950","1951",
            "1994","1990","1992","1985",
            "prompt","kernel","shell","command",
            "File attribute table","File allocation table","Font attribute table","Format allocation table",
            "By operating system","By compiler","By interpreter","By application software",
            "Restarting computer","Install the program","To scan","To turn off",
            "To prevent deadlock","To deadlock recovery","To solve the deadlock","None of these",
            "Recycle bin","Hard disk","Taskbar","None of these"

    };
    int flag=0;

    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_system);

        final TextView score = (TextView)findViewById(R.id.textView);


        submibutton=(Button)findViewById(R.id.bbnext);
        quibutton=(Button)findViewById(R.id.bbquit);
        t=(TextView) findViewById(R.id.tvqu);

        radio_ggg=(RadioGroup)findViewById(R.id.answersgrp);
        rbbb1=(RadioButton)findViewById(R.id.radi1);
        rbbb2=(RadioButton)findViewById(R.id.radi2);
        rbbb3=(RadioButton)findViewById(R.id.radi3);
        rbbb4=(RadioButton)findViewById(R.id.radi4);
        t.setText(questions[flag]);
        rbbb1.setText(opt[0]);
        rbbb2.setText(opt[1]);
        rbbb3.setText(opt[2]);
        rbbb4.setText(opt[3]);
        db=new DBHelper(this);
        String username=getIntent().getStringExtra("username");
        correct=0;
        submibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_ggg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton ns = (RadioButton) findViewById(radio_ggg.getCheckedRadioButtonId());
                String ansText = ns.getText().toString();
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
                    t.setText(questions[flag]);
                    rbbb1.setText(opt[flag*4]);
                    rbbb2.setText(opt[flag*4 +1]);
                    rbbb3.setText(opt[flag*4 +2]);
                    rbbb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    db.updateOs(username,correct);
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    in.putExtra("username",username);

                    startActivity(in);
                    finish();
                }
                radio_ggg.clearCheck();
            }
        });

        quibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateOs(username,correct);
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
}
}