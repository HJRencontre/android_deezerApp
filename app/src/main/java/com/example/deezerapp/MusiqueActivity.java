package com.example.deezerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MusiqueActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewMusic, textViewArtiste, textViewAlbum;
    private ImageView imageViewCover;
    private Button buttonPlay, buttonLink;
    private MediaPlayer player = new MediaPlayer();

    private Musique musique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musique);

        textViewMusic = findViewById(R.id.textViewMusic);
        textViewArtiste = findViewById(R.id.textViewArtiste);
        textViewAlbum = findViewById(R.id.textViewAlbum);

        imageViewCover = findViewById(R.id.imageViewCover);

        buttonPlay = findViewById(R.id.buttonEcouter);
        buttonLink = findViewById(R.id.buttonLinkDeezer);

        buttonPlay.setOnClickListener(this);
        buttonLink.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        musique = (Musique) getIntent().getSerializableExtra("selectedMusique");
        textViewMusic.setText(musique.getTitre());
        textViewArtiste.setText(musique.getArtiste());
        textViewAlbum.setText(musique.getAlbum());

        ServicesAPI.loadImage(this,musique.getCover(), imageViewCover);

    }

    @Override
    public void onClick(View view) {
        if (view.equals(buttonLink)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(musique.getLink()));
            startActivity(intent);
        } else if (!player.isPlaying()) {
            buttonPlay.setText("STOP");
            try {
                player.reset();
                player.setDataSource(this, Uri.parse(musique.getPreviewLink()));
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buttonPlay.setText("Play");
            player.stop();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(player.isPlaying()){
            player.stop();
        }
    }
}

