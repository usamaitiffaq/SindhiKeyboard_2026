package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.media.AudioManager
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses.SuggestionItems

object DictionaryObject {
    var suggestionList = mutableListOf<SuggestionItems>()
    var audioManager: AudioManager? = null
}