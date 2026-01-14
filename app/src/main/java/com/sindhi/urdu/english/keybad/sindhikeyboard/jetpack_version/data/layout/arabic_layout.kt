package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val arabicLayout = arrayOf(
    arrayOf(
        Key("١", 1f, code = 49),
        Key("٢", 1f, code = 50),
        Key("٣", 1f, code = 51),
        Key("٤", 1f, code = 52),
        Key("٥", 1f, code = 53),
        Key("٦", 1f, code = 54),
        Key("٧", 1f, code = 55),
        Key("٨", 1f, code = 56),
        Key("٩", 1f, code = 57),
        Key("٠", 1f, code = 48)
    ),
    arrayOf(
        Key(labelMain = "ج", weight = 1f, labelSecondary = "°"),
        Key(labelMain = "ح", weight = 1f, labelSecondary = "{"),
        Key(labelMain = "خ", weight = 1f, labelSecondary = "}"),
        Key(labelMain = "ه", weight = 1f, labelSecondary = ">"),
        Key(labelMain = "ع", weight = 1f, labelSecondary = "<"),
        Key(labelMain = "غ", weight = 1f, labelSecondary = "["),
        Key(labelMain = "ف", weight = 1f, labelSecondary = "]"),
        Key(labelMain = "ق", weight = 1f, labelSecondary = "="),
        Key(labelMain = "ث", weight = 1f, labelSecondary = "|"),
        Key(labelMain = "ص", weight = 1f, labelSecondary = "\\"),
        Key(labelMain = "ض", weight = 1f, labelSecondary = "%")
    ),
    arrayOf(
        Key(labelMain = "ط", weight = 1f, labelSecondary = "~"),
        Key(labelMain = "ك", weight = 1f, labelSecondary = "/"),
        Key(labelMain = "م", weight = 1f, labelSecondary = ")"),
        Key(labelMain = "ن", weight = 1f, labelSecondary = "("),
        Key(labelMain = "ت", weight = 1f, labelSecondary = "+"),
        Key(labelMain = "ا", weight = 1f, labelSecondary = "~"),
        Key(labelMain = "ل", weight = 1f, labelSecondary = "&"),
        Key(labelMain = "ب", weight = 1f, labelSecondary = "-"),
        Key(labelMain = "ي", weight = 1f, labelSecondary = "$"),
        Key(labelMain = "س", weight = 1f, labelSecondary = "#"),
        Key(labelMain = "ش", weight = 1f, labelSecondary = "@")
    ),
    arrayOf(
        Key(labelMain = "د", weight = 1f, labelSecondary = "٪"),
        Key(labelMain = "ظ", weight = 1f, labelSecondary = "\\"),
        Key(labelMain = "ز", weight = 1f, labelSecondary = "؟"),
        Key(labelMain = "و", weight = 1f, labelSecondary = "!"),
        Key(labelMain = "ة", weight = 1f, labelSecondary = "؛"),
        Key(labelMain = "ى", weight = 1f, labelSecondary = ":"),
        Key(labelMain = "ر", weight = 1f, labelSecondary = "'"),
        Key(labelMain = "ؤ", weight = 1f, labelSecondary = "\""),
        Key(labelMain = "ء", weight = 1f, labelSecondary = "*"),
        Key(labelMain = "ذ", weight = 1f, labelSecondary = "`"),
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
            shouldShowPopUp = false,
            shouldBeResized = true,
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
            "العربية",
            4f,
            valueMain = " ",
            shouldShowPopUp = false,
            isCharacter = false,
            isLanguageKey = true,
            shouldBeResized = true,
            shouldShowLanguageName = true,
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
