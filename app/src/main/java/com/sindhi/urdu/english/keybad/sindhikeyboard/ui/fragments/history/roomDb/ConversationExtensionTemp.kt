package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "convo_ext_tb_temp")
class ConversationExtensionTemp(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var conversationName: String = "",
    var source: String = "",
    var translation: String = "",
    var from: String = "",
    var fromLang: String = "",
    var toLang: String = "",
    var isFavorite: Boolean = false,
    var time:Long = 0)