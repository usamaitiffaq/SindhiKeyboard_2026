package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_tb")
class WordEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var word: String? = null,
    var def: String? = null
)