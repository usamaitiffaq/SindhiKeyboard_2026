package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.sindhiPoetryModels.LabelNamesIcons

class LabelsAdapter(private val labelsList: List<LabelNamesIcons>,
                    private val onItemClickListener: (name: String) -> Unit) : RecyclerView.Adapter<LabelsAdapter.LabelsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_labels, parent, false)
        return LabelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabelsViewHolder, position: Int) {
        val myLabelsList = labelsList[position]
        holder.tvItem.text = myLabelsList.name
        holder.ivIcon.setImageDrawable(myLabelsList.id)

        holder.itemView.setOnClickListener {
            onItemClickListener(myLabelsList.name)
        }
    }

    override fun getItemCount(): Int {
        return labelsList.size
    }

    inner class LabelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: AppCompatTextView = itemView.findViewById(R.id.tvItem)
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
    }
}