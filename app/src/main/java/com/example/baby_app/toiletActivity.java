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

public class toiletActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner;
    myDBHelper myHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myHelper = new myDBHelper(this);
        db = myHelper.getReadableDatabase();
        spinner = (Spinner) findViewById(R.id.spinner_location3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("거리별")){
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                }else {
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

        cursor_w = db.rawQuery("SELECT * FROM toilet order by random() limit 2000;", null);
        cursor_w.moveToFirst();

        for (int i = 0; i < cursor_w.getCount() ; i++) {
            String title = cursor_w.getString(0);
            float latitude = cursor_w.getFloat(16);
            float longitude = cursor_w.getFloat(17);
            LatLng kunsan = new LatLng(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(kunsan);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            cursor_w.moveToNext();

            LatLng my = new LatLng(37.5652894, 126.849467);
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.position(my).title("내 위치");
            mMap.addMarker(markerOptions2).showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(my));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions2.getPosition(), 11));

        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(toiletActivity.this);
                dlg.setTitle(marker.getTitle());
                final Cursor cursor_d;
                db = myHelper.getReadableDatabase();
                cursor_d = db.rawQuery("SELECT * FROM toilet WHERE toilet_name like '" + marker.getTitle() + "';", null);
                cursor_d.moveToFirst();
                dlg.setMessage("구분 : " + cursor_d.getString(1) + "\n"
                        + "도로 주소 : " + cursor_d.getString(2) + "\n"
                        + "지번 주소 : " + cursor_d.getString(3) + "\n"
                        + "남녀공용 : " + cursor_d.getString(4) + "\n"
                        + "남성 대변 및 소변 기수 : " + cursor_d.getString(5) + "개   " + cursor_d.getString(6) + "개" + "\n"
                        + "남성 어린이 대변 및 소변 기수 : " + cursor_d.getString(9) + "개    " + cursor_d.getString(10) + "개" + "\n"
                        + "여성 대변기수 : " + cursor_d.getString(11) + "개" + "\n"
                        + "여성 어린이용대변기수 : " + cursor_d.getString(13) + "개" + "\n"
                        + "전화번호 : " + cursor_d.getString(14) + "\n"
                        + "개방시간 : " + cursor_d.getString(15) + "\n");

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(toiletActivity.this,"확인을 누르셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        cursor_w.close();
        db.close();
    }
}
