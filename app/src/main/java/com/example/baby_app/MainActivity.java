package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button library, park, toilet, museum_art, perform, field_study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = (Button) findViewById(R.id.library);
        park = (Button) findViewById(R.id.park);
        toilet = (Button) findViewById(R.id.toilet);
        museum_art = (Button) findViewById(R.id.museum_art);
        perform = (Button) findViewById(R.id.perform);
        field_study = (Button) findViewById(R.id.field_study);

        library.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), libraryActivity.class);
                        startActivity(intent);
                    }
                }
        );

        park.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), parkActivity.class);
                        startActivity(intent);
                    }
                }
        );

        perform.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), performActivity.class);
                        startActivity(intent);
                    }
                }
        );

        museum_art.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), museum_artActivity.class);
                        startActivity(intent);
                    }
                }
        );

        toilet.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), sdetailActivity.class);
                        startActivity(intent);
                    }
                }
        );

        field_study.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), field_studyActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
