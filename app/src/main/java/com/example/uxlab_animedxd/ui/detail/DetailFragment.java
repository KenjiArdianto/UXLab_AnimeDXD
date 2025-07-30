package com.example.uxlab_animedxd.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.model.Anime;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anime_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi semua komponen UI
        TextView titleDetail = view.findViewById(R.id.anime_title_detail);
        ImageView imageDetail = view.findViewById(R.id.anime_image_detail);
        TextView genreDetail = view.findViewById(R.id.anime_genre_detail); // ID dari XML yang baru
        TextView synopsisDetail = view.findViewById(R.id.anime_synopsis_detail); // ID dari XML yang baru
        Button backButton = view.findViewById(R.id.back_btn);
        Button reviewButton = view.findViewById(R.id.review_btn); // ID untuk tombol review

        // Terima data Parcelable 'Anime' dari argumen
        Anime anime = null;
        if (getArguments() != null) {
            // Gunakan kunci "animeItem" yang sama seperti saat mengirim
            anime = getArguments().getParcelable("animeItem");
        }

        // Tampilkan data ke UI
        if (anime != null) {
            titleDetail.setText(anime.getTitle());
            imageDetail.setImageResource(anime.getCover()); // Gunakan getCover()
            genreDetail.setText(anime.getGenre());
            synopsisDetail.setText(anime.getSynopsis());
        }

        // Atur listener untuk tombol kembali
        backButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        // Atur listener untuk tombol review (jika diperlukan)
        reviewButton.setOnClickListener(v -> {
            // Tambahkan logika di sini, misalnya pindah ke fragment review
        });
    }
}