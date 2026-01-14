package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.databinding.ItemStickerBinding


class StickersDetailsAdapter(
    private val stickers: List<String>,
    private val onStickerClick: (String) -> Unit
) : RecyclerView.Adapter<StickersDetailsAdapter.StickerViewHolder>() {

    inner class StickerViewHolder(val binding: ItemStickerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stickerUrl: String) {
            Glide.with(binding.root.context)
                .load(stickerUrl)
                .placeholder(R.drawable.ic_no_stickers)
                .into(binding.ivSticker)

            binding.root.setOnClickListener { onStickerClick(stickerUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerViewHolder {
        val binding = ItemStickerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StickerViewHolder, position: Int) {
        holder.bind(stickers[position])
    }

    override fun getItemCount(): Int = stickers.size
}