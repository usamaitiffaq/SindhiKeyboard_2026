package com.sindhi.urdu.english.keybad.sindhikeyboard.interfaces

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.QuoteX

//import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.QuoteX

class GeneralAdapter(val generalstatusitemclicklistner: generalstatusitemclicklistner): RecyclerView.Adapter<GeneralAdapter.generalviewholder>() {
    lateinit var context: Context
    val modelList= ArrayList<QuoteX>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): generalviewholder {
        context=parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.status_layout, parent, false)
        val generalviewholder= generalviewholder(view)

        view.findViewById<ImageView>(R.id.copy).setOnClickListener {
            generalstatusitemclicklistner.onshayariitemcopyitemclicked(modelList[generalviewholder.adapterPosition])
        }
        view.findViewById<ImageView>(R.id.share).setOnClickListener {
            generalstatusitemclicklistner.onshayariitemshareitemclicked(modelList[generalviewholder.adapterPosition])
        }
        view.findViewById<ImageView>(R.id.messenger).setOnClickListener {
            generalstatusitemclicklistner.onshayariitemmessengeritemclicked(modelList[generalviewholder.adapterPosition])
        }
        view.findViewById<ImageView>(R.id.whatsapp).setOnClickListener {
            generalstatusitemclicklistner.onshayariitemwhatsappitemclicked(modelList[generalviewholder.adapterPosition])
        }
        view.findViewById<ImageView>(R.id.twitter).setOnClickListener {
            generalstatusitemclicklistner.onshayariitemwitteritemclicked(modelList[generalviewholder.adapterPosition])
        }
//        view.edit.setOnClickListener {
//            generalstatusitemclicklistner.onshayariitemedititemclicked(modelList[generalviewholder.adapterPosition])
//        }
        return generalviewholder
    }

    override fun onBindViewHolder(holder: generalviewholder, position: Int) {
        val currentitem=modelList[position]
        holder.showtext.text = currentitem.poetry
        holder.author.text = currentitem.name
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun getitemsforgeneralstatus(itemslist:List<QuoteX>)
    {
        modelList.clear()
        modelList.addAll(itemslist)
        notifyDataSetChanged()
    }

    class generalviewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val showtext = itemView.findViewById<TextView?>(R.id.qoutetext)
        val author = itemView.findViewById<TextView?>(R.id.author)
        val copy = itemView.findViewById<ImageView?>(R.id.copy)
        val share = itemView.findViewById<ImageView?>(R.id.share)
    }
}