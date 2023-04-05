package com.example.deezerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Musique> musiques = new ArrayList<Musique>();
        musiques.add(new Musique("Manny and Nellie's Theme",
                "Babylon",
                "Justin Hurwitz",
                "https://e-cdns-images.dzcdn.net/images/cover/536ad416397f2c3480f7782bb740dccb/250x250-000000-80-0-0.jpg",
                "https://cdns-preview-a.dzcdn.net/stream/c-add95428ef6c56f8576e0f6bcf95c978-5.mp3",
                "https://www.deezer.com/track/2048083707"));

        musiques.add(new Musique("Draxter",
                ".RAW",
                "Laylow",
                "https://e-cdns-images.dzcdn.net/images/cover/98d557207991e40a192b196976d6adfb/250x250-000000-80-0-0.jpg",
                "https://cdns-preview-8.dzcdn.net/stream/c-8ffdbc8e82016ebf8c7c3139ac9e2520-2.mp3",
                "https://www.deezer.com/track/1794965237"));

        ListView listViewSearch = findViewById(R.id.listViewSearch);
        MusiqueBaseAdapter adapter = new MusiqueBaseAdapter(musiques,this);

        listViewSearch.setAdapter(adapter);
    }
}