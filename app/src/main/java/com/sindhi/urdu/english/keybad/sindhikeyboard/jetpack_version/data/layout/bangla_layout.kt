package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val bangla_layout = arrayOf(
    arrayOf(
        Key("ض", 1f, labelSecondary = "1", codeSecondary = 49),
        Key("ص", 1f, labelSecondary = "2", codeSecondary = 50),
        Key("غ", 1f, labelSecondary = "3", codeSecondary = 51),
        Key("ڑ", 1f, labelSecondary = "4", codeSecondary = 52),
        Key("ٹ", 1f, labelSecondary = "5", codeSecondary = 53),
        Key("ث", 1f, labelSecondary = "6", codeSecondary = 54),
        Key("ح", 1f, labelSecondary = "7", codeSecondary = 55),
        Key("ئ", 1f, labelSecondary = "8", codeSecondary = 56),
        Key("ظ", 1f, labelSecondary = "9", codeSecondary = 57),
        Key("ط", 1f, labelSecondary = "0", codeSecondary = 48)
    ),
    arrayOf(
        Key("ق", 1f),
        Key("و", 1f),
        Key("ع", 1f),
        Key("ر", 1f),
        Key("ت", 1f),
        Key("ے", 1f),
        Key("ء", 1f),
        Key("ی", 1f),
        Key("ہ", 1f),
        Key("پ", 1f)
    ),
    arrayOf(
        Key("ا", 1f, labelSecondary = "#", codeSecondary = 35),
        Key("س", 1f, labelSecondary = "#", codeSecondary = 35),
        Key("ڈ", 1f, labelSecondary = "#", codeSecondary = 35),
        Key("د", 1f, labelSecondary = "$", codeSecondary = 36),
        Key("ف", 1f, labelSecondary = "_", codeSecondary = 95),
        Key("گ", 1f, labelSecondary = "&", codeSecondary = 38),
        Key("ھ", 1f, labelSecondary = "-", codeSecondary = 45),
        Key("ج", 1f, labelSecondary = "+", codeSecondary = 43),
        Key("ک", 1f, labelSecondary = "(", codeSecondary = 40),
        Key("ل", 1f, labelSecondary = ")", codeSecondary = 41)
    ),
    arrayOf(
        Key("ذ", 1f, labelSecondary = "*", codeSecondary = 42),
        Key("ز", 1f, labelSecondary = "*", codeSecondary = 42),
        Key("ش", 1f, labelSecondary = "*", codeSecondary = 42),
        Key("خ", 1f, labelSecondary = "\"", codeSecondary = 34),
        Key("چ", 1f, labelSecondary = "'", codeSecondary = 39),
        Key("ب", 1f, labelSecondary = ":", codeSecondary = 58),
        Key("ں", 1f, labelSecondary = ";", codeSecondary = 59),
        Key("ن", 1f, labelSecondary = "!", codeSecondary = 33),
        Key("م", 1f, labelSecondary = "?", codeSecondary = 63),
        Key(
            "Delete",
            1f,
            isSpecialCharacter = true,
            icon = R.drawable.backspace,
            shouldShowPopUp = false,
            isCharacter = false,
            shouldShowIcon = true,
            contentDescription = "Delete"
        )
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
        Key(",", 1f, code = 44),
        Key(
            labelMain = "Languages",
            weight = 1f,
            icon = R.drawable.languages,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            contentDescription = "Languages",
        ),
        Key(
            "Bangla",
            4f,
            valueMain = " ",
            shouldShowPopUp = false,
            isCharacter = false,
            shouldBeResized = true,
            isLanguageKey = true,
            code = 32
        ),
        Key(".", 1f, code = 46),
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
