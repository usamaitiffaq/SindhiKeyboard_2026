package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val sindhi_layout = arrayOf(

    arrayOf(
        Key(labelMain = "ق", weight = 1f),
        Key(labelMain = "ص", weight = 1f),
        Key(labelMain = "ي", weight = 1f),
        Key(labelMain = "ر", weight = 1f),
        Key(labelMain = "ت", weight = 1f),
        Key(labelMain = "ٿ", weight = 1f),
        Key(labelMain = "ع", weight = 1f),
        Key(labelMain = "ڳ", weight = 1f),
        Key(labelMain = "و", weight = 1f),
        Key(labelMain = "پ", weight = 1f)
    ),
    arrayOf(
        Key(labelMain = "ڇ", weight = 1f),
        Key(labelMain = "ڍ", weight = 1f),
        Key(labelMain = "ا", weight = 1f),
        Key(labelMain = "س", weight = 1f),
        Key(labelMain = "د", weight = 1f),
        Key(labelMain = "ف", weight = 1f),
        Key(labelMain = "گ", weight = 1f),
        Key(labelMain = "ه", weight = 1f),
        Key(labelMain = "ج", weight = 1f),
        Key(labelMain = "ڪ", weight = 1f)
    ),
    arrayOf(
        Key(labelMain = "ل", weight = 1f),
        Key(labelMain = "ک", weight = 1f),
        Key(labelMain = "ڱ", weight = 1f),
        Key(labelMain = "ز", weight = 1f),
        Key(labelMain = "خ", weight = 1f),
        Key(labelMain = "ط", weight = 1f),
        Key(labelMain = "ڀ", weight = 1f),
        Key(labelMain = "ب", weight = 1f),
        Key(labelMain = "ن", weight = 1f)
    ),
    arrayOf(
        Key("۱/۲ ", 1.5f, isCharacter = false, isSpecialCharacter = true, shouldBeResized = true, shouldShowPopUp = false, shouldBeRounded = false),
        Key(labelMain = "م", weight = 1f),
        Key(labelMain = "ئ", weight = 1f),
        Key(labelMain = "ڏ", weight = 1f),
        Key(labelMain = "ڌ", weight = 1f),
        Key(labelMain = "ى", weight = 1f),
        Key(labelMain = "۽", weight = 1f),
        Key(labelMain = "ؤ", weight = 1f),
        Key("Delete", 1f, isSpecialCharacter = true, icon = R.drawable.backspace, shouldShowPopUp = false, isCharacter = false, shouldShowIcon = true, contentDescription = "Delete")
    ),
    arrayOf(
        Key(
            "?123",
            1.5f,
            isCharacter = false,
            isSpecialCharacter = true,
            shouldBeResized = true,
            shouldShowPopUp = false,
            shouldBeRounded = true
        ),
        Key(
            labelMain = "Languages",
            weight = 2f,
            icon = R.drawable.ic_eng_txt,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            contentDescription = "Languages",
        ),
        Key(
            "Space",
            5f,
            code = 32,
            icon = R.drawable.space,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            isLanguageKey = true,
            contentDescription = "Space"
        ),
        /*Key(
            "سنڌي",
            4f,
            valueMain = " ",
            shouldShowPopUp = false,
            isCharacter = false,
            shouldBeResized = true,
            shouldShowLanguageName = true,
            isLanguageKey = true,
            code = 32
        ),*/
        Key("۔", 1f),
        Key(
            labelMain = "Done",
            weight = 1.5f,
            isSpecialCharacter = true,
            icon = R.drawable.done_icon,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            contentDescription = "Done",
            shouldBeRounded = true
        )
    )
)