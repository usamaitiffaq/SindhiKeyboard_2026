package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sindhi.urdu.english.keybad.R
import java.io.File

class StickersRowGridAdapter(
    private val ctx: Context,
    private val colorList: ArrayList<File>,
    private val onItemClickListener: (file: File) -> Unit
) : RecyclerView.Adapter<StickersRowGridAdapter.StickerRowGridViewHolder>() {

    inner class StickerRowGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSticker: ImageView = itemView.findViewById(R.id.ivTrayIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerRowGridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sticker_tray_grid_icon, parent, false)
        return StickerRowGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: StickerRowGridViewHolder, position: Int) {
        val pos = position
        Glide.with(ctx)
            .asBitmap()
            .load(colorList[position])
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(holder.ivSticker)

        holder.itemView.setOnClickListener {
            onItemClickListener(colorList[position])
        }
    }

    override fun getItemCount(): Int = colorList.size
}