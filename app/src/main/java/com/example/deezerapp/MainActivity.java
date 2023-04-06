package com.example.deezerapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IOnSelectedMusique {

    private SearchFragment searchFragment;
    private MusiqueFragment musiqueFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isMobile()){
            searchFragment = new SearchFragment();
            musiqueFragment = new MusiqueFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, searchFragment)
                    .add(R.id.frameLayout, musiqueFragment)
                    .hide(musiqueFragment)
                    .commit();
        }else{
            searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.searchFragment);
            musiqueFragment = (MusiqueFragment) getSupportFragmentManager().findFragmentById(R.id.musiqueFragment);
            getSupportFragmentManager().beginTransaction()
                    .show(musiqueFragment)
                    .commit();
        }


        searchFragment.setListener(this);
    }

    @Override
    public void onSelectedMusique(Musique musique) {
        musiqueFragment.setMusique(musique);
        getSupportFragmentManager().beginTransaction()
                .hide(searchFragment)
                .show(musiqueFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (isMobile() && musiqueFragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .hide(musiqueFragment)
                    .show(searchFragment)
                    .commit();
        }else{
            super.onBackPressed();
        }
    }

    private boolean isMobile(){
        return findViewById(R.id.frameLayout)!=null;
    }
}
