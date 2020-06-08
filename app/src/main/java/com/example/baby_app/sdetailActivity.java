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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class sdetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
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
        Detail_layout = (LinearLayout) findViewById(R.id.Detail_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Detail_layout.setVisibility(View.VISIBLE);
        String var = getIntent().getStringExtra("study");

        db = myHelper.getReadableDatabase();
        final Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM study WHERE Country_name = ('" + var + "');", null);
        cursor_w.moveToFirst();

        if (cursor_w.getCount() > 0) {
            country_name.setText(cursor_w.getString(0));
            program_name.setText(cursor_w.getString(2));
            address.setText(cursor_w.getString(3));
            homepage.setText(cursor_w.getString(6));
            phone.setText(cursor_w.getString(7));

        }

        cursor_w.close();
        db.close();


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
        String var = getIntent().getStringExtra("study");
        cursor_w = db.rawQuery("SELECT * FROM study WHERE Country_name = ('" + var + "');", null);
        cursor_w.moveToFirst();

        for (int i = 0; i < cursor_w.getCount(); i++) {
            String title = cursor_w.getString(0);
            float latitude = cursor_w.getFloat(4);
            float longitude = cursor_w.getFloat(5);
            LatLng kunsan = new LatLng(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(kunsan);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(kunsan));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15));
            cursor_w.moveToNext();
        }


        cursor_w.close();
        db.close();
    }

}