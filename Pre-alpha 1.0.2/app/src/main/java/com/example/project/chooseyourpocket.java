package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class chooseyourpocket extends AppCompatActivity {
Button nazad;
ListView pachki;
    private ArrayAdapter<String> mAdapter;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseyourpocket);
        nazad=findViewById(R.id.nazad);
        pachki=findViewById(R.id.listv);
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
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chooseyourpocket.this, CiggaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        int l = 0;
        Cursor cursork = mDb.rawQuery("Select Count(*) From Cigga", null);
        cursork.moveToFirst();
        while (!cursork.isAfterLast()) {
            Log.d("Tagg","Выполняется");
            l = cursork.getInt(0) ;
            cursork.moveToNext();
        }
        String d, v, dv;
        String[] dva = new String[l];
        Cursor cursord = mDb.rawQuery("SELECT Name, Price FROM Cigga", null);
        cursord.moveToFirst();
        int i = 0;
        int j = 1;
            while (!cursord.isAfterLast() && i < l) {
                Log.d("Tagg", "Выполняется");
                d = cursord.getString(0);
                mDb.execSQL("UPDATE Cigga SET ID = "+j+" Where Name = '"+d+"'");
                j++;
                v = cursord.getString(1);
                dv = d + " " + v + "₽";
                dva[i] = dv;
                i++;
                cursord.moveToNext();
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_3, dva);
        pachki.setAdapter(adapter);
        int finalL = l+1;
        pachki.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position++;
                for (int i = 1; i < finalL; i++) {
                    mDb.execSQL("UPDATE Cigga SET Activ = 0 WHERE ID = "+ i);
                }
                mDb.execSQL("UPDATE Cigga SET Activ = 1 WHERE ID = "+position);
                Intent intent = new Intent(chooseyourpocket.this, CiggaActivity.class);
                startActivityForResult(intent, 1);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Активная пачка выбрана", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}