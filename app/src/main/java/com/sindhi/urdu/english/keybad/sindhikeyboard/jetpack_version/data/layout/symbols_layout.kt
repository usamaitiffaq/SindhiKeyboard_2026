package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val symbolsLayout = arrayOf(
    arrayOf(
        Key("1", 1f, code = 49),
        Key("2", 1f, code = 50),
        Key("3", 1f, code = 51),
        Key("4", 1f, code = 52),
        Key("5", 1f, code = 53),
        Key("6", 1f, code = 54),
        Key("7", 1f, code = 55),
        Key("8", 1f, code = 56),
        Key("9", 1f, code = 57),
        Key("0", 1f, code = 48)
    ),
    arrayOf(
        Key("@", 1f, code = 64),
        Key("#", 1f, code = 35),
        Key("$", 1f, code = 36),
        Key("&", 1f, code = 38),
        Key("_", 1f, code = 95),
        Key("-", 1f, code = 45),
        Key("+", 1f, code = 43),
        Key("(", 1f, code = 40),
        Key(")", 1f, code = 41),
        Key("/", 1f, code = 47)
    ),
    arrayOf(
        Key(
            "=\\<",
            1.5f,
            isCharacter = false,
            shouldBeResized = true,
            isSpecialCharacter = true,
            shouldShowPopUp = false
        ),
        Key("*", 1f, code = 42),
        Key("\"", 1f, code = 34),
        Key("'", 1f, code = 39),
        Key(":", 1f, code = 58),
        Key(";", 1f, code = 59),
        Key("!", 1f, code = 33),
        Key("?", 1f, code = 63),
        Key(
            "Delete",
            1.5f,
            isSpecialCharacter = true,
            icon = R.drawable.backspace,
            shouldShowIcon = true,
            shouldShowPopUp = false,
            isCharacter = false,
            contentDescription = "Delete"
        )
    ),
    arrayOf(
        Key(
            "ABC",
            weight = 1.5f,
            isCharacter = false,
            isSpecialCharacter = true,
            shouldShowPopUp = false,
            shouldShowLanguageName = true,
            shouldBeResized = true,
            shouldBeRounded = true
        ),
        Key(",", 1f, code = 44),
        Key(
            "Numbers",
            1f,
            icon = R.drawable.numbers,
            shouldShowIcon = true,
            shouldShowPopUp = false,
            isCharacter = false,
            contentDescription = "Numbers",
        ),
        Key(
            "Space",
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
