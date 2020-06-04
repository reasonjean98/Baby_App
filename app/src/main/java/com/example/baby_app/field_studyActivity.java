package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class field_studyActivity extends AppCompatActivity {

    Spinner spinner_category1, spinner_city1;
    myDBHelper myHelper;
    SQLiteDatabase db;
    ListView study_listview;
    ArrayList<String> study_arraylist;
    ArrayAdapter<String> study_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_study);

        spinner_category1 = (Spinner) findViewById(R.id.spinner_category1);
        spinner_city1 = (Spinner) findViewById(R.id.spinner_city1);
        myHelper = new myDBHelper(this);
        study_arraylist = new ArrayList<String>();
        study_listview = (ListView) findViewById(R.id.study_listview);
        study_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, study_arraylist);
        study_listview.setAdapter(study_adapter);

        db = myHelper.getReadableDatabase();
        Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM study;", null);

        cursor_w.moveToFirst();

        for(int i = 0; i < cursor_w.getCount(); i++){
            String study_name = cursor_w.getString(0);
            study_arraylist.add(study_name);
            study_adapter.notifyDataSetChanged();
            cursor_w.moveToNext();
        }

        cursor_w.close();
        db.close();
    }



    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context) {
            super(context, "Baby_app.db", null, 1);
        }
        public void onCreate(SQLiteDatabase db){

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
