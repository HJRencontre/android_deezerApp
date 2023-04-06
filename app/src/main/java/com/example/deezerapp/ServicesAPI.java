package com.example.deezerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServicesAPI {

    private static  String URL_API = "https://api.deezer.com/search?q=";

    public static void musiqueRequest(Context context, String search, IListenerApi listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API + search,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Musique> musiques = new ArrayList<Musique>();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Musique temp = new Musique();
                                JSONObject object = jsonArray.getJSONObject(i);
                                temp.setTitre(object.getString("title"));
                                temp.setArtiste(object.getJSONObject("artist").getString("name"));
                                temp.setAlbum(object.getJSONObject("album").getString("title"));
                                temp.setCover(object.getJSONObject("album").getString("cover_medium"));
                                temp.setPreviewLink(object.getString("preview"));
                                temp.setLink(object.getString("link"));
                                musiques.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.onReceiveMusiques(musiques);
                    }
                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Erreur");
                }
        });
        queue.add(request);
    }

    public static void loadImage(Context context, String previewLink, final ImageView imageViewCover){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest(previewLink,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageViewCover.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageViewCover.setImageResource(android.R.drawable.ic_menu_gallery);
                    }
                });
        queue.add(request);
    }
}
