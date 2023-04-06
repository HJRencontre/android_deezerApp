package com.example.deezerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener, IListenerApi, View.OnClickListener {

    private ArrayList<Musique> musiques = new ArrayList<Musique>();
    private ListView listViewSearch;
    private EditText editTextSearch;
    private Button buttonSearch;
    private IOnSelectedMusique listener;

    public void setListener(IOnSelectedMusique listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, null);

        listViewSearch = v.findViewById(R.id.listViewSearch);
        editTextSearch = v.findViewById(R.id.editTextSearch);
        buttonSearch = v.findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(this);
        listViewSearch.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onReceiveMusiques(ArrayList<Musique> musiques) {
        this.musiques = musiques;
        MusiqueBaseAdapter adapter = new MusiqueBaseAdapter(musiques,getContext());
        listViewSearch.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(!editTextSearch.getText().toString().equals("")){
            ServicesAPI.musiqueRequest(getContext(), editTextSearch.getText().toString(), this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        listener.onSelectedMusique(musiques.get(i));
    }
}