package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.model.BackgroundImageItem

class BackgroundImagesAdapter(
    private val backgroundList: MutableList<BackgroundImageItem>,
    private val onItemClickListener: (position: Int) -> Unit) : RecyclerView.Adapter<BackgroundImagesAdapter.BackgroundViewHolder>() {

    private var selectedPosition: Int = backgroundList.indexOfFirst { it.isSelected }

    inner class BackgroundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBorders: ImageView = itemView.findViewById(R.id.ivBorders)
        val ivBackground: ImageView = itemView.findViewById(R.id.ivBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images_backgrounds, parent, false)
        return BackgroundViewHolder(view)
    }

    override fun onBindViewHolder(holder: BackgroundViewHolder, position: Int) {
        val item = backgroundList[position]
        holder.ivBackground.setImageResource(item.backgroundResId)
        holder.ivBorders.setBackgroundResource(
            if (item.isSelected) R.color.navy_blue else R.color.transp
        )

        holder.itemView.setOnClickListener {
            if (selectedPosition != -1) {
                backgroundList[selectedPosition].isSelected = false
                notifyItemChanged(selectedPosition)
            }
            item.isSelected = true
            selectedPosition = position
            notifyItemChanged(position)
            onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = backgroundList.size
}

/* class BackgroundImagesAdapter(
    private val backgroundList: MutableList<BackgroundImageItem>,
    private val onItemClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<BackgroundImagesAdapter.BackgroundViewHolder>() {
    private var selectedPosition: Int = -1

    inner class BackgroundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBorders: ImageView = itemView.findViewById(R.id.ivBorders)
        val ivBackground: ImageView = itemView.findViewById(R.id.ivBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images_backgrounds, parent, false)
        return BackgroundViewHolder(view)
    }

    override fun onBindViewHolder(holder: BackgroundViewHolder, position: Int) {
        val item = backgroundList[position]
        holder.ivBackground.setImageResource(item.backgroundResId)
        holder.ivBorders.setBackgroundResource(
            if (item.isSelected) {
                R.color.navy_blue
            } else {
                R.color.transp
            }
        )

        holder.itemView.setOnClickListener {
            if (selectedPosition != -1) {
                backgroundList[selectedPosition].isSelected = false
                notifyItemChanged(selectedPosition)
            }
            item.isSelected = true
            selectedPosition = position
            notifyItemChanged(position)
            onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = backgroundList.size
} */