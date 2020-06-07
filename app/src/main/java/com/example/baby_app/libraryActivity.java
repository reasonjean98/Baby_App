package com.example.baby_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class libraryActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner;
    myDBHelper myHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        myHelper = new myDBHelper(this);
        db = myHelper.getReadableDatabase();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString().equals("거리별")) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                } else if(parent.getItemAtPosition(position).toString().equals("200m")){
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
        cursor_w = db.rawQuery("SELECT * FROM library;", null);
        cursor_w.moveToFirst();

        for (int i = 0; i < cursor_w.getCount(); i++) {

            final String title = cursor_w.getString(0);
            float latitude = cursor_w.getFloat(9);
            float longitude = cursor_w.getFloat(10);

            LatLng kunsan = new LatLng(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(kunsan);
            markerOptions.title(title);


            mMap.addMarker(markerOptions);
            cursor_w.moveToNext();

        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(libraryActivity.this);
                dlg.setTitle(marker.getTitle());
                final Cursor cursor_d;
                db = myHelper.getReadableDatabase();
                cursor_d = db.rawQuery("SELECT * FROM library WHERE library_name like '" + marker.getTitle() + "';", null);
                cursor_d.moveToFirst();
                dlg.setMessage("\n" + "평일 운영 시작 : " + cursor_d.getString(3) + "\n"
                        + "평일 운영 종료 : " + cursor_d.getString(4) + "\n"
                        + "토요일 운영 시작 : " + cursor_d.getString(5) + "\n"
                        + "토요일 운영 종료 : " + cursor_d.getString(6) + "\n"
                        + "전화번호 : " + cursor_d.getString(8) + "\n"
                        + "홈페이지 : " + cursor_d.getString(11));

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(libraryActivity.this,"확인을 눌르셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        LatLng kunsan = new LatLng(37.5004448, 126.7499806);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(kunsan);
        mMap.addMarker(markerOptions);
        markerOptions.title("내 위치!!!!!!!!!!!!!!!!");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kunsan));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 13));

        cursor_w.close();
        db.close();
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);

    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);

    }



}