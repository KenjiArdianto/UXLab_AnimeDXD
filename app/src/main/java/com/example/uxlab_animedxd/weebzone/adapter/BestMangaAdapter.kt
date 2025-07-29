package com.example.uxlab_animedxd.weebzone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxlab_animedxd.R
import com.example.uxlab_animedxd.weebzone.model.Manga

class BestMangaAdapter(private val data: List<Manga>) :
    RecyclerView.Adapter<BestMangaAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        private val tvTitle:  TextView   = itemView.findViewById(R.id.tvTitle)
        private val tvSub:    TextView   = itemView.findViewById(R.id.tvSubtitle)

        fun bind(item: Manga) {
            imgPoster.setImageResource(item.imageRes)
            tvTitle.text = item.title.uppercase()
            tvSub.text   = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_best_manga, parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(data[position])
}
