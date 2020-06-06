package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class pdetailActivity extends AppCompatActivity {

    Button Map, Detail;
    LinearLayout Detail_layout, Map_layout;
    myDBHelper myHelper;
    SQLiteDatabase db;
    TextView perform_name, perform_date, perform_time, perform_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetail);

        myHelper = new myDBHelper(this);
        perform_name = (TextView) findViewById(R.id.perform_name);
        perform_time = (TextView) findViewById(R.id.perform_time);
        perform_home = (TextView) findViewById(R.id.perform_home);
        perform_date = (TextView) findViewById(R.id.perform_date);
        Map = (Button) findViewById(R.id.Map);
        Detail = (Button) findViewById(R.id.Detail);
        Detail_layout = (LinearLayout) findViewById(R.id.Detail_layout);
        Map_layout = (LinearLayout) findViewById(R.id.Map_layout);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detail_layout.setVisibility(View.GONE);
                Map_layout.setVisibility(View.VISIBLE);
            }
        });

        Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detail_layout.setVisibility(View.VISIBLE);
                Map_layout.setVisibility(View.GONE);
                String var = getIntent().getStringExtra("name");

                db = myHelper.getReadableDatabase();
                final Cursor cursor_w;
                cursor_w = db.rawQuery("SELECT * FROM perform WHERE perform_name = ('" + var + "');", null);
                cursor_w.moveToFirst();

                if(cursor_w.getCount() > 0 ){
                    perform_name.setText(cursor_w.getString(0));
                    perform_date.setText(cursor_w.getString(1));
                    perform_home.setText(cursor_w.getString(2));
                    perform_time.setText(cursor_w.getString(4));

                }

                cursor_w.close();
                db.close();


            }
        });
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
