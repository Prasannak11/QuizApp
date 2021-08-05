package com.example.quiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public  static final String DBNAME="User.db";
    public DBHelper( Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username varchar(100) not null primary key,password varchar(100) not null," +
                " Android int default 0,Datascience int default 0,Python int default 0,Os int default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public Boolean insertData(String username,String pwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",pwd);
        long res=db.insert("user",null,contentValues);
        if (res==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from user where username=?",new String[] {username});
        if(cursor.getCount() >0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepwd(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from user where username=? and password=?",new String[] {username,password});
        if(cursor.getCount() >0)
            return true;
        else
            return false;

    }

    public void updateAndroid(String username,int score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Android",score);
        long res=db.update("user",contentValues,"username=?",new String[]{username});

    }
    public void updateOs(String username,int score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Os",score);
        long res=db.update("user",contentValues,"username=?",new String[]{username});

    }


    public void updatePython(String username,int score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Python",score);
        long res=db.update("user",contentValues,"username=?",new String[]{username});

    }

    public void updateDatascience(String username,int score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Datascience",score);
        long res=db.update("user",contentValues,"username=?",new String[]{username});

    }

    public Cursor getdata(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select username,Android,DataScience,Python,Os from user where username=?",new String[]{username},null);
        return cursor;
    }


}
