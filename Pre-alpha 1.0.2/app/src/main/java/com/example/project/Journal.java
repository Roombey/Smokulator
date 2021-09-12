package com.example.project;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Journal extends AppCompatActivity {
    private ArrayAdapter<String> mAdapter;
    TextView day, mounth, year;
    Spinner sday, smounth, syear;
    Button back, show;
    ListView joulv;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
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
        day = findViewById(R.id.day);
        mounth = findViewById(R.id.mounth);
        year = findViewById(R.id.year);
        sday = findViewById(R.id.spinnerday);
        smounth = findViewById(R.id.spinnermounth);
        syear = findViewById(R.id.spinneryear);
        back = findViewById(R.id.backkkkkk);
        show = findViewById(R.id.show);
        joulv = findViewById(R.id.joulv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Journal.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        LocalDate d1 = LocalDate.now();
        String[] dd = new String[31];
        String[] mm = new String[12];
        String[] yy = new String[122];
        int j = 0;
        for(int i = 1; i < 32; i++){
            dd[j] = Integer.toString(i);
            j++;
        }
        mm[0] = "Январь";
        mm[1] = "Февраль";
        mm[2] = "Март";
        mm[3] = "Апрель";
        mm[4] = "Май";
        mm[5] = "Июнь";
        mm[6] = "Июль";
        mm[7] = "Август";
        mm[8] = "Сентябрь";
        mm[9] = "Октябрь";
        mm[10] = "Ноябрь";
        mm[11] = "Декабрь";
        j = d1.getYear();
        for(int i = 0; i < 122; i++){
            yy[i] = Integer.toString(j);
            j--;
        }
        ArrayAdapter<String> adapterd = new ArrayAdapter<>(this, R.layout.list_item_2, dd);
        sday.setAdapter(adapterd);
        ArrayAdapter<String> adapterm = new ArrayAdapter<>(this, R.layout.list_item_2, mm);
        smounth.setAdapter(adapterm);
        ArrayAdapter<String> adaptery = new ArrayAdapter<>(this, R.layout.list_item_2, yy);
        syear.setAdapter(adaptery);
        sday.setSelection(d1.getDayOfMonth()-1);
        smounth.setSelection(d1.getMonthValue()-1);
        String today = d1.toString();
        String d, c;
        int o;
        Cursor cursors = mDb.rawQuery("SELECT Name, Time, Date FROM diary Where Date = '" + today + "'", null);
        cursors.moveToFirst();
        int i = 0;
        while (!cursors.isAfterLast()) {
            i++;
            cursors.moveToNext();
        }
        String[] di = new String[i];
        i = 0;
        Cursor cursord = mDb.rawQuery("SELECT Name FROM diary Where Date = '" + today + "' Order by Time", null);
        Cursor cursort = mDb.rawQuery("SELECT Time FROM diary Where Date = '" + today + "' Order by Time", null);
        Cursor cursoro = mDb.rawQuery("SELECT Own FROM diary Where Date = '" + today + "' Order by Time", null);
        cursord.moveToFirst();
        cursort.moveToFirst();
        cursoro.moveToFirst();
        while (!cursord.isAfterLast()) {
            Log.d("Tagg", "Выполняется");
            c = cursord.getString(0);
            o = cursoro.getInt(0);
            if(o == 2){
                c = "Вы выкурили чужую сигарету";
            }
            if(o == 1){
                c = "Вы выкурили свою сигарету";
            }
            if(o == 0){
                c = "Вы поделились сигаретой";
            }
            d = c + " в " + cursort.getString(0);
            di[i] = d;
            i++;
            cursord.moveToNext();
            cursort.moveToNext();
            cursoro.moveToNext();
        }
        ArrayAdapter<String> adapterj = new ArrayAdapter<>(this, R.layout.list_item_1, di);
        joulv.setAdapter(adapterj);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dd, mm, yy, sum, c, d;
                int o;
                dd = sday.getSelectedItem().toString();
                if(dd.length()==1){
                    dd="0"+dd;
                }
                mm = Integer.toString(smounth.getSelectedItemPosition()+1);
                if(mm.length()==1){
                    mm="0"+mm;
                }
                yy = syear.getSelectedItem().toString();
                sum = yy+"-"+mm+"-"+dd;
                Cursor cursor4 = mDb.rawQuery("SELECT Name, Time, Date FROM diary Where Date = '" + sum + "'", null);
                cursor4.moveToFirst();
                int i = 0;
                while (!cursor4.isAfterLast()) {
                    i++;
                    cursor4.moveToNext();
                }
                String[] did = new String[i];
                i = 0;
                Cursor cursor1 = mDb.rawQuery("SELECT Name FROM diary Where Date = '" + sum + "' Order by Time", null);
                Cursor cursor2 = mDb.rawQuery("SELECT Time FROM diary Where Date = '" + sum + "' Order by Time", null);
                Cursor cursor3 = mDb.rawQuery("SELECT Own FROM diary Where Date = '" + sum + "' Order by Time", null);
                cursor1.moveToFirst();
                cursor2.moveToFirst();
                cursor3.moveToFirst();
                while (!cursor1.isAfterLast()) {
                    Log.d("Tagg", "Выполняется");
                    c = cursor1.getString(0);
                    o = cursor3.getInt(0);
                    if(o == 2){
                        c = "Вы выкурили чужую сигарету";
                    }
                    if(o == 1){
                        c = "Вы выкурили свою сигарету";
                    }
                    if(o == 0){
                        c = "Вы поделились сигаретой";
                    }
                    d = c + " в "+cursor2.getString(0);
                    did[i] = d;
                    i++;
                    cursor1.moveToNext();
                    cursor2.moveToNext();
                    cursor3.moveToNext();
                }
                ArrayAdapter<String> adapterjs = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item_1, did);
                joulv.setAdapter(adapterjs);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case 150:
                Intent i = new Intent(Journal.this, SettingsActivity.class);
                startActivity(i);
                break;
            case 151:
                Intent b = new Intent(Journal.this, CashActivity.class);
                startActivity(b);
                break;
            case 152:
                Intent c = new Intent(Journal.this, MainActivity.class);
                startActivity(c);
                break;
            case 153:
                Intent d = new Intent(Journal.this, CiggaActivity.class);
                startActivity(d);
                break;
            case 154:
                Intent e = new Intent(Journal.this, Journal.class);
                startActivity(e);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}