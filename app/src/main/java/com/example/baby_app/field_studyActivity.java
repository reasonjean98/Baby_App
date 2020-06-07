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
        final Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM study;", null);

        cursor_w.moveToFirst();

        for (int i = 0; i < cursor_w.getCount(); i++) {
            String study_name = cursor_w.getString(0);
            study_arraylist.add(study_name);
            study_adapter.notifyDataSetChanged();
            cursor_w.moveToNext();
        }

        cursor_w.close();
        db.close();

        study_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), sdetailActivity.class);
                intent.putExtra("study", study_arraylist.get(position));
                startActivity(intent);
            }
        });

        spinner_category1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(spinner_category1.getSelectedItem().toString().equals("만들기체험")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_category1.getSelectedItem().toString().equals("전통문화체험")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_category1.getSelectedItem().toString().equals("전통음식체험")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_category1.getSelectedItem().toString().equals("어촌생활체험")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_category1.getSelectedItem().toString().equals("농작물경작체험")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_category1.getSelectedItem().toString().equals("기타")){
                    Toast.makeText(getApplicationContext(), spinner_category1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_category1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE program_selection like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
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

        spinner_city1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(spinner_city1.getSelectedItem().toString().equals("경기도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("강원도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("경상북도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("경상남도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("전라북도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("전라남도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var1 = spinner_city1.getSelectedItem().toString();
                    String var2 = "광주광역시";
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var1 + "') or City_name like ('" + var2 + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("충청남도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("충청북도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = spinner_city1.getSelectedItem().toString();
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
                        cursor_w.moveToNext();
                    }

                    cursor_w.close();
                    db.close();
                }
                else if(spinner_city1.getSelectedItem().toString().equals("제주도")){
                    Toast.makeText(getApplicationContext(), spinner_city1.getSelectedItem().toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    db = myHelper.getReadableDatabase();
                    final Cursor cursor_w;
                    String var = "제주특별자치도";
                    cursor_w = db.rawQuery("SELECT * FROM study WHERE City_name like ('" + var + "');", null);
                    cursor_w.moveToFirst();
                    study_arraylist.clear();
                    for(int i = 0; i < cursor_w.getCount(); i++){
                        String perform_name = cursor_w.getString(0);
                        study_arraylist.add(perform_name);
                        study_adapter.notifyDataSetChanged();
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
