package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorAdapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.model.ColorItem

class ColorAdapter(
    private val ctx: Context,
    private val colorList: MutableList<ColorItem>,
    private val onItemClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private var selectedPosition: Int = -1

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: View = itemView.findViewById(R.id.colorView)
        val selectedBorder: View = itemView.findViewById(R.id.selectedBorder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val pos = position
        holder.colorView.backgroundTintList = ColorStateList.valueOf(colorList[position].color)
        holder.selectedBorder.backgroundTintList = ColorStateList.valueOf(
            if (colorList[position].isSelected) {
                colorList[0].color // white is on 0 index
            } else {
                // colorList[colorList.size - 1].color
                ctx.getColor(R.color.transp)
            }
        )

        holder.itemView.setOnClickListener {
            colorList.forEachIndexed { index, colorItem ->
                if (colorItem.isSelected) {
                    colorItem.isSelected = false
                    colorList[index].isSelected = false
                    notifyItemChanged(index)
                }
            }

            colorList[position].isSelected = true
            selectedPosition = pos
            notifyItemChanged(pos)

            onItemClickListener(colorList[position].color)
        }
    }

    override fun getItemCount(): Int = colorList.size
}