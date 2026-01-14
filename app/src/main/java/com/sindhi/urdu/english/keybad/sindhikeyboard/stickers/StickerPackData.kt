package com.sindhi.urdu.english.keybad.sindhikeyboard.stickers
import java.io.File

data class StickerPackData(
    val android_play_store_link: String,
    val sticker_packs: List<StickerPack>,
    val sticker_content: List<StickerContent>,
    val sticker_details: List<StickerPackDetail>
)

data class StickerPack(
    val identifier: String,
    val name: String,
    val premium: String,
    var downloadPercentage: String = "Progress 0 %",
    val isAdded: Boolean = false,
    val isDownloaded: Boolean = false,
    val image_data_version: String,
    val stickers_list_size: String,
    val publisher: String,
    val thumbnail_image_file: String,
    val tray_image_file: String,
    val publisher_website: String
)

data class StickerContent(
    val identifier: String,
    val name: String,
    val tray_image_file: String,
    val image_data_version: String,
    val publisher: String,
    val publisher_website: String,
    val avoid_cache: Boolean,
    val publisher_email: String?,
    val privacy_policy_website: String?,
    val license_agreement_website: String?,
    val stickers: List<Sticker>
)

data class Sticker(
    val image_file: String,
    val emojis: List<String>
)

data class StickerPackDetail(
    val identifier: String,
    val name: String,
    val stickers: List<StickerItem>
)

data class StickerItem(
    val image_file: String,
    val image_file_url: String
)

data class StickerPackInfo(
    val folderName: String,
    val stickerFiles: List<File>
)