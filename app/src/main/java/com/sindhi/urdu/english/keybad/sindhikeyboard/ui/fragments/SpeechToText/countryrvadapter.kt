package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.SpeechToText

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sindhi.newvoicetyping.ui.Speechtotext.CountryCountry

import com.sindhi.urdu.english.keybad.R

class countryrvadapter(val clicklistner:(CountryCountry) -> Unit):RecyclerView.Adapter<countryrvadapter.countiesrvviewholder>() {

    lateinit var context: Context
    val countrieslist=ArrayList<CountryCountry>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): countiesrvviewholder {
        context=parent.context
        val view=LayoutInflater.from(parent.context).inflate(R.layout.countriesrvitem,parent,false)

        val countiesrvviewholder = countiesrvviewholder(view)

        return countiesrvviewholder
    }

    override fun onBindViewHolder(holder: countiesrvviewholder, position: Int) {
        val currentcountry=countrieslist[position]
        holder.countryname.text=currentcountry.countryName
        Glide.with(context).load(currentcountry.arImage).into(holder.countryflag)
        holder.itemView.setOnClickListener {
                clicklistner.invoke(currentcountry)
        }
    }

    override fun getItemCount(): Int {
        return countrieslist.size
    }

    fun getcountryinfo(items:ArrayList<CountryCountry>) {
        countrieslist.clear()
        countrieslist.addAll(items)
        countrieslist.sortBy { it.countryName }
        notifyDataSetChanged()
    }

    class countiesrvviewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val countryflag:ImageView=itemView.findViewById(R.id.ivcountryflag)
        val countryname:TextView=itemView.findViewById(R.id.tvcountryname)

    }
}