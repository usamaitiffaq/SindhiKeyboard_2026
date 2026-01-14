package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.model

object StickerDataCache {
    var stickerPackData: StickerPackData? = null
    var progressMap = mutableMapOf<String, String>()
    var downloadInProgressMap = mutableMapOf<String, Boolean>()
}