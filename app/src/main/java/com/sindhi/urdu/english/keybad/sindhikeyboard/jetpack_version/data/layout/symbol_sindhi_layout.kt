package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val symbolsSindhiLayout = arrayOf(
    arrayOf(
        Key("۱", 1f, isSymbolLayout = true),
        Key("۲", 1f, isSymbolLayout = true),
        Key("۳", 1f, isSymbolLayout = true),
        Key("۴", 1f, isSymbolLayout = true),
        Key("۵", 1f, isSymbolLayout = true),
        Key("٦", 1f, isSymbolLayout = true),
        Key("۷", 1f, isSymbolLayout = true),
        Key("۸", 1f, isSymbolLayout = true),
        Key("۹", 1f, isSymbolLayout = true),
        Key("۰", 1f, isSymbolLayout = true)
    ),

    arrayOf(
        Key("ض", 0.9f, isSymbolLayout = true),
        Key("ڙ", 0.9f, isSymbolLayout = true),
        Key("ٽ", 0.9f, isSymbolLayout = true),
        Key("ث", 0.9f, isSymbolLayout = true),
        Key("غ", 0.9f, isSymbolLayout = true),
        Key("ھ", 0.9f, isSymbolLayout = true),
        Key("ڦ", 0.9f, isSymbolLayout = true),
        Key("ڃ", 0.9f, isSymbolLayout = true),
        Key("ڄ", 0.9f, isSymbolLayout = true),
        Key("ٺ", 0.9f, isSymbolLayout = true),
    ),

    arrayOf(
        Key("آ", 0.9f, isSymbolLayout = true),
        Key("ش", 0.9f, isSymbolLayout = true),
        Key("ڊ", 0.9f, isSymbolLayout = true),
        Key("ڦ", 0.9f, isSymbolLayout = true),
        Key("ح", 0.9f, isSymbolLayout = true),
        Key("ذ", 0.9f, isSymbolLayout = true),
        Key("ظ", 0.9f, isSymbolLayout = true),
        Key("ء", 0.9f, isSymbolLayout = true),
        Key("ڻ", 0.9f, isSymbolLayout = true)
    ),

    arrayOf(
        Key("۲/۲ ", 1.5f, isCharacter = false, isSpecialCharacter = true, shouldBeResized = true, shouldShowPopUp = false, isSymbolLayout = true),
        Key("۾",1f, isSymbolLayout = true),
        Key("“",1f, isSymbolLayout = true),
        Key("”",1f, isSymbolLayout = true),
        Key("جھ",1f, isSymbolLayout = true),
        Key("گھ",1f, isSymbolLayout = true),
        Key("چ",1f, isSymbolLayout = true),
        Key("ٻ",1f, isSymbolLayout = true),
        Key("Delete", 1.5f, isSpecialCharacter = true, icon = R.drawable.backspace, shouldShowPopUp = false, isCharacter = false, shouldShowIcon = true, contentDescription = "Delete", isSymbolLayout = true)
    ),

    arrayOf(
        Key("?123", 1.5f, isCharacter = false, isSpecialCharacter = true, shouldBeResized = true, shouldShowPopUp = false, shouldBeRounded = true, isSymbolLayout = true),
        Key(",", 1f, isSymbolLayout = true),
        Key("سنڌي", 4f, valueMain = " ", shouldShowPopUp = false, isCharacter = false, shouldBeResized = true, shouldShowLanguageName = true, isLanguageKey = true, code = 32, isSymbolLayout = true),
        Key("۔", 1f, isSymbolLayout = true),
        Key(labelMain = "Done", weight = 1.5f, isSpecialCharacter = true, icon = R.drawable.done_icon, shouldShowPopUp = false, shouldShowIcon = true, isCharacter = false, contentDescription = "Done", shouldBeRounded = true, isSymbolLayout = true)
    )
)