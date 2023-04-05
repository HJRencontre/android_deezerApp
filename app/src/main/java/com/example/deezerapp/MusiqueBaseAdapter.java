package com.example.deezerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusiqueBaseAdapter extends BaseAdapter {

    private ArrayList<Musique> musiques = new ArrayList<Musique>();
    private Context context;

    public MusiqueBaseAdapter(ArrayList<Musique> musiques, Context context) {
        this.musiques = musiques;
        this.context = context;
    }

    @Override
    public int getCount() {
        return musiques.size();
    }

    @Override
    public Object getItem(int i) {
        return musiques.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_music, viewGroup, false);
        }

        TextView textViewTitre = view.findViewById((R.id.textViewTitre));
        textViewTitre.setText(musiques.get(i).getTitre());

        TextView textViewAlbum = view.findViewById((R.id.textViewAlbum));
        textViewAlbum.setText(musiques.get(i).getAlbum());

        TextView textViewArtiste = view.findViewById((R.id.textViewArtiste));
        textViewArtiste.setText(musiques.get(i).getArtiste());

        ImageView imageViewCover = view.findViewById(R.id.imageViewCover);
        ServicesAPI.loadImage(context, musiques.get(i).getCover(), imageViewCover);

        return view;
    }
}
