package com.example.project;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.database.Cursor;


import java.io.IOException;


public class CashActivity extends AppCompatActivity{

    TextView buttoday;
    TextView Week;
    TextView Month;
    TextView Year;
    TextView All;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDb;
    Button back;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        buttoday = (TextView) findViewById(R.id.buttoday);
        Week = (TextView) findViewById(R.id.Week);
        Month = (TextView) findViewById(R.id.Month);
        Year = (TextView) findViewById(R.id.Year);
        All = (TextView) findViewById(R.id.All);
        back = findViewById(R.id.menu2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashActivity.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        String product8 = null;
        Cursor cursorl2 = mDb.rawQuery("SELECT * FROM money", null);
        cursorl2.moveToFirst();
        while (!cursorl2.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product8 = cursorl2.getString(1) ;
            cursorl2.moveToNext();
        }
        String product1 = "";
        Cursor cursorl1 = mDb.rawQuery("SELECT * FROM money", null);
        cursorl1.moveToFirst();
        while (!cursorl1.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product1 = cursorl1.getString(2) ;
            cursorl1.moveToNext();
        }
        String product2 = "";
        Cursor cursor2 = mDb.rawQuery("SELECT * FROM money", null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product2 = cursor2.getString(3) ;
            cursor2.moveToNext();
        }
        String product3 = "";
        Cursor cursor3 = mDb.rawQuery("SELECT * FROM money", null);
        cursor3.moveToFirst();
        while (!cursor3.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product3 = cursor3.getString(4) ;
            cursor3.moveToNext();
        }
        String product4 = "";
        Cursor cursor4 = mDb.rawQuery("SELECT * FROM money", null);
        cursor4.moveToFirst();
        while (!cursor4.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product4 = cursor4.getString(5) ;
            cursor4.moveToNext();
        }
//        product8 = String.format("%.2f", product8);
//        product1 = String.format("%.2f", product1);
//        product2 = String.format("%.2f", product2);
//        product3 = String.format("%.2f", product3);
//        product4 = String.format("%.2f", product4);
        buttoday.setText(product8);
        Week.setText(product1);
        Month.setText(product2);
        Year.setText(product3);
        All.setText(product4);






    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case 150:
                Intent i = new Intent(CashActivity.this, SettingsActivity.class);
                startActivity(i);
                break;
            case 151:
                Intent b = new Intent(CashActivity.this, CashActivity.class);
                startActivity(b);
                break;
            case 152:
                Intent c = new Intent(CashActivity.this, MainActivity.class);
                startActivity(c);
                break;
            case 153:
                Intent d = new Intent(CashActivity.this, CiggaActivity.class);
                startActivity(d);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

