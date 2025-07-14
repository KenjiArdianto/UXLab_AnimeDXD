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

class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.VH> {
    private final List<SlideItem> data;
    SliderAdapter(List<SlideItem> data) { this.data = data; }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img; TextView txt;
        VH(View v) {
            super(v);
            img = v.findViewById(R.id.imgSlide);
            txt = v.findViewById(R.id.tvCaption);
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup p, int t) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_slide, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        SlideItem it = data.get(pos);
        h.img.setImageResource(it.imageRes);
        h.txt.setText(it.caption);
    }

    @Override
    public int getItemCount() { return data.size(); }
}
