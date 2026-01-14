package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val nepali_layout = arrayOf(
    arrayOf(
        Key(labelMain = "ط", weight = 1f, labelSecondary = "١", codeSecondary = 49),
        Key(labelMain = "ظ", weight = 1f, labelSecondary = "٢", codeSecondary = 50),
        Key(labelMain = "ئ", weight = 1f, labelSecondary = "٣", codeSecondary = 51),
        Key(labelMain = "ح", weight = 1f, labelSecondary = "٤", codeSecondary = 52),
        Key(labelMain = "ث", weight = 1f, labelSecondary = "٥", codeSecondary = 53),
        Key(labelMain = "ٽ", weight = 1f, labelSecondary = "٦", codeSecondary = 54),
        Key(labelMain = "ڙ", weight = 1f, labelSecondary = "٧", codeSecondary = 55),
        Key(labelMain = "غ", weight = 1f, labelSecondary = "٨", codeSecondary = 56),
        Key(labelMain = "ص", weight = 1f, labelSecondary = "٩", codeSecondary = 57),
        Key(labelMain = "ض", weight = 1f, labelSecondary = "٠", codeSecondary = 48)
    ),
    arrayOf(
        Key(labelMain = "پ", weight = 1f, labelSecondary = "/"),
        Key(labelMain = "ہ", weight = 1f, labelSecondary = ")"),
        Key(labelMain = "ي", weight = 1f, labelSecondary = "("),
        Key(labelMain = "ء", weight = 1f, labelSecondary = "+"),
        Key(labelMain = "ڪ", weight = 1f, labelSecondary = "-"),
        Key(labelMain = "ت", weight = 1f, labelSecondary = "&"),
        Key(labelMain = "ر", weight = 1f, labelSecondary = "_"),
        Key(labelMain = "ع", weight = 1f, labelSecondary = "؋"),
        Key(labelMain = "و", weight = 1f, labelSecondary = "#"),
        Key(labelMain = "ق", weight = 1f, labelSecondary = "@")
    ),
    arrayOf(
        Key(labelMain = "ل", weight = 1f, labelSecondary = "%"),
        Key(labelMain = "ک", weight = 1f, labelSecondary = "\\"),
        Key(labelMain = "ج", weight = 1f, labelSecondary = "؟"),
        Key(labelMain = "ح", weight = 1f, labelSecondary = "!"),
        Key(labelMain = "ڏ", weight = 1f, labelSecondary = "؛"),
        Key(labelMain = "گ", weight = 1f, labelSecondary = ":"),
        Key(labelMain = "ف", weight = 1f, labelSecondary = "»"),
        Key(labelMain = "د", weight = 1f, labelSecondary = "«"),
        Key(labelMain = "س", weight = 1f, labelSecondary = ","),
        Key(labelMain = "ا", weight = 1f, labelSecondary = "`")
    ),
    arrayOf(
        Key(labelMain = "م", weight = 1f, labelSecondary = "^"),
        Key(labelMain = "ن", weight = 1f, labelSecondary = "|"),
        Key(labelMain = "ب", weight = 1f, labelSecondary = ">"),
        Key(labelMain = "چ", weight = 1f, labelSecondary = "<"),
        Key(labelMain = "خ", weight = 1f, labelSecondary = "}"),
        Key(labelMain = "ش", weight = 1f, labelSecondary = "{"),
        Key(labelMain = "ز", weight = 1f, labelSecondary = "["),
        Key(labelMain = "ذ", weight = 1f, labelSecondary = "]"),
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
            "سنڌي",
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
