package com.example.quiz_app;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2, tv3;
    Button btnRestart,quit;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button view=findViewById(R.id.btnview);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        quit = (Button) findViewById(R.id.quit);
        db=new DBHelper(this);
        String username=getIntent().getStringExtra("username");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=db.getdata(username);
                if(cursor.getCount()==0){
                    Toast.makeText(ResultActivity.this,"No Users",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                cursor.moveToNext();
                    buffer.append("Name: " + cursor.getString(0) + "\n");
                    buffer.append("Android Score: " + cursor.getInt(1) + "\n");
                    buffer.append("Datascience Score: " + cursor.getInt(2) + "\n");
                    buffer.append("Python Score: " + cursor.getInt(3) + "\n");
                    buffer.append("Operating System Score: "+cursor.getInt(4)+"\n");
                AlertDialog.Builder builder=new AlertDialog.Builder(ResultActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Score");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),topics.class);
                in.putExtra("username",username);
                startActivity(in);
            }
        });
    }
}