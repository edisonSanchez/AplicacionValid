package com.poc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String urlArtist = "https://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&format=json";
    private String urlTracks = "https://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=829751643419a7128b7ada50de590067&format=json";
    private String titleArtist = "Top Artistas";
    private String titleTracks = "Top Canciones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTracks = findViewById(R.id.btn_tracks);
        Button btnArtists = findViewById(R.id.btn_artists);
        btnArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verTop = new Intent(MainActivity.this, DetailsTop.class);
                verTop.putExtra("title", titleArtist);
                verTop.putExtra("url", urlArtist);
                startActivity(verTop);
            }
        });

        btnTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verTop = new Intent(MainActivity.this, DetailsTop.class);
                verTop.putExtra("title", titleTracks);
                verTop.putExtra("url", urlTracks);
                startActivity(verTop);
            }
        });
    }
}
