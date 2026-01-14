package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorAdapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import java.io.File

class StickersEditorAdapter(
    private val items: MutableList<Any>,
    private val onStickerClick: (Drawable) -> Unit // Lambda to handle sticker clicks
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_STICKER = 1
    }

    fun updateData(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is String) TYPE_HEADER else TYPE_STICKER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_section_header, parent, false)
            )
            TYPE_STICKER -> StickerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_sticker, parent, false),
                onStickerClick
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(items[position] as String)
            is StickerViewHolder -> holder.bind(items[position] as File)
        }
    }

    override fun getItemCount(): Int = items.size

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader: TextView = view.findViewById(R.id.tvPackName)
        fun bind(header: String) {
            tvHeader.text = header
        }
    }

    class StickerViewHolder(view: View, private val onStickerClick: (Drawable) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val ivSticker: ImageView = view.findViewById(R.id.ivSticker)
        fun bind(stickerFile: File) {
            ivSticker.setImageURI(stickerFile.toUri())
            ivSticker.setOnClickListener {
                val drawable = ivSticker.drawable
                if (drawable != null) {
                    onStickerClick(drawable) // Notify the fragment
                }
            }
        }
    }
}

