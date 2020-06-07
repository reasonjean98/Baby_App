package com.example.baby_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class parkActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner1, spinner2;
    myDBHelper myHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myHelper = new myDBHelper(this);
        db = myHelper.getReadableDatabase();
        spinner1 = (Spinner) findViewById(R.id.spinner_location1);
        spinner2 = (Spinner) findViewById(R.id.spinner_category);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (parent.getItemAtPosition(position).toString().equals("거리별")) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (parent.getItemAtPosition(position).toString().equals("전체")) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() + "클릭하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

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


    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        db = myHelper.getReadableDatabase();
        final Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM park;", null);
        cursor_w.moveToFirst();

        for (int i = 0; i < cursor_w.getCount(); i++) {
            String title = cursor_w.getString(0);
            float latitude = cursor_w.getFloat(4);
            float longitude = cursor_w.getFloat(5);
            LatLng all = new LatLng(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(all);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            cursor_w.moveToNext();
        }
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(parkActivity.this);
                dlg.setTitle(marker.getTitle());
                final Cursor cursor_d;
                db = myHelper.getReadableDatabase();
                cursor_d = db.rawQuery("SELECT * FROM park WHERE park_name like '" + marker.getTitle() + "';", null);
                cursor_d.moveToFirst();
                dlg.setMessage("\n" + "공원 구분 : " + cursor_d.getString(1) + "\n"
                        + "도로 주소 : " + cursor_d.getString(2) + "\n"
                        + "지번 주소 : " + cursor_d.getString(3) + "\n"
                        + "전화번호 : " + cursor_d.getString(6));

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(parkActivity.this,"확인을 눌르셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        LatLng kunsan = new LatLng(37.6137327, 126.6968904);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(kunsan);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kunsan));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 13));

        cursor_w.close();
        db.close();
    }
}
