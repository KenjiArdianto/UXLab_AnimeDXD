package com.example.uxlab_animedxd.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.model.Anime;
import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private final ArrayList<Anime> animeList;
    private OnItemClickListener onItemClickListener;

    public AnimeAdapter(ArrayList<Anime> animeList) {
        this.animeList = animeList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_layout, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.animePoster.setImageResource(anime.getCover());
        holder.animeTitle.setText(anime.getTitle());
        holder.animeGenre.setText(anime.getGenre());
        holder.animeDescription.setText(anime.getSynopsis());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(anime);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView animePoster;
        TextView animeTitle, animeGenre, animeDescription;

        AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            animePoster = itemView.findViewById(R.id.anime_poster);
            animeTitle = itemView.findViewById(R.id.anime_title);
            animeGenre = itemView.findViewById(R.id.tv_anime_genre);
            animeDescription = itemView.findViewById(R.id.anime_description);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Anime anime);
    }
}