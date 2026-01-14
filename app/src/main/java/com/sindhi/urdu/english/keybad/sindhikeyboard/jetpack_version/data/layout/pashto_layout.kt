package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val pashto_layout = arrayOf(
    arrayOf(
        Key(labelMain = "ج", weight = 1f, labelSecondary = "1", codeSecondary = 49),
        Key(labelMain = "چ", weight = 1f, labelSecondary = "2", codeSecondary = 50),
        Key(labelMain = "ح", weight = 1f, labelSecondary = "3", codeSecondary = 51),
        Key(labelMain = "خ", weight = 1f, labelSecondary = "4", codeSecondary = 52),
        Key(labelMain = "څ", weight = 1f, labelSecondary = "5", codeSecondary = 53),
        Key(labelMain = "ځ", weight = 1f, labelSecondary = "6", codeSecondary = 54),
        Key(labelMain = "ف", weight = 1f, labelSecondary = "7", codeSecondary = 55),
        Key(labelMain = "ق", weight = 1f, labelSecondary = "8", codeSecondary = 56),
        Key(labelMain = "ث", weight = 1f, labelSecondary = "9", codeSecondary = 57),
        Key(labelMain = "ص", weight = 1f, labelSecondary = "0", codeSecondary = 48)
    ),
    arrayOf(
        Key(labelMain = "ګ", weight = 1f, labelSecondary = "/"),
        Key(labelMain = "ک", weight = 1f, labelSecondary = ")"),
        Key(labelMain = "م", weight = 1f, labelSecondary = "("),
        Key(labelMain = "ن", weight = 1f, labelSecondary = "+"),
        Key(labelMain = "ت", weight = 1f, labelSecondary = "-"),
        Key(labelMain = "ا", weight = 1f, labelSecondary = "&"),
        Key(labelMain = "ل", weight = 1f, labelSecondary = "_"),
        Key(labelMain = "ب", weight = 1f, labelSecondary = "؋"),
        Key(labelMain = "س", weight = 1f, labelSecondary = "#"),
        Key(labelMain = "ش", weight = 1f, labelSecondary = "@"),
    ),
    arrayOf(
        Key(labelMain = "ع", weight = 1f, labelSecondary = "%"),
        Key(labelMain = "غ", weight = 1f, labelSecondary = "\\"),
        Key(labelMain = "ه", weight = 1f, labelSecondary = "؟"),
        Key(labelMain = "پ", weight = 1f, labelSecondary = "!"),
        Key(labelMain = "ټ", weight = 1f, labelSecondary = "؛"),
        Key(labelMain = "و", weight = 1f, labelSecondary = ":"),
        Key(labelMain = "ي", weight = 1f, labelSecondary = "»"),
        Key(labelMain = "ی", weight = 1f, labelSecondary = "«"),
        Key(labelMain = "ې", weight = 1f, labelSecondary = ","),
        Key(labelMain = "ښ", weight = 1f, labelSecondary = "`")
    ),
    arrayOf(
        Key(labelMain = "د", weight = 1f, labelSecondary = "^"),
        Key(labelMain = "ډ", weight = 1f, labelSecondary = "|"),
        Key(labelMain = "ذ", weight = 1f, labelSecondary = ">"),
        Key(labelMain = "ر", weight = 1f, labelSecondary = "<"),
        Key(labelMain = "ړ", weight = 1f, labelSecondary = "}"),
        Key(labelMain = "ز", weight = 1f, labelSecondary = "{"),
        Key(labelMain = "ژ", weight = 1f, labelSecondary = "["),
        Key(labelMain = "ږ", weight = 1f, labelSecondary = "]"),
        Key(labelMain = "ط", weight = 1f, labelSecondary = "~"),
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
            "پښتو",
            4f,
            valueMain = " ",
            shouldShowPopUp = false,
            isCharacter = false,
            shouldBeResized = true,
            shouldShowLanguageName = true,
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
