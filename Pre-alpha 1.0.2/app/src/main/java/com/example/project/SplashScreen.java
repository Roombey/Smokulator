package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
    private static final String MY_SETTINGS = "my_settings";
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited) {
            LocalDate d1 = LocalDate.now();
            mDb.execSQL("UPDATE date SET date = "+d1.getYear()+" WHERE id = 5");
            mDb.execSQL("UPDATE date SET date = "+d1.getDayOfMonth()+" WHERE id = 7");
            mDb.execSQL("UPDATE date SET date = "+d1.getMonthValue()+" WHERE id = 6");
            mDb.execSQL("UPDATE last SET times = null Where id = 1");
            mDb.execSQL("UPDATE time SET time = 1 Where id = 1");
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
            builder.setMessage("1. Добавьте пачку или пачки сигарет, которыми вы пользуетесь. \n" +
                    "2. Первая добавленная вами пачка сразу станет активной. \n" +
                    "Изменить активную пачку можно с помощью кнопки «Выбрать пачку». \n" +
                    "3. Вы можете сразу изменить количество сигарет в активной пачке. \n" +
                    "4. В любой момент вы можете добавить или удалить пачку. \n" +
                    "5. В окне курения вы можете выполнять различные действия с сигаретами. \n" +
                    "6. В окне финансов вы можете отслеживать ваши расходы на сигареты. \n" +
                    "Учтите, что расчет идет по сигаретам, а не пачкам. \n" +
                    "7. В окне статистики вы можете отслеживать статистику вашего курения").setTitle("Инструкция по использованию")
                    .setNeutralButton("Начать!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Удачи!", Toast.LENGTH_SHORT);
                            toast.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getApplicationContext(), CiggaActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 3 * 1000);
                        }
                    });

            builder.show();

        }
        else{
            int dy = 1911;
            int dm = 12;
            int dd = 31;
            LocalDate d1;
            Cursor cursorka = mDb.rawQuery("SELECT date FROM date Where id = 5", null);
            cursorka.moveToFirst();
            while (!cursorka.isAfterLast()) {
                Log.d("Tagg", "Выполняется");
                dy = cursorka.getInt(0);
                cursorka.moveToNext();
            }
            cursorka.close();
            Cursor cursorkar = mDb.rawQuery("SELECT date FROM date Where id = 6", null);
            cursorkar.moveToFirst();
            while (!cursorkar.isAfterLast()) {
                Log.d("Tagg", "Выполняется");
                dm = cursorkar.getInt(0);
                cursorkar.moveToNext();
            }
            cursorkar.close();
            Cursor cursorkara = mDb.rawQuery("SELECT date FROM date Where id = 7", null);
            cursorkara.moveToFirst();
            while (!cursorkara.isAfterLast()) {
                Log.d("Tagg", "Выполняется");
                dd = cursorkara.getInt(0);
                cursorkara.moveToNext();
            }
            cursorkara.close();
            d1 = LocalDate.of(dy,dm,dd);
            LocalDate d2 = LocalDate.now();
            long days = ChronoUnit.DAYS.between(d1, d2);
            days++;
            mDb.execSQL("UPDATE time Set time = "+days+" Where id = 1");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3 * 1000);
        }

    }
}