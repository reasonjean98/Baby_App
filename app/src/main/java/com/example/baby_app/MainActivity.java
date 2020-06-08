package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button library, park, toilet, museum_art, perform, field_study;
    EditText search;
    Button search_btn;
    myDBHelper myHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new myDBHelper(this);
        search_btn = (Button) findViewById(R.id.search_btn);
        search = (EditText) findViewById(R.id.search);
        library = (Button) findViewById(R.id.library);
        park = (Button) findViewById(R.id.park);
        toilet = (Button) findViewById(R.id.toilet);
        museum_art = (Button) findViewById(R.id.museum_art);
        perform = (Button) findViewById(R.id.perform);
        field_study = (Button) findViewById(R.id.field_study);


        search_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String text = search.getText().toString();
                db = myHelper.getReadableDatabase();
                final Cursor cursor_w;
                cursor_w = db.rawQuery("SELECT Country_name FROM study UNION SELECT perform_name FROM perform UNION SELECT toilet_name FROM toilet UNION SELECT library_name FROM library UNION SELECT name FROM museum_art UNION SELECT park_name FROM park;", null);
                cursor_w.moveToFirst();

                for (int i = 0; i < cursor_w.getCount(); i++) {
                    String a = cursor_w.getString(0);
                    final Cursor cursor_d;
                    if(text.equals(a)){
                        db = myHelper.getReadableDatabase();
                        cursor_d = db.rawQuery("SELECT Country_name FROM study UNION SELECT perform_name FROM perform UNION SELECT toilet_name FROM toilet UNION SELECT library_name FROM library UNION SELECT name FROM museum_art UNION SELECT park_name FROM park;", null);
                        cursor_w.moveToFirst();

                    }
                    cursor_w.moveToNext();
                }

                cursor_w.close();
                db.close();
            }
        });


        library.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), libraryActivity.class);
                        startActivity(intent);
                    }
                }
        );

        park.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), parkActivity.class);
                        startActivity(intent);
                    }
                }
        );

        perform.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), performActivity.class);
                        startActivity(intent);
                    }
                }
        );

        museum_art.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), museum_artActivity.class);
                        startActivity(intent);
                    }
                }
        );

        toilet.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), toiletActivity.class);
                        startActivity(intent);
                    }
                }
        );

        field_study.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), field_studyActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    public class myDBHelper extends SQLiteOpenHelper {
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
