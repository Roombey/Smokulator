package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {
    private ArrayAdapter<String> mAdapter;
    TextView num;
    TextView dita;
    TextView staj;
    TextView kd;
    Button back;
    ListView staticus;
    ListView staticus2;
    private static final String MY_SETTINGS = "my_settings";

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
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        int gig = (int)sp.getFloat("given", 0);
        int tak = sp.getInt("taken", 0);
        staticus = findViewById(R.id.staticus);
        staticus2 = findViewById(R.id.staticus2);
        back = findViewById(R.id.menu1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        String[] kek = new String[5];
        kek[0] = "Сигарет выкурено: ";
        kek[1] = "Выкурено сегодня: ";
        kek[2] = "Ср. сигарет в день: ";
        kek[3] = "Отдано сигарет: ";
        kek[4] = "Выкурено чужих сигарет: ";
        String[] kekl = new String[5];
        String product = "";

        Cursor cursor = mDb.rawQuery("SELECT * FROM siger", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product += cursor.getString(0) ;
            cursor.moveToNext();
        }
        cursor.close();
        kekl[0] =product;
        String l = "";
        Cursor cursorl = mDb.rawQuery("SELECT * FROM sigertoday", null);
        cursorl.moveToFirst();
        while (!cursorl.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            l += cursorl.getInt(1) ;
            cursorl.moveToNext();
        }
        cursorl.close();
        kekl[1] =l;

        String product13 = "";
        Cursor cursor13 = mDb.rawQuery("SELECT * FROM sigiday", null);
        cursor13.moveToFirst();
        while (!cursor13.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product13 += cursor13.getString(1) ;
            cursor13.moveToNext();
        }
        cursor13.close();

        kekl[2] =product13;
        kekl[3] = Integer.toString(gig);
        kekl[4] = Integer.toString(tak);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_1, kek);
        staticus.setAdapter(adapter);
        staticus.setOverScrollMode(View.OVER_SCROLL_NEVER);
        ArrayAdapter<String> adapterl = new ArrayAdapter<>(this, R.layout.list_item_1, kekl);
        staticus2.setAdapter(adapterl);
        staticus2.setOverScrollMode(View.OVER_SCROLL_NEVER);
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
            case 154:
                Intent e = new Intent(SettingsActivity.this, Journal.class);
                startActivity(e);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
