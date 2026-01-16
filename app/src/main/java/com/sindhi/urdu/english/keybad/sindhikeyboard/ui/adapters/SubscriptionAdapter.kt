package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.manual.mediation.library.sotadlib.utils.PrefHelper
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.itemDs

class SubscriptionAdapter(private val clickListener: (itemDs) -> Unit) : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {
    private lateinit var context: Context
    private val subscriptionsList = ArrayList<itemDs>()
    private lateinit var prefHelper: PrefHelper

    class SubscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentLayout: ConstraintLayout = itemView.findViewById(R.id.clContent)
        val tvSaveTag: TextView = itemView.findViewById(R.id.tvSaveTag)
        val tvPlanPrice: TextView = itemView.findViewById(R.id.tvPlanPrice)
        val tvSubDetails: TextView = itemView.findViewById(R.id.tvSubDetails)
        val tvBillingCycle: TextView = itemView.findViewById(R.id.tvBillingCycle)
        val ivRadio: ImageView = itemView.findViewById(R.id.ivRadio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        context = parent.context
        prefHelper = PrefHelper(context)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlst, parent, false)
        return SubscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val currentItem = subscriptionsList[position]

        val selectedPos = prefHelper.getStringDefault("SubscriberPosition", "0")
        val isSelected = selectedPos == position.toString()

        holder.contentLayout.isSelected = isSelected

        if (isSelected) {
            // Set Selected Background and Show Radio
            holder.contentLayout.setBackgroundResource(R.drawable.ic_selected_subscription)
            holder.ivRadio.visibility = View.VISIBLE
        } else {
            // Set Unselected Background and Hide Radio
            holder.contentLayout.setBackgroundResource(R.drawable.selector_subscription_item_bg)
            holder.ivRadio.visibility = View.INVISIBLE
        }

        val priceText = currentItem.formattedPrice

        if (priceText.contains("Yearly", ignoreCase = true) || priceText.contains("Lifetime", ignoreCase = true)) {
            // Yearly Plan
            holder.tvSaveTag.visibility = View.VISIBLE
            holder.tvSaveTag.text = "Save 75%"
        } else if (priceText.contains("Month", ignoreCase = true)) {
            // Monthly Plan
            holder.tvSaveTag.visibility = View.VISIBLE
            holder.tvSaveTag.text = "Save 40%"
        } else {
            // Weekly or other plans
            holder.tvSaveTag.visibility = View.INVISIBLE
        }

        holder.tvPlanPrice.text = currentItem.formattedPrice

        val lines = currentItem.subsName.split("\n")
        if (lines.isNotEmpty()) {
            holder.tvSubDetails.text = lines[0]
        }
        if (lines.size > 1) {
            holder.tvBillingCycle.text = lines[1]
        }

        // ----------------------------------------------------------------
        // 4. CLICK LISTENER
        // ----------------------------------------------------------------
        holder.itemView.setOnClickListener {
            val oldPos = prefHelper.getStringDefault("SubscriberPosition", "0")?.toInt() ?: 0

            prefHelper.putString("SubscriberPosition", position.toString())

            if (oldPos != position) {
                notifyItemChanged(oldPos) // Refresh old item (to unselect)
            }
            notifyItemChanged(position)   // Refresh new item (to select)

            clickListener.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return subscriptionsList.size
    }

    fun setSubscriptionList(items: ArrayList<itemDs>) {
        subscriptionsList.clear()
        subscriptionsList.addAll(items)
        notifyDataSetChanged()
    }
}