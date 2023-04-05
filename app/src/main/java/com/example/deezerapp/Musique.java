package com.example.deezerapp;

import java.io.Serializable;

public class Musique implements Serializable {
    private String titre, album, artiste, cover, previewLink, link;

    public Musique(String titre, String album, String artiste, String cover, String previewLink, String link) {
        this.titre = titre;
        this.album = album;
        this.artiste = artiste;
        this.cover = cover;
        this.previewLink = previewLink;
        this.link = link;
    }

    public Musique() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
