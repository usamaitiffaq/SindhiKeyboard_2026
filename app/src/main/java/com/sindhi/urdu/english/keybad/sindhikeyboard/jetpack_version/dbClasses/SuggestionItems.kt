package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses

data class SuggestionItems(
    val id: Int,
    val engRomanWordsSuggestion: String?,
    val urduWordsSuggestion: String?,
    val normalSuggestion: String?,
    val dailyUseWords: String?
)