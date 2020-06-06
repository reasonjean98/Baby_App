package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class sdetailActivity extends AppCompatActivity {
    Button Map, Detail;
    LinearLayout Detail_layout, Map_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdetail);

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
            }
        });
    }


}