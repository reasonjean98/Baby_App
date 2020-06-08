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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class performActivity extends AppCompatActivity {

    private GoogleMap mMap;
    Spinner spinner_age, spinner_category2;
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
        spinner_age = (Spinner) findViewById(R.id.spinner_age);
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

        for (int i = 0; i < cursor_w.getCount(); i++) {
            String perform_name = cursor_w.getString(0);
            age_arraylist.add(perform_name);
            age_adapter.notifyDataSetChanged();
            cursor_w.moveToNext();
        }

        cursor_w.close();
        db.close();

        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (spinner_age.getSelectedItem().toString().equals("만 5세 이상")) {
                    Toast.makeText(getApplicationContext(), spinner_age.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = "만 5세 이상";
                    String var1 = "만 6세 이상";
                    String var2 = "6세 이상";
                    String var3 = "7세 이상";
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE age like ('" + var + "') or age like ('" + var1 + "') or age like ('" + var2 + "') or age like ('" + var3 + "');", null);
                    cursor_w.moveToFirst();
                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                } else if (spinner_age.getSelectedItem().toString().equals("전체관람가")) {
                    Toast.makeText(getApplicationContext(), spinner_age.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    cursor_w = db.rawQuery("SELECT * FROM perform;", null);
                    cursor_w.moveToFirst();
                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                } else if (spinner_age.getSelectedItem().toString().equals("5세 이하")) {
                    Toast.makeText(getApplicationContext(), spinner_age.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = "3세 이상";
                    String var1 = "4세 이상";
                    String var2 = "5세 이상";
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE age like ('" + var + "') or age like ('" + var1 + "') or age like ('" + var2 + "');", null);
                    cursor_w.moveToFirst();
                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                } else if (spinner_age.getSelectedItem().toString().equals("만 7세 이상")) {
                    Toast.makeText(getApplicationContext(), spinner_age.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = "만 7세 이상";
                    String var1 = "8세 이상";
                    String var2 = "만 8세 이상";
                    String var3 = "9세 이상";
                    String var4 = "만 9세 이상";
                    String var5 = "10세 이상";
                    String var6 = "만 10세 이상";
                    String var7 = "11세 이상";
                    String var8 = "만 11세 이상";
                    String var9 = "12세 이상";
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE age like ('" + var + "') or age like ('" + var1 + "') or age like ('" + var2 + "') or age like ('" + var3 + "') or age like ('" + var4 + "') or age like ('" + var5 + "') or age like ('" + var6 + "') or age like ('" + var7 + "') or age like ('" + var8 + "') or age like ('" + var9 + "');", null);
                    cursor_w.moveToFirst();
                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                } else if (spinner_age.getSelectedItem().toString().equals("만 12세 이상")) {
                    Toast.makeText(getApplicationContext(), spinner_age.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = "만 12세 이상";
                    String var1 = "13세 이상";
                    String var2 = "만 13세 이상";
                    String var3 = "14세 이상";
                    String var4 = "만 14세 이상";
                    String var5 = "15세 이상";
                    String var6 = "만 15세 이상";
                    String var7 = "16세 이상";
                    String var8 = "만 16세 이상";
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE age like ('" + var + "') or age like ('" + var1 + "') or age like ('" + var2 + "') or age like ('" + var3 + "') or age like ('" + var4 + "') or age like ('" + var5 + "') or age like ('" + var6 + "') or age like ('" + var7 + "') or age like ('" + var8 + "');", null);
                    cursor_w.moveToFirst();
                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_category2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(spinner_category2.getSelectedItem().toString().equals("뮤지컬")){
                    Toast.makeText(getApplicationContext(), spinner_category2.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String category = spinner_category2.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE category like ('" + category + "%" + "');", null);
                    cursor_w.moveToFirst();

                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }else if(spinner_category2.getSelectedItem().toString().equals("연극")){
                    Toast.makeText(getApplicationContext(), spinner_category2.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String category = spinner_category2.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM perform WHERE category like ('" + category + "%" + "');", null);
                    cursor_w.moveToFirst();

                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }else if(spinner_category2.getSelectedItem().toString().equals("분야별")){
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String category = spinner_category2.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM perform;", null);
                    cursor_w.moveToFirst();

                    age_arraylist.clear();
                    for (int i = 0; i < cursor_w.getCount(); i++) {
                        String perform_name = cursor_w.getString(0);
                        age_arraylist.add(perform_name);
                        age_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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


    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "Baby_app.db", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
