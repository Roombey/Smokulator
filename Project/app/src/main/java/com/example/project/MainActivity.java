package com.example.project;
import java.time.Month;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.database.SQLException;
import android.database.Cursor;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    int zero = 0;
    int Smoke = 1;
    Button knopka;
    Button knopkaMenu;
    Button shot;
    Button give;
    TextView sig, lt;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);


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
        knopka = (Button) findViewById(R.id.knopka);
        shot = (Button) findViewById(R.id.shot);
        give = (Button) findViewById(R.id.give);
        sig = findViewById(R.id.sig);
        lt = findViewById(R.id.lt);
        knopkaMenu = findViewById(R.id.knopkaMenu);
        String sigasT = null;
        Cursor cursorka1 = mDb.rawQuery("SELECT * FROM last", null);
        cursorka1.moveToFirst();
        while (!cursorka1.isAfterLast()) {
            Log.d("Tagg", "Выполняется");
            sigasT = cursorka1.getString(1);
            cursorka1.moveToNext();
        }
        cursorka1.close();
        if(sigasT != null) {
            lt.setText(sigasT);
        }
        else{
            lt.setText("");
        }
        knopkaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        int l = 0;
        Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga WHERE Activ = 1", null);
        cursork.moveToFirst();
        while (!cursork.isAfterLast()) {
            Log.d("Tagg", "Выполняется");
            l = cursork.getInt(0);
            cursork.moveToNext();
        }
        if (l == 1) {
            String sigas = "";
            Cursor cursorka = mDb.rawQuery("SELECT sig FROM Cigga Where Activ = 1", null);
            cursorka.moveToFirst();
            while (!cursorka.isAfterLast()) {
                Log.d("Tagg", "Выполняется");
                sigas = cursorka.getString(0);
                cursorka.moveToNext();
            }
            cursorka.close();
            sig.setText(sigas);
        }
        knopk();
        shot();
        give();
        int d = ' ';
        Date currentDate = new  Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        int DayOfWeek = (c.get(Calendar.DAY_OF_WEEK));
        int WeekOfMonth = (c.get(Calendar.WEEK_OF_MONTH));
        int Year = (c.get(Calendar.YEAR));
        int tt = WeekOfMonth;
        int MONTH = (c.get(Calendar.MONTH));
        int ddt = MONTH;
        int rtt = Year;
        int rdd = ' ';
        Cursor cursorddd = mDb.rawQuery("SELECT * FROM date where id = 4", null);
        cursorddd.moveToFirst();
        while (!cursorddd.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            rdd = cursorddd.getInt(1) ;
            cursorddd.moveToNext();
        }
        if (rtt !=rdd )
        { String lol111 = "";
            Cursor cursore333 = mDb.rawQuery("UPDATE money SET year = 0 WHERE id = '1'", null);
            cursore333.moveToFirst();
            while (!cursore333.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                lol111 += cursore333.getInt(1) ;
                cursore333.moveToNext();
            }
            cursore333.close();
            String product5 = "";
            Cursor cursorr = mDb.rawQuery("UPDATE date SET date = '"+rtt+"' WHERE id = '4'", null);
            cursorr.moveToFirst();
            while (!cursorr.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                product5 += cursorr.getString(1) ;
                cursorr.moveToNext();
            }
            cursorr.close();

        }


        int ddd = ' ';
        Cursor cursordd = mDb.rawQuery("SELECT * FROM date where id = 3", null);
        cursordd.moveToFirst();
        while (!cursordd.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            ddd = cursordd.getInt(1) ;
            cursordd.moveToNext();
        }
        if (ddt!=ddd){
            String lol11 = "";
            Cursor cursore333 = mDb.rawQuery("UPDATE money SET mounth = 0 WHERE id = '1'", null);
            cursore333.moveToFirst();
            while (!cursore333.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                lol11 += cursore333.getInt(1) ;
                cursore333.moveToNext();
            }
            cursore333.close();
            String product5 = "";
            Cursor cursorr = mDb.rawQuery("UPDATE date SET date = '"+ddt+"' WHERE id = '3'", null);
            cursorr.moveToFirst();
            while (!cursorr.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                product5 += cursorr.getString(1) ;
                cursorr.moveToNext();
            }
            cursorr.close();
        }




        Cursor cursord = mDb.rawQuery("SELECT * FROM date where id = 1", null);
        cursord.moveToFirst();
        while (!cursord.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            d = cursord.getInt(1) ;
            cursord.moveToNext();

        }

        int dt = ' ';
        Cursor cursords = mDb.rawQuery("SELECT * FROM date where id = 2", null);
        cursords.moveToFirst();
        while (!cursords.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            dt = cursords.getInt(1) ;
            cursords.moveToNext();
        }
        if (dt !=tt )
        { String lol11 = "";
            Cursor cursore333 = mDb.rawQuery("UPDATE money SET Week = 0 WHERE id = '1'", null);
            cursore333.moveToFirst();
            while (!cursore333.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                lol11 += cursore333.getInt(1) ;
                cursore333.moveToNext();
            }
            cursore333.close();
            String product5 = "";
            Cursor cursorr = mDb.rawQuery("UPDATE date SET date = '"+tt+"' WHERE id = '2'", null);
            cursorr.moveToFirst();
            while (!cursorr.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                product5 += cursorr.getString(1) ;
                cursorr.moveToNext();
            }
            cursorr.close();

        }

        String product7 = "";
        Cursor cursorl1 = mDb.rawQuery("SELECT * FROM time", null);
        cursorl1.moveToFirst();
        while (!cursorl1.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product7 += cursorl1.getInt(1) ;
            cursorl1.moveToNext();
        }
        cursorl1.close();

        int t = DayOfWeek;
        cursord.close();
        if(d != t){
            String lol1 = "";
            Cursor cursore33 = mDb.rawQuery("UPDATE money SET today = 0 WHERE id = '1'", null);
            cursore33.moveToFirst();
            while (!cursore33.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                lol1 += cursore33.getInt(1) ;
                cursore33.moveToNext();
            }
            cursore33.close();
            String lol = "";
            Cursor cursore = mDb.rawQuery("UPDATE sigertoday SET sigitoday = 0 WHERE id = '1'", null);
            cursore.moveToFirst();
            while (!cursore.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                lol += cursore.getInt(1) ;
                cursore.moveToNext();
            }
            cursore.close();
//            String pr = "";
//            Cursor cursore8 = mDb.rawQuery("UPDATE time SET time = time+1 WHERE id = '1'", null);
//            cursore8.moveToFirst();
//            while (!cursore8.isAfterLast()) {
//                Log.d("Tagg","Выполняется");
//                pr += cursore8.getInt(1) ;
//                cursore8.moveToNext();
//            }
//            cursore.close();
            String product8 = "";
            Cursor cursorl2 = mDb.rawQuery("SELECT * FROM time", null);
            cursorl2.moveToFirst();
            while (!cursorl2.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                product8 += cursorl2.getInt(1) ;
                cursorl2.moveToNext();
            }
            cursorl1.close();

            String product5 = "";
            Cursor cursorr = mDb.rawQuery("UPDATE date SET date = '"+t+"' WHERE id = '1'", null);
            cursorr.moveToFirst();
            while (!cursorr.isAfterLast()) {
                Log.d("Tagg","Выполняется");
                product5 += cursorr.getString(1) ;
                cursorr.moveToNext();
            }
            cursorr.close();
        }


        String product = "";

        Cursor cursor = mDb.rawQuery("SELECT * FROM siger", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product += cursor.getString(0) ;
            cursor.moveToNext();
        }
        cursor.close();

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
        double zz = vz/v;
        String result = String.format("%.1f",zz);
        cursord91.close();
        String product12 = "";
        Cursor cursor12 = mDb.rawQuery("UPDATE sigiday SET sigiday = '"+result+"'   WHERE id = '1'", null);
        cursor12.moveToFirst();
        while (!cursor12.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product12 += cursor12.getString(1) ;
            cursor12.moveToNext();
        }
        cursor12.close();
        String product13 = "";
        Cursor cursor13 = mDb.rawQuery("SELECT * FROM sigiday", null);
        cursor13.moveToFirst();
        while (!cursor13.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            product13 += cursor13.getString(1) ;
            cursor13.moveToNext();
        }
        cursor13.close();


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case 150:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
                break;
            case 151:
                Intent b = new Intent(MainActivity.this, CashActivity.class);
                startActivity(b);
                break;
            case 152:
                Intent c = new Intent(MainActivity.this, MainActivity.class);
                startActivity(c);
                break;
            case 153:
                Intent d = new Intent(MainActivity.this, CiggaActivity.class);
                startActivity(d);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    void give(){
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";
                Cursor cursor1 = mDb.rawQuery("UPDATE siger SET sigi = sigi+1   WHERE id = '1'", null);
                cursor1.moveToFirst();
                while (!cursor1.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product += cursor1.getString(0) ;
                    cursor1.moveToNext();
                }
                cursor1.close();

                String product1 = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM siger", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product1 += cursor.getString(0) ;
                    cursor.moveToNext();
                }
                cursor.close();
                String product2 = "";
                Cursor cursor2 = mDb.rawQuery("UPDATE sigertoday SET sigitoday = sigitoday+1   WHERE id = '1'", null);
                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product2 += cursor2.getString(1) ;
                    cursor2.moveToNext();
                }
                cursor2.close();
                String product3 = "";
                Cursor cursor3 = mDb.rawQuery("SELECT * FROM sigertoday", null);
                cursor3.moveToFirst();
                while (!cursor3.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product3 += cursor3.getString(1) ;
                    cursor3.moveToNext();
                }
                cursor3.close();
                double vv= ' ';

                Cursor cursord911 = mDb.rawQuery("SELECT * FROM time", null);
                cursord911.moveToFirst();
                while (!cursord911.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    vv = cursord911.getDouble(1) ;
                    cursord911.moveToNext();

                }
                double vz= ' ';

                Cursor cursord91 = mDb.rawQuery("SELECT * FROM siger", null);
                cursord91.moveToFirst();
                while (!cursord91.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    vz = cursord91.getDouble(0) ;
                    cursord91.moveToNext();

                }
                double zz = vz/vv;
                String result1 = String.format("%.1f",zz);
                cursord91.close();
                String product12 = "";
                Cursor cursor12 = mDb.rawQuery("UPDATE sigiday SET sigiday = '"+result1+"'   WHERE id = '1'", null);
                cursor12.moveToFirst();
                while (!cursor12.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product12 += cursor12.getString(1) ;
                    cursor12.moveToNext();
                }
                cursor12.close();
                String product122 = "";

                Cursor cursor122 = mDb.rawQuery("SELECT * FROM sigiday", null);
                cursor122.moveToFirst();
                while (!cursor122.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product122 += cursor122.getString(1) ;
                    cursor122.moveToNext();
                }
                cursor122.close();
                Date currentDate = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);
                String product1212 = "";
                Cursor cursor1212 = mDb.rawQuery("UPDATE Last SET times = '"+timeText+"' WHERE id = '1'", null);
                cursor1212.moveToFirst();
                while (!cursor1212.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    product1212 += cursor1212.getString(1) ;
                    cursor1212.moveToNext();
                }
                cursor1212.close();

                String sigasT = "";
                Cursor cursorka1 = mDb.rawQuery("SELECT * FROM last ", null);
                cursorka1.moveToFirst();
                while (!cursorka1.isAfterLast()) {
                    Log.d("Tagg", "Выполняется");
                    sigasT = cursorka1.getString(1);
                    cursorka1.moveToNext();
                }
                cursorka1.close();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Вы выкурили чужую сигарету", Toast.LENGTH_SHORT);
                toast.show();
                lt.setText(sigasT);
            }
        });

    }

    void shot() {

        shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = 0;
                Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga WHERE Activ = 1", null);
                cursork.moveToFirst();
                while (!cursork.isAfterLast()) {
                    Log.d("Tagg", "Выполняется");
                    l = cursork.getInt(0);
                    cursork.moveToNext();
                }
                int b = 0;
                if (l == 1){
                    Cursor cursorj = mDb.rawQuery("Select sig From Cigga WHERE Activ = 1", null);
                    cursorj.moveToFirst();
                    while (!cursorj.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        b = cursorj.getInt(0);
                        cursorj.moveToNext();
                    }
                }
                if (b > 0){
                    String product = "";
                    Cursor cursor1 = mDb.rawQuery("UPDATE Cigga SET sig = sig-1   WHERE Activ = 1", null);
                    cursor1.moveToFirst();
                    while (!cursor1.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product += cursor1.getString(0);
                        cursor1.moveToNext();
                    }
                    cursor1.close();
                    double vv = ' ';
                    Cursor cursord9 = mDb.rawQuery("SELECT * FROM Cigga Where Activ = 1", null);
                    cursord9.moveToFirst();
                    while (!cursord9.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        vv = cursord9.getDouble(5);
                        cursord9.moveToNext();

                    }
                    float zz = (float) vv;
                    String product132 = "";
                    Cursor cursor132 = mDb.rawQuery("UPDATE money SET today = today + '" + zz + "'    WHERE id = '1'", null);
                    cursor132.moveToFirst();
                    while (!cursor132.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product132 += cursor132.getString(1);
                        cursor132.moveToNext();
                    }
                    cursor132.close();
                    double z = vv;

                    String product1332 = "";
                    Cursor cursor1332 = mDb.rawQuery("UPDATE money SET  week = week + '" + zz + "'    WHERE id = '1'", null);
                    cursor1332.moveToFirst();
                    while (!cursor1332.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product1332 += cursor1332.getString(1);
                        cursor1332.moveToNext();
                    }
                    cursor1332.close();

                    String product1333 = "";
                    Cursor cursor1333 = mDb.rawQuery("UPDATE money SET  mounth = mounth + '" + zz + "'    WHERE id = '1'", null);
                    cursor1333.moveToFirst();
                    while (!cursor1333.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product1333 += cursor1333.getString(1);
                        cursor1333.moveToNext();
                    }
                    cursor1333.close();

                    String product133 = "";
                    Cursor cursor133 = mDb.rawQuery("UPDATE money SET  year = year + '" + zz + "'    WHERE id = '1'", null);
                    cursor133.moveToFirst();
                    while (!cursor133.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product133 += cursor133.getString(1);
                        cursor133.moveToNext();
                    }
                    cursor133.close();

                    String product13 = "";
                    Cursor cursor13 = mDb.rawQuery("UPDATE money SET  alltime = alltime + '" + zz + "'    WHERE id = '1'", null);
                    cursor13.moveToFirst();
                    while (!cursor13.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product133 += cursor13.getString(1);
                        cursor133.moveToNext();
                    }
                    cursor133.close();
                    String sigas = "";
                    Cursor cursorka = mDb.rawQuery("SELECT sig FROM Cigga Where Activ = 1", null);
                    cursorka.moveToFirst();
                    while (!cursorka.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        sigas = cursorka.getString(0);
                        cursorka.moveToNext();
                    }
                    cursorka.close();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Вы поделились сигаретой", Toast.LENGTH_SHORT);
                    toast.show();
                    sig.setText(sigas);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "У вас закончились сигареты!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
    void knopk(){
        knopka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = 0;
                Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga WHERE Activ = 1", null);
                cursork.moveToFirst();
                while (!cursork.isAfterLast()) {
                    Log.d("Tagg", "Выполняется");
                    l = cursork.getInt(0);
                    cursork.moveToNext();
                }
                int b = 0;
                if (l == 1){
                    Cursor cursorj = mDb.rawQuery("Select sig From Cigga WHERE Activ = 1", null);
                    cursorj.moveToFirst();
                    while (!cursorj.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        b = cursorj.getInt(0);
                        cursorj.moveToNext();
                    }
                }
                if (b > 0) {

                    Date currentDate = new Date();
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    String timeText = timeFormat.format(currentDate);
                    String product1212 = "";
                    Cursor cursor1212 = mDb.rawQuery("UPDATE Last SET times = '"+timeText+"' WHERE id = '1'", null);
                    cursor1212.moveToFirst();
                    while (!cursor1212.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product1212 += cursor1212.getString(1) ;
                        cursor1212.moveToNext();
                    }
                    cursor1212.close();

                    String sigasT = "";
                    Cursor cursorka1 = mDb.rawQuery("SELECT * FROM last ", null);
                    cursorka1.moveToFirst();
                    while (!cursorka1.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        sigasT = cursorka1.getString(1);
                        cursorka1.moveToNext();
                    }

                    cursorka1.close();

                    lt.setText(sigasT);
                    double vv1= 0;

                    Cursor cursord9 = mDb.rawQuery("SELECT * FROM Cigga Where Activ = 1", null);
                    cursord9.moveToFirst();
                    while (!cursord9.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        vv1 = cursord9.getDouble(5) ;
                        cursord9.moveToNext();
                    }
                    float zz1 = (float) vv1;
                    String product132 = "";
                    Cursor cursor132 = mDb.rawQuery("UPDATE money SET today = today + '"+zz1+"'    WHERE id = '1'", null);
                    cursor132.moveToFirst();
                    while (!cursor132.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product132 += cursor132.getString(1);
                        cursor132.moveToNext();
                    }
                    cursor132.close();
                    String product1332 = "";
                    Cursor cursor1332 = mDb.rawQuery("UPDATE money SET  week = week + '"+zz1+"'    WHERE id = '1'", null);
                    cursor1332.moveToFirst();
                    while (!cursor1332.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product1332 += cursor1332.getString(1);
                        cursor1332.moveToNext();
                    }
                    cursor1332.close();

                    String product1333 = "";
                    Cursor cursor1333 = mDb.rawQuery("UPDATE money SET  mounth = mounth + '"+zz1+"'    WHERE id = '1'", null);
                    cursor1333.moveToFirst();
                    while (!cursor1333.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product1333 += cursor1333.getString(1);
                        cursor1333.moveToNext();
                    }
                    cursor1333.close();

                    String product133 = "";
                    Cursor cursor133 = mDb.rawQuery("UPDATE money SET  year = year + '"+zz1+"'    WHERE id = '1'", null);
                    cursor133.moveToFirst();
                    while (!cursor133.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product133 += cursor133.getString(1);
                        cursor133.moveToNext();
                    }
                    cursor133.close();

                    String product13 = "";
                    Cursor cursor13 = mDb.rawQuery("UPDATE money SET  alltime = alltime + '"+zz1+"'    WHERE id = '1'", null);
                    cursor13.moveToFirst();
                    while (!cursor13.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product133 += cursor13.getString(1);
                        cursor133.moveToNext();
                    }
                    cursor133.close();

                    String product = "";
                    Cursor cursor1 = mDb.rawQuery("UPDATE siger SET sigi = sigi+1   WHERE id = '1'", null);
                    cursor1.moveToFirst();
                    while (!cursor1.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product += cursor1.getString(0) ;
                        cursor1.moveToNext();
                    }
                    cursor1.close();
                    String product22 = "";
                    Cursor cursor22 = mDb.rawQuery("UPDATE Cigga SET sig = sig-1   WHERE Activ = 1", null);
                    cursor22.moveToFirst();
                    while (!cursor1.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        product22 += cursor22.getString(0);
                        cursor22.moveToNext();
                    }
                    cursor22.close();
                    String product1 = "";

                    Cursor cursor = mDb.rawQuery("SELECT * FROM siger", null);
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product1 += cursor.getString(0) ;
                        cursor.moveToNext();
                    }
                    cursor.close();
                    String product2 = "";
                    Cursor cursor2 = mDb.rawQuery("UPDATE sigertoday SET sigitoday = sigitoday+1   WHERE id = '1'", null);
                    cursor2.moveToFirst();
                    while (!cursor2.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product2 += cursor2.getString(1) ;
                        cursor2.moveToNext();
                    }
                    cursor2.close();
                    String product3 = "";
                    Cursor cursor3 = mDb.rawQuery("SELECT * FROM sigertoday", null);
                    cursor3.moveToFirst();
                    while (!cursor3.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product3 += cursor3.getString(1) ;
                        cursor3.moveToNext();
                    }
                    cursor3.close();
                    double vv= ' ';

                    Cursor cursord911 = mDb.rawQuery("SELECT * FROM time", null);
                    cursord911.moveToFirst();
                    while (!cursord911.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        vv = cursord911.getDouble(1) ;
                        cursord911.moveToNext();

                    }
                    double vz= ' ';

                    Cursor cursord91 = mDb.rawQuery("SELECT * FROM siger", null);
                    cursord91.moveToFirst();
                    while (!cursord91.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        vz = cursord91.getDouble(0) ;
                        cursord91.moveToNext();

                    }
                    double zz = vz/vv;
                    String result1 = String.format("%.1f",zz);
                    cursord91.close();
                    String product12 = "";
                    Cursor cursor12 = mDb.rawQuery("UPDATE sigiday SET sigiday = '"+result1+"'   WHERE id = '1'", null);
                    cursor12.moveToFirst();
                    while (!cursor12.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product12 += cursor12.getString(1) ;
                        cursor12.moveToNext();
                    }
                    cursor12.close();
                    String product122 = "";

                    Cursor cursor122 = mDb.rawQuery("SELECT * FROM sigiday", null);
                    cursor122.moveToFirst();
                    while (!cursor122.isAfterLast()) {
                        Log.d("Tagg","Выполняется");
                        product122 += cursor122.getString(1) ;
                        cursor122.moveToNext();
                    }
                    cursor122.close();
                    String sigas = "";
                    Cursor cursorka = mDb.rawQuery("SELECT sig FROM Cigga Where Activ = 1", null);
                    cursorka.moveToFirst();
                    while (!cursorka.isAfterLast()) {
                        Log.d("Tagg", "Выполняется");
                        sigas = cursorka.getString(0);
                        cursorka.moveToNext();
                    }
                    cursorka.close();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Вы выкурили свою сигарету", Toast.LENGTH_SHORT);
                    toast.show();
                    sig.setText(sigas);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "У вас закончились сигареты!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}