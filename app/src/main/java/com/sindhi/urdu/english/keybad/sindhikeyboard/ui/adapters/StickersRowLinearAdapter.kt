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

class StickersRowLinearAdapter(
    private val ctx: Context,
    private val colorList: ArrayList<File>,
    private val onItemClickListener: (position: Int) -> Unit) : RecyclerView.Adapter<StickersRowLinearAdapter.StickerRowLinearViewHolder>() {

    inner class StickerRowLinearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivTrayIcon: ImageView = itemView.findViewById(R.id.ivTrayIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerRowLinearViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sticker_tray_linear_icon, parent, false)
        return StickerRowLinearViewHolder(view)
    }

    override fun onBindViewHolder(holder: StickerRowLinearViewHolder, position: Int) {
        val pos = position
        Glide.with(ctx)
            .asBitmap()
            .load(colorList[position])
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(holder.ivTrayIcon)

        holder.itemView.setOnClickListener {
            onItemClickListener(pos)
        }
    }

    override fun getItemCount(): Int = colorList.size
}