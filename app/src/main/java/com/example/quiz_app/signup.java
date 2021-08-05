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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class signup extends AppCompatActivity {
    private EditText username,pwd;
    private Button signup,login;
    CheckBox check;
    Boolean checked=false;
    DBHelper db;
    String regularExpression ="^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=(EditText)findViewById(R.id.edit3);
        pwd=(EditText)findViewById(R.id.edit4);
        signup=(Button)findViewById(R.id.button);
        login=(Button)findViewById(R.id.button2);
        check=(CheckBox)findViewById(R.id.checkuser);
        db=new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newuname = username.getText().toString();
                String newpass = pwd.getText().toString();
                System.out.println("Matches" + newpass);
                if (checked == true){
                if(validatePassword(newpass)) {
                    Boolean check_user=db.checkusername(newuname);
                    if(!check_user) {
                        boolean insert = db.insertData(newuname, newpass);
                        if (insert) {
                            Toast.makeText(com.example.quiz_app.signup.this, "Registered ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(com.example.quiz_app.signup.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(com.example.quiz_app.signup.this,"Username already exists, Try agian",Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getBaseContext(), "Invalid Password or username", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(signup.this,"Please accept terms and condition",Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(signup.this,"Checked",Toast.LENGTH_LONG).show();
                checked=true;
            }
        });
    }
    public boolean validatePassword(String password){
        Pattern pattern=Pattern.compile(regularExpression);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }
}