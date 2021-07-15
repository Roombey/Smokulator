package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class dobpachki extends AppCompatActivity {
Button back;
Button add;
TextInputEditText namep;
TextInputEditText pricep;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobpachki);
        back=findViewById(R.id.menu1);
        add=findViewById(R.id.addp);
        pricep=findViewById(R.id.pricep);
        namep=findViewById(R.id.namep);
        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dobpachki.this, CiggaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = 0;
                Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga", null);
                cursork.moveToFirst();
                while (!cursork.isAfterLast()) {
                    Log.d("Tagg","Выполняется");
                    l = cursork.getInt(0) ;
                    cursork.moveToNext();
                }
                String n = namep.getText().toString();
                String p = pricep.getText().toString();
                double p1 = Double.parseDouble(p);
                ContentValues cv = new ContentValues();
                cv.put("ID",l+1);
                cv.put("Name", ""+n+"");
                cv.put("Price",""+p1+"");
                if(l == 0) {
                    cv.put("Activ", "1");
                }
                else{
                    cv.put("Activ", "0");
                }
                cv.put("sig", "20");
                cv.put("odna", p1/20);
                mDb.insert("Cigga", null, cv);

                  Intent intent = new Intent(dobpachki.this, CiggaActivity.class);
                  startActivityForResult(intent, 1);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Новая пачка добавлена", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

//        pricep.clearFocus();
        namep.requestFocus();
        pricep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pricep.requestFocus();
                pricep.isFocused();
                pricep.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });
        namep.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean consumed = false;
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    namep.clearFocus();
                    pricep.callOnClick();
//                    pricep.requestFocus();
//                    pricep.isFocused();
                    consumed = true;
                }
                return consumed;
            }
        });
        pricep.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean consumed = false;
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    pricep.setInputType(InputType.TYPE_NULL);
                    pricep.clearFocus();
                    consumed = true;
                }
                return consumed;
            }
        });

    }
}