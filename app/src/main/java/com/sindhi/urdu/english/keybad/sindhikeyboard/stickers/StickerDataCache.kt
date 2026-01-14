package com.sindhi.urdu.english.keybad.sindhikeyboard.stickers

object StickerDataCache {
    var stickerPackData: StickerPackData? = null
    var progressMap = mutableMapOf<String, String>()
    var downloadInProgressMap = mutableMapOf<String, Boolean>()
}