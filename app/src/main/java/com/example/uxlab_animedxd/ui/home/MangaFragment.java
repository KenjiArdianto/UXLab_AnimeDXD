package com.example.uxlab_animedxd.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uxlab_animedxd.R;
import java.util.ArrayList;
import java.util.List;

public class MangaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manga, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- Setup untuk Best Manga ---
        RecyclerView rvBest = view.findViewById(R.id.rvBestManga);
        rvBest.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        // Buat data dummy untuk Best Manga
        List<MangaItem> bestMangaList = new ArrayList<>();
        bestMangaList.add(new MangaItem(R.drawable.poster1, "Kagurabachi", "Action, Fantasy"));
        bestMangaList.add(new MangaItem(R.drawable.poster2, "Oshi no Ko", "Drama, Supernatural"));
        bestMangaList.add(new MangaItem(R.drawable.poster3, "Dandadan", "Comedy, Sci-Fi"));
        bestMangaList.add(new MangaItem(R.drawable.poster1, "More Manga 1", "Genre"));
        bestMangaList.add(new MangaItem(R.drawable.poster2, "More Manga 2", "Genre"));

        rvBest.setAdapter(new BestMangaAdapter(bestMangaList));

        // --- Setup untuk Latest Manga ---
        RecyclerView rvLatest = view.findViewById(R.id.rvLatestManga);
        rvLatest.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Buat data dummy untuk Latest Manga
        List<MangaItem> latestMangaList = new ArrayList<>();
        latestMangaList.add(new MangaItem(R.drawable.poster3, "The Fragrant Flower Blooms With Dignity", "A story of romance between two students from different schools."));
        latestMangaList.add(new MangaItem(R.drawable.poster1, "Rent-A-Girlfriend Season 4", "The continuing saga of Kazuya and his rental girlfriends."));
        latestMangaList.add(new MangaItem(R.drawable.poster2, "Another Latest Manga", "Description for another manga."));
        latestMangaList.add(new MangaItem(R.drawable.poster3, "Yet Another One", "Description for yet another manga."));

        rvLatest.setAdapter(new LatestMangaAdapter(latestMangaList));
    }
}