package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class topics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        Button Android=(Button)findViewById(R.id.button8);
        Button DataScience=(Button)findViewById(R.id.button5);
        Button Python=(Button)findViewById(R.id.button9);
        Button OS=(Button)findViewById(R.id.button111);
        String username=getIntent().getStringExtra("username");
        Android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), com.example.quiz_app.Android.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

        DataScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Datascience.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        Python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Python.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        OS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OperatingSystem.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }
}