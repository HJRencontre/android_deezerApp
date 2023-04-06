package com.example.deezerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class MusiqueFragment extends Fragment implements View.OnClickListener {

    private TextView textViewMusic, textViewArtiste, textViewAlbum;
    private ImageView imageViewCover;
    private Button buttonPlay, buttonLink;
    private MediaPlayer player = new MediaPlayer();

    private Musique musique;

    public void setMusique(Musique musique) {
        this.musique = musique;
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_musique, null);

        textViewMusic = v.findViewById(R.id.textViewMusic);
        textViewArtiste = v.findViewById(R.id.textViewArtiste);
        textViewAlbum = v.findViewById(R.id.textViewAlbum);

        imageViewCover = v.findViewById(R.id.imageViewCover);

        buttonPlay = v.findViewById(R.id.buttonEcouter);
        buttonLink = v.findViewById(R.id.buttonLinkDeezer);

        buttonPlay.setOnClickListener(this);
        buttonLink.setOnClickListener(this);

        return v;
    }

    public void refresh() {
        textViewMusic.setText(musique.getTitre());
        textViewArtiste.setText(musique.getArtiste());
        textViewAlbum.setText(musique.getAlbum());

        ServicesAPI.loadImage(getContext(),musique.getCover(), imageViewCover);

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
                player.setDataSource(getContext(), Uri.parse(musique.getPreviewLink()));
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

}

