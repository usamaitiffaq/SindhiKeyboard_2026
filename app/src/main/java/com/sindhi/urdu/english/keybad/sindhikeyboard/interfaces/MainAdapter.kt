package com.sindhi.urdu.english.keybad.sindhikeyboard.interfaces

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.models.Cat_Update

class MainAdapter(private val shayaricategoryclicklistner: shayaricategoryclicklistner) :
    RecyclerView.Adapter<MainAdapter.mainadapterviewholder>() {

    lateinit var context: Context
    val modelList = ArrayList<Cat_Update>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainadapterviewholder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.shayri_layout, parent, false)
        val mainadapterviewholder = mainadapterviewholder(view)
        view.findViewById<ImageView>(R.id.shayributton).setOnClickListener {
            shayaricategoryclicklistner.onshayaricategoryitemclicked(modelList[mainadapterviewholder.adapterPosition])
        }
        return mainadapterviewholder
    }

    override fun onBindViewHolder(holder: mainadapterviewholder, position: Int) {
        val model = modelList[position]
        Glide.with(context)
            .load(model.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun getitems(shayariitems: ArrayList<Cat_Update>) {
        modelList.clear()
        modelList.addAll(shayariitems)
        notifyDataSetChanged()
    }

    class mainadapterviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView?>(R.id.shayributton)
    }
}