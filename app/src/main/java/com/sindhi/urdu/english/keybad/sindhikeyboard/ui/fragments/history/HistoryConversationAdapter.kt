package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.blockingClickListener
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history.roomDb.ConversationExtension

class HistoryConversationAdapter : RecyclerView.Adapter<HistoryConversationAdapter.HistoryConversationViewHolder>() {

    lateinit var mcontext: Context
    var historyConversationList = ArrayList<ConversationExtension>()
    var historyConversationClickListener: HistoryConversationClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryConversationViewHolder {
        mcontext = parent.context
        return HistoryConversationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_conversation_item, parent, false))
    }

    override fun getItemCount(): Int {
        return historyConversationList.size
    }

    override fun onBindViewHolder(holder: HistoryConversationViewHolder, position: Int) {
        val currentItem = historyConversationList[position]
        holder.txtConversationName.text = currentItem.conversationName
        holder.txtFromLanguage.text = currentItem.fromLang
        holder.txtToLanguage.text = currentItem.toLang
        holder.txtFrom.text = currentItem.source
        holder.txtTo.text = currentItem.translation
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryList(conversationExtensions: List<ConversationExtension>) {
        historyConversationList.clear()
        historyConversationList.addAll(conversationExtensions)
        notifyDataSetChanged()
    }

    inner class HistoryConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtConversationName: TextView = itemView.findViewById(R.id.convoName)
        val txtFromLanguage: AppCompatTextView = itemView.findViewById(R.id.txtFromLang)
        val txtToLanguage: AppCompatTextView = itemView.findViewById(R.id.txtToLang)
        val txtFrom: TextView = itemView.findViewById(R.id.txtFrom)
        val txtTo: TextView = itemView.findViewById(R.id.txtTo)

        val btnFavorite: ImageView = itemView.findViewById(R.id.btnFavorite)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

        init {
            itemView.blockingClickListener {
                historyConversationClickListener?.onRootViewClick(adapterPosition)
            }

            btnFavorite.blockingClickListener {
                historyConversationClickListener?.onFavoriteClick(adapterPosition)
            }

            btnDelete.blockingClickListener {
                historyConversationClickListener?.onDeleteClick(adapterPosition)
            }
        }
    }

    interface HistoryConversationClickListener {
        fun onRootViewClick(position: Int)
        fun onFavoriteClick(position: Int)
        fun onDeleteClick(position: Int)
    }
}