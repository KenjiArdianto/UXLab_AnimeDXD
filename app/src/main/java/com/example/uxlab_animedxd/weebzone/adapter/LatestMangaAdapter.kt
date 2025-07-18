package com.example.uxlab_animedxd.weebzone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxlab_animedxd.R
import com.example.uxlab_animedxd.weebzone.model.Manga

class LatestMangaAdapter(private val data: List<Manga>) :
    RecyclerView.Adapter<LatestMangaAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgThumb: ImageView = itemView.findViewById(R.id.imgThumb)
        private val tvTitle : TextView  = itemView.findViewById(R.id.tvTitle)
        private val tvDesc  : TextView  = itemView.findViewById(R.id.tvDesc)

        fun bind(item: Manga) {
            imgThumb.setImageResource(item.imageRes)
            tvTitle.text = item.title
            tvDesc.text  = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_latest_manga, parent, false)
        )

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(data[position])
}
