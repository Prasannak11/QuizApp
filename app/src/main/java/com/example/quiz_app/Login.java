package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText eusername,epwd;
    Button Login;
    String username,paswd;
    int count=0;
    DBHelper db;
    Boolean checkuserpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eusername = (EditText) findViewById(R.id.edit3);
        epwd = (EditText) findViewById(R.id.edit4);
        Login = (Button) findViewById(R.id.button3);
        db=new DBHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputname = eusername.getText().toString();
                String inputpwd = epwd.getText().toString();
                checkuserpwd=db.checkusernamepwd(inputname,inputpwd);
                if(!checkuserpwd){
                    count++;
                    if (count == 3) {
                        Login.setEnabled(false);
                        Toast.makeText(Login.this, "Failed Login Attempts", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(Login.this, "Login Failed" + count, Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, topics.class);
                    intent.putExtra("username",inputname);
                    startActivity(intent);
                    finish();
                    }
            }
        });
    }
}