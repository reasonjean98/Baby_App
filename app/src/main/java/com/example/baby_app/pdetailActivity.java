package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class pdetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Button Map, Detail;
    LinearLayout Detail_layout, Map_layout;
    myDBHelper myHelper;
    SQLiteDatabase db;
    TextView perform_name, perform_date, perform_time, perform_home, perform_age, perform_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetail);

        myHelper = new myDBHelper(this);
        perform_name = (TextView) findViewById(R.id.perform_name);
        perform_time = (TextView) findViewById(R.id.perform_time);
        perform_home = (TextView) findViewById(R.id.perform_home);
        perform_date = (TextView) findViewById(R.id.perform_date);
        perform_age = (TextView) findViewById(R.id.perform_age);
        perform_address = (TextView) findViewById(R.id.perform_address);
        Detail_layout = (LinearLayout) findViewById(R.id.Detail_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Detail_layout.setVisibility(View.VISIBLE);
        String var = getIntent().getStringExtra("name");

        db = myHelper.getReadableDatabase();
        final Cursor cursor_w;
        cursor_w = db.rawQuery("SELECT * FROM perform WHERE perform_name = ('" + var + "');", null);
        cursor_w.moveToFirst();

        if (cursor_w.getCount() > 0) {
            perform_name.setText(cursor_w.getString(0));
            perform_date.setText(cursor_w.getString(1));
            perform_home.setText(cursor_w.getString(2));
            perform_age.setText(cursor_w.getString(3));
            perform_time.setText(cursor_w.getString(4));
            perform_address.setText(cursor_w.getString(6));
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
        final Geocoder g = new Geocoder(this);
        List<Address> address = null;
        db = myHelper.getReadableDatabase();
        final Cursor cursor_w;
        String var = getIntent().getStringExtra("name");
        cursor_w = db.rawQuery("SELECT * FROM perform WHERE perform_name = ('" + var + "');", null);
        cursor_w.moveToFirst();

        List<Address> list = null;

        for (int i = 0; i < cursor_w.getCount(); i++) {
            String title = cursor_w.getString(0);
            String location = cursor_w.getString(6);
            try {
                list = g.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LatLng kunsan = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(kunsan);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(kunsan));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15));
            cursor_w.moveToNext();
        }
    }
}