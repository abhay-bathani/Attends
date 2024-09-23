package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "register.db";
    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key,password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT, roll TEXT, name TEXT, totalLectures INTEGER DEFAULT 0, totalPresent INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }
    public boolean checkUsername (String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }
    public boolean checkUser(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor1;
        cursor1 = myDB.rawQuery("select * from users where username = ? and password=?", new String[]{username,password});
        if (cursor1.getCount() > 0)
            return true;
        else return false;
    }


    public void addStudent(String roll, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roll", roll);
        contentValues.put("name", name);
        db.insert("students", null, contentValues);
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM students", null);
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("students", "id = ?", new String[]{String.valueOf(id)});
    }

    public void updateAttendance(int studentId, boolean isPresent) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isPresent) {
            db.execSQL("UPDATE students SET totalPresent = totalPresent + 1, totalLectures = totalLectures + 1 WHERE id = ?", new Object[]{studentId});
        } else {
            db.execSQL("UPDATE students SET totalLectures = totalLectures + 1 WHERE id = ?", new Object[]{studentId});
        }
    }



    public float getAttendancePercentage(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT totalPresent, totalLectures FROM students WHERE id = ?", new String[]{String.valueOf(studentId)});
        if (cursor.moveToFirst()) {
            int totalPresent = cursor.getInt(cursor.getColumnIndexOrThrow("totalPresent"));
            int totalLectures = cursor.getInt(cursor.getColumnIndexOrThrow("totalLectures"));
            cursor.close();
            return (totalPresent / (float) totalLectures) * 100;
        }
        cursor.close();
        return 0;
    }


}