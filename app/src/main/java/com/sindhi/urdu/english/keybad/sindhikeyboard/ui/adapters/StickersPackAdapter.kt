package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manual.mediation.library.sotadlib.utils.NetworkCheck
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.databinding.ItemStickersViewBinding
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.CustomFirebaseEvents
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.blockingClickListener
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerDataCache.downloadInProgressMap
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerDataCache.progressMap
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerPack
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerPackData
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.activities.StickersViewActivity


class StickersPackAdapter(
    private var context: Context,
    private var stickerPackData: StickerPackData? = null,
    var onStickerDetailsClick: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<StickersPackAdapter.StickerViewHolder>() {

    inner class StickerViewHolder(private val binding: ItemStickersViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stickerPack: StickerPack, position: Int) {
            binding.txtPackName.text = stickerPack.name
            binding.txtSize.text = "Size: ${stickerPack.stickers_list_size}"

            Glide.with(binding.ivSticker.context)
                .load(stickerPack.thumbnail_image_file)
                .placeholder(R.drawable.ic_no_stickers)
                .into(binding.ivSticker)

            val progress = progressMap[stickerPack.name] ?: context.getString(R.string.label_starting_download)
            binding.txtProgress.text = progress

            val isDownloadInProgress = downloadInProgressMap[stickerPack.name] ?: false
            if (!isDownloadInProgress) {
                binding.txtAddSticker.visibility = View.VISIBLE
                binding.txtProgress.visibility = View.GONE
            } else {
                binding.txtAddSticker.visibility = View.GONE
                if (progress != "Download Complete") {
                    binding.txtProgress.visibility = View.VISIBLE
                }
            }

            binding.txtAddSticker.setOnClickListener {
                CustomFirebaseEvents.activitiesFragmentEvent(context as StickersViewActivity, "add_sticker_scr")
                if (NetworkCheck.isNetworkAvailable(context as StickersViewActivity)) {
                    downloadInProgressMap[stickerPack.name] = true
                    notifyItemChanged(position)
                    binding.txtProgress.text = context.getString(R.string.label_starting_download)
                    binding.txtProgress.visibility = View.VISIBLE
                    (context as StickersViewActivity).stickerViewModel.downloadStickerPack(stickerPack, context)
                } else {
                    Toast.makeText(context as StickersViewActivity,"No Internet!", Toast.LENGTH_LONG).show()
                }
            }

            binding.ivSticker.blockingClickListener {
                onStickerDetailsClick?.invoke(position)
            }

            binding.txtPackName.blockingClickListener {
                onStickerDetailsClick?.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerViewHolder {
        val binding = ItemStickersViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StickerViewHolder, position: Int) {
        val stickerPack = stickerPackData?.sticker_packs?.get(position)
        stickerPack?.let {
            holder.bind(it, position)
        }
    }

    override fun getItemCount(): Int = stickerPackData?.sticker_packs?.size ?: 0

    fun updateData(newStickerPackData: StickerPackData?) {
        stickerPackData = newStickerPackData
        notifyDataSetChanged()
    }

    fun updateProgress(packName: String, progress: String) {
        progressMap[packName] = progress
        val position = stickerPackData?.sticker_packs?.indexOfFirst { it.name == packName } ?: -1
        if (position != -1) {
            notifyItemChanged(position)
        }
    }

    fun markDownloadComplete(packName: String) {
        progressMap[packName] = "Download Complete"
        val position = stickerPackData?.sticker_packs?.indexOfFirst { it.name == packName } ?: -1
        if (position != -1) {
            notifyItemChanged(position)
        }
    }
}