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

public class sdetailActivity extends AppCompatActivity {

    Button Map, Detail;
    LinearLayout Detail_layout, Map_layout;
    myDBHelper myHelper;
    SQLiteDatabase db;
    TextView country_name, program_name, address, phone, homepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdetail);

        myHelper = new myDBHelper(this);
        country_name = (TextView) findViewById(R.id.country_name);
        program_name = (TextView) findViewById(R.id.program_name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        homepage = (TextView) findViewById(R.id.homepage);
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
                cursor_w = db.rawQuery("SELECT * FROM study;", null);
                cursor_w.moveToFirst();

                if(cursor_w.getCount() > 0 ){
                    country_name.setText(cursor_w.getString(0));
                    program_name.setText(cursor_w.getString(2));
                    address.setText(cursor_w.getString(3));
                    homepage.setText(cursor_w.getString(6));
                    phone.setText(cursor_w.getString(7));

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