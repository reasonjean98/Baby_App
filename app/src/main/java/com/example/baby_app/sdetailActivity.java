package com.example.baby_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class sdetailActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdetail);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("Detail").setIndicator("상세정보보기");
        tabSpecSong.setContent(R.id.Detail);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("Map").setIndicator("지도보기");
        tabSpecArtist.setContent(R.id.Map);
        tabHost.addTab(tabSpecArtist);

        tabHost.setCurrentTab(0);
    }
}