package com.example.uxlab_animedxd.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uxlab_animedxd.R;
import java.util.List;

public class LatestMangaAdapter extends RecyclerView.Adapter<LatestMangaAdapter.ViewHolder> {

    private final List<MangaItem> mangaList;

    public LatestMangaAdapter(List<MangaItem> mangaList) {
        this.mangaList = mangaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_manga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MangaItem item = mangaList.get(position);
        holder.thumbnail.setImageResource(item.getImageRes());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imgThumb);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDesc);
        }
    }
}