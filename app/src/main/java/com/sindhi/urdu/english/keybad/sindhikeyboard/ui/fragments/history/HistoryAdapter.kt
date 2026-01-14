package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history.roomDb.WordEntity

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    lateinit var mContext: Context
    private val historyList = ArrayList<WordEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        mContext = parent.context
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.historyrvitem, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.tvWords.text = "Word:${currentItem.word}"
        holder.tvDefinition.text = "Translation:${currentItem.def}"
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryList(wordsList: List<WordEntity>) {
        historyList.clear()
        historyList.addAll(wordsList)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWords: TextView = itemView.findViewById(R.id.tvhistory)
        val tvDefinition: TextView = itemView.findViewById(R.id.tvdefinition)
    }
}