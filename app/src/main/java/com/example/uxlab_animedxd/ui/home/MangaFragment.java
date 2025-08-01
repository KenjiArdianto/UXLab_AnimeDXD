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

        // Data Best Manga (disamakan dengan bestNew di Kotlin)
        List<MangaItem> bestMangaList = new ArrayList<>();
        bestMangaList.add(new MangaItem(R.drawable.jujutsu, "Jujutsu Kaisen", "A boy swallows a cursed object and becomes a vessel of a powerful demon. He joins a sorcerer school to fight curses."));
        bestMangaList.add(new MangaItem(R.drawable.aot, "Attack on Titan", "Humanity battles giant man-eating titans in a desperate attempt to survive behind massive walls."));
        bestMangaList.add(new MangaItem(R.drawable.oshinoko, "Oshi no Ko", "A shocking reincarnation tale about the dark side of Japan’s entertainment industry and the quest for revenge."));
        bestMangaList.add(new MangaItem(R.drawable.hikaru, "The Summer Hikaru Died", "A small-town boy senses something is off when his best friend comes back… different."));
        rvBest.setAdapter(new BestMangaAdapter(bestMangaList));

        // --- Setup untuk Latest Manga ---
        RecyclerView rvLatest = view.findViewById(R.id.rvLatestManga);
        rvLatest.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Data Latest Manga (disamakan dengan latest di Kotlin)
        List<MangaItem> latestMangaList = new ArrayList<>();
        latestMangaList.add(new MangaItem(R.drawable.chainsaw, "Chainsaw Man", "Denji, a devil hunter with a chainsaw demon inside him, fights brutal devils in a world where fear manifests as monsters."));
        latestMangaList.add(new MangaItem(R.drawable.spy, "Spy x Family", "A spy must build a fake family to complete his mission—only to discover his “wife” is an assassin and “daughter” a telepath."));
        latestMangaList.add(new MangaItem(R.drawable.mha, "My Hero Academia", "In a world full of superpowers (quirks), a powerless boy enters the top hero school to become the Symbol of Peace."));
        latestMangaList.add(new MangaItem(R.drawable.bluelock, "Blue Lock", "After Japan’s loss in the World Cup, a radical football program aims to create the ultimate egotist striker."));
        latestMangaList.add(new MangaItem(R.drawable.tokyo_rev, "Tokyo Revengers", "A man travels back in time to high school to stop the death of his girlfriend and change the future."));
        latestMangaList.add(new MangaItem(R.drawable.deathnote, "Death Note", "A high school student gains a notebook that can kill anyone whose name is written in it—sparking a global manhunt."));
        rvLatest.setAdapter(new LatestMangaAdapter(latestMangaList));
    }

}