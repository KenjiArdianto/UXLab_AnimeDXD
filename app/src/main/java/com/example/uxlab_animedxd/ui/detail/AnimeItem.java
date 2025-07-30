package com.example.uxlab_animedxd.ui.detail;

import java.io.Serializable;

public class AnimeItem implements Serializable {
    private final int imageRes;
    private final String title;
    private final String genre;
    private final String synopsis;

    public AnimeItem(int imageRes, String title, String genre, String synopsis) {
        this.imageRes = imageRes;
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    // Getter methods
    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getSynopsis() { return synopsis; }
}