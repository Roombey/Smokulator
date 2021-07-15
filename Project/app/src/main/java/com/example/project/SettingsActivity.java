package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {
    TextView num;
    TextView dita;
    TextView staj;
    TextView kd;
    Button back;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        num = findViewById(R.id.Textnum);
        dita = findViewById(R.id.textdita);
        staj = findViewById(R.id.textstaj);
        kd = findViewById(R.id.textkd);
        back = findViewById(R.id.menu1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        String product8 = "";
        Cursor cursorl2 = mDb.rawQuery("SELECT * FROM time", null);
        cursorl2.moveToFirst();
        while (!cursorl2.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product8 += cursorl2.getInt(1) ;
            cursorl2.moveToNext();
        }
        staj.setText(product8);
        String product5 = "";

        String l = "";
        Cursor cursorl = mDb.rawQuery("SELECT * FROM sigertoday", null);
        cursorl.moveToFirst();
        while (!cursorl.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            l += cursorl.getInt(1) ;
            cursorl.moveToNext();
        }
        cursorl.close();
        dita.setText(l);
        String product = "";

        Cursor cursor = mDb.rawQuery("SELECT * FROM siger", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product += cursor.getString(0) ;
            cursor.moveToNext();
        }
        cursor.close();
        num.setText(product);
        double v= ' ';
        Cursor cursord9 = mDb.rawQuery("SELECT * FROM time", null);
        cursord9.moveToFirst();
        while (!cursord9.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            v = cursord9.getDouble(1) ;
            cursord9.moveToNext();
        }
        double vz= ' ';
        Cursor cursord91 = mDb.rawQuery("SELECT * FROM siger", null);
        cursord91.moveToFirst();
        while (!cursord91.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            vz = cursord91.getDouble(0) ;
            cursord91.moveToNext();
        }

        String product13 = "";
        Cursor cursor13 = mDb.rawQuery("SELECT * FROM sigiday", null);
        cursor13.moveToFirst();
        while (!cursor13.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product13 += cursor13.getString(1) ;
            cursor13.moveToNext();
        }
        cursor13.close();

        kd.setText(product13);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case 150:
                Intent i = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(i);
                break;
            case 151:
                Intent b = new Intent(SettingsActivity.this, CashActivity.class);
                startActivity(b);
                break;
            case 152:
                Intent c = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(c);
                break;
            case 153:
                Intent d = new Intent(SettingsActivity.this, CiggaActivity.class);
                startActivity(d);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
