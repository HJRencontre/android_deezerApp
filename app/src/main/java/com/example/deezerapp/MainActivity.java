package com.example.deezerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, IListenerApi, View.OnClickListener {

    private ArrayList<Musique> musiques = new ArrayList<Musique>();
    private ListView listViewSearch;
    private EditText editTextSearch;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewSearch = findViewById(R.id.listViewSearch);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(this);
        listViewSearch.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MainActivity.this,MusiqueActivity.class);
        intent.putExtra("selectedMusique",musiques.get(i));
        startActivity(intent);
    }

    @Override
    public void onReceiveMusiques(ArrayList<Musique> musiques) {
        this.musiques = musiques;
        MusiqueBaseAdapter adapter = new MusiqueBaseAdapter(musiques,this);
        listViewSearch.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(!editTextSearch.getText().toString().equals("")){
            ServicesAPI.musiqueRequest(this, editTextSearch.getText().toString(), this);
        }
    }
}