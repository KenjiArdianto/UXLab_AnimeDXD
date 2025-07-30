package com.example.uxlab_animedxd.ui.home;

public class MangaItem {
    private final int imageRes;
    private final String title;
    private final String subtitle;

    public MangaItem(int imageRes, String title, String subtitle) {
        this.imageRes = imageRes;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
}