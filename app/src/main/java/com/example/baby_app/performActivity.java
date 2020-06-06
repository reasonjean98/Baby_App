package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class performActivity extends AppCompatActivity {

    Spinner spinner_age,spinner_category2;
    myDBHelper myHelper;
    SQLiteDatabase db;
    ListView age_listview;
    ArrayList<String> age_arraylist;
    ArrayAdapter<String> age_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform);

        spinner_category2 = (Spinner) findViewById(R.id.spinner_category2);
        spinner_age = (Spinner) findViewById(R.id.spinner);
        myHelper = new myDBHelper(this);
        age_listview = (ListView) findViewById(R.id.age_listview);
        age_arraylist = new ArrayList<String>();
        age_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, age_arraylist);
        age_listview.setAdapter(age_adapter);

        // 처음 들어와서 전체 리스트 보여주기 위함
        db = myHelper.getReadableDatabase();
        final Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM perform;", null);

        cursor_w.moveToFirst();

        for(int i = 0; i < cursor_w.getCount(); i++){
            String perform_name = cursor_w.getString(0);
            age_arraylist.add(perform_name);
            age_adapter.notifyDataSetChanged();
            cursor_w.moveToNext();
        }

        cursor_w.close();
        db.close();

        // 리스트에 있는 요소 클릭 시 상세보기 페이지로 넘어가게 함
        age_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), pdetailActivity.class);
                intent.putExtra("name", age_arraylist.get(position));
                startActivity(intent);
            }
        });
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
