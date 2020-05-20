package com.example.baby_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class libraryActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spinner = (Spinner) findViewById(R.id.spinner);

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


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng kunsan = new LatLng(35.945287,  126.682163);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(kunsan);
        markerOptions.title("군산시립도서관");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(kunsan));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

}