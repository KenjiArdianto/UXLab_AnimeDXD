package com.example.uxlab_animedxd.data;

import android.content.Context;
import android.content.res.TypedArray;
import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.model.Anime;
import java.util.ArrayList;

public class DummyAnimeDataSource {
    public static ArrayList<Anime> getAnimeList(Context context) {
        String[] animeTitles = context.getResources().getStringArray(R.array.anime_titles);
        String[] animeGenres = context.getResources().getStringArray(R.array.anime_genres);
        String[] animeDescriptions = context.getResources().getStringArray(R.array.anime_descriptions);

        ArrayList<Anime> listAnime = new ArrayList<>();

        try (TypedArray animePosters = context.getResources().obtainTypedArray(R.array.anime_posters)) {
            for (int i = 0; i < animeTitles.length; i++) {
                Anime anime = new Anime(
                        animePosters.getResourceId(i, -1),
                        animeTitles[i],
                        animeGenres[i],
                        animeDescriptions[i]
                );
                listAnime.add(anime);
            }
        }
        return listAnime;
    }
}