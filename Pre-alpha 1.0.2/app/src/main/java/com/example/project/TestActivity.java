package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class TestActivity extends AppCompatActivity {
    Button setiings;
    Button cash;
    Button home;
    Button cig;
    Button reset;
    Button journal;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private static final String MY_SETTINGS = "my_settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setiings = findViewById(R.id.btnSet);
        cash = findViewById(R.id.btnCash);
        home = findViewById(R.id.btnHome);
        cig = findViewById(R.id.btnCig);
        reset = findViewById(R.id.resetall);
        journal = findViewById(R.id.jou);
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
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                builder.setMessage("Сбросить статистику?")
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int l = 0;
                                Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga", null);
                                cursork.moveToFirst();
                                while (!cursork.isAfterLast()) {
                                    Log.d("Tagg","Выполняется");
                                    l = cursork.getInt(0) ;
                                    cursork.moveToNext();
                                }
                                for(int i = 1; i <= l; i++){
                                    mDb.execSQL("Delete From Cigga Where ID = "+i);
                                }
                                mDb.execSQL("Delete From Diary Where Own != '2'");
                                mDb.execSQL("UPDATE money SET today = 0, week = 0, mounth = 0, year = 0, alltime = 0 Where id = 1");
                                mDb.execSQL("UPDATE siger SET sigi = 0 Where id = 1");
                                mDb.execSQL("UPDATE sigertoday SET sigitoday = 0 Where id = 1");
                                mDb.execSQL("UPDATE sigiday SET sigiday = 0 Where id = 1");
                                mDb.execSQL("UPDATE time SET time = 1 Where id = 1");
                                mDb.execSQL("UPDATE last SET times = null Where id = 1");
                                SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                                        Context.MODE_PRIVATE);
                                SharedPreferences.Editor e = sp.edit();
                                e.putBoolean("hasVisited", false);
                                e.commit();
                                e.putInt("taken", 0);
                                e.commit();
                                e.putFloat("given", 0);
                                e.commit();
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Статистика сброшена", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(TestActivity.this, SplashScreen.class);
                                startActivityForResult(intent, 1);
                            }
                        });
                builder.show();
            }
        });
        setiings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("btnCode", "BTN_START_CHTOTO");
                setResult(150, intent);
                finish();
            }

        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("btnCode", "BTN_START_drugoe");
                setResult(151, intent);
                finish();
            }

        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("btnCode", "BTN_START_tretie");
                setResult(152, intent);
                finish();
            }

        });
        cig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("btnCode","BTN_START_poslednee");
                setResult(153, intent);
                finish();
            }
        });
        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("btnCode","BTN_START_pyatoe");
                setResult(154, intent);
                finish();
            }
        });
    }
}