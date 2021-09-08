package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class CiggaActivity extends AppCompatActivity {
    Button menu;
    Button add;
    Button choose;
    Button delete;
    Button renew;
    TextView tv1, tv2, tv3, tv4;
    DatabaseHelper mDBHelper;
    boolean lol = false;
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigga);
        menu = findViewById(R.id.menu);
        add=findViewById(R.id.add);
        choose=findViewById(R.id.choose);
        renew = findViewById(R.id.renew);
        tv1 = findViewById(R.id.textViews);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        EditText etee = new EditText(this);
        etee.setInputType(InputType.TYPE_CLASS_NUMBER);
        tv4 = findViewById(R.id.textView4);
        delete = findViewById(R.id.deletecig);
        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        AlertDialog.Builder builder = new AlertDialog.Builder(CiggaActivity.this);
        View inputEditTextField = etee;

        builder.setMessage("Изменение количества сигарет:")
                .setView(inputEditTextField)
                .setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Добавить 20 сигарет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mDb.execSQL("UPDATE Cigga Set sig = sig + 20 Where Activ = 1");
//                        Intent intent = new Intent(CiggaActivity.this, CiggaActivity.class);
//                        startActivityForResult(intent, 1);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Добавлено 20 сигарет", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                })
                .setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String editTextInput = ((EditText) inputEditTextField).getText().toString();
                        mDb.execSQL("UPDATE Cigga Set sig = " + editTextInput + " Where Activ = 1");
//                        Intent intent = new Intent(CiggaActivity.this, CiggaActivity.class);
//                        startActivityForResult(intent, 1);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Количество сигарет изменено", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
        renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = 0;
                Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga WHERE Activ = 1", null);
                cursork.moveToFirst();
                while (!cursork.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    l = cursork.getInt(0) ;
                    cursork.moveToNext();
                }

                if(l == 1) {
                    builder.show();

                String sig = "";
                Cursor cursord = mDb.rawQuery("SELECT sig From Cigga Where Activ = 1", null);
                cursord.moveToFirst();
                sig = cursord.getString(0);
                if (sig != "") {
                    tv2.setText(sig);
                }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет активной пачки!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CiggaActivity.this, TestActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CiggaActivity.this, delete.class);
                startActivityForResult(intent, 1);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CiggaActivity.this, dobpachki.class);
                startActivityForResult(intent, 1);
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CiggaActivity.this, chooseyourpocket.class);
                startActivityForResult(intent, 1);
            }

        });
        int l = 0;
        Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga WHERE Activ = 1", null);
        cursork.moveToFirst();
        while (!cursork.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            l = cursork.getInt(0) ;
            cursork.moveToNext();
        }
        if(l == 1) {
            String name = "";
            Cursor cursorm = mDb.rawQuery("SELECT Name From Cigga Where Activ = 1", null);
            cursorm.moveToFirst();
            name = cursorm.getString(0);
            if (name != "") {
                tv1.setText(name);
            }
            String price = "";
            Cursor cursors = mDb.rawQuery("SELECT Price From Cigga Where Activ = 1", null);
            cursors.moveToFirst();
            price = cursors.getString(0);
            price = price + "₽";
            if (price != "₽") {
                tv3.setText(price);
            }
            String sig = "";
            Cursor cursord = mDb.rawQuery("SELECT sig From Cigga Where Activ = 1", null);
            cursord.moveToFirst();
            sig = cursord.getString(0);
            if (sig != "") {
                tv2.setText(sig);
            }
            String odna = "";
            Cursor cursorb = mDb.rawQuery("SELECT odna From Cigga Where Activ = 1", null);
            cursorb.moveToFirst();
            odna = cursorb.getString(0);
            odna = odna + "₽";
            if (odna != "₽") {
                tv4.setText(odna);
            }
        }
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case 150:
                Intent i = new Intent(CiggaActivity.this, SettingsActivity.class);
                startActivity(i);
                break;
            case 151:
                Intent b = new Intent(CiggaActivity.this, CashActivity.class);
                startActivity(b);
                break;
            case 152:
                Intent c = new Intent(CiggaActivity.this, MainActivity.class);
                startActivity(c);
                break;
            case 153:
                Intent d = new Intent(CiggaActivity.this, CiggaActivity.class);
                startActivity(d);
                break;
            case 154:
                Intent e = new Intent(CiggaActivity.this, Journal.class);
                startActivity(e);
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}