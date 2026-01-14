package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val qwertyLayout_english = arrayOf(
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
        Key(labelMain = "q", weight = 1f, labelSecondary = "1", code = 113, codeSecondary = 49),
        Key("w", 1f, labelSecondary = "2", code = 119, codeSecondary = 50),
        Key("e", 1f, labelSecondary = "3", code = 101, codeSecondary = 51),
        Key("r", 1f, labelSecondary = "4", code = 114, codeSecondary = 52),
        Key("t", 1f, labelSecondary = "5", code = 116, codeSecondary = 53),
        Key("y", 1f, labelSecondary = "6", code = 121, codeSecondary = 54),
        Key("u", 1f, labelSecondary = "7", code = 117, codeSecondary = 55),
        Key("i", 1f, labelSecondary = "8", code = 105, codeSecondary = 56),
        Key("o", 1f, labelSecondary = "9", code = 111, codeSecondary = 57),
        Key("p", 1f, labelSecondary = "0", code = 112, codeSecondary = 48)
    ),
    arrayOf(
        Key("a", 1f, labelSecondary = "@", code = 97, codeSecondary = 64),
        Key("s", 1f, labelSecondary = "#", code = 115, codeSecondary = 35),
        Key("d", 1f, labelSecondary = "$", code = 100, codeSecondary = 36),
        Key("f", 1f, labelSecondary = "_", code = 102, codeSecondary = 95),
        Key("g", 1f, labelSecondary = "&", code = 103, codeSecondary = 38),
        Key("h", 1f, labelSecondary = "-", code = 104, codeSecondary = 45),
        Key("j", 1f, labelSecondary = "+", code = 106, codeSecondary = 43),
        Key("k", 1f, labelSecondary = "(", code = 107, codeSecondary = 40),
        Key("l", 1f, labelSecondary = ")", code = 108, codeSecondary = 41)
    ),
    arrayOf(
        Key(
            "CAPS",
            1.4f,
            isSpecialCharacter = true,
            icon = R.drawable.caps_lock_off,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            contentDescription = "Capitalize"
        ),
        Key("z", 1f, labelSecondary = "*", code = 122, codeSecondary = 42),
        Key("x", 1f, labelSecondary = "\"", code = 120, codeSecondary = 34),
        Key("c", 1f, labelSecondary = "'", code = 99, codeSecondary = 39),
        Key("v", 1f, labelSecondary = ":", code = 118, codeSecondary = 58),
        Key("b", 1f, labelSecondary = ";", code = 98, codeSecondary = 59),
        Key("n", 1f, labelSecondary = "!", code = 110, codeSecondary = 33),
        Key("m", 1f, labelSecondary = "?", code = 109, codeSecondary = 63),
        Key(
            "Delete",
            1.5f,
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
            shouldBeResized = true,
            isSpecialCharacter = true,
            shouldShowPopUp = false,
            shouldBeRounded = true
        ),
        Key(",", 1f, code = 44),
        Key(
            labelMain = "Languages",
            weight = 2f,
            icon = R.drawable.ic_sindhi_txt,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            contentDescription = "Languages",
        ),
        /*Key(
            "English",
            5f,
            valueMain = " ",
            shouldShowPopUp = false,
            isCharacter = false,
            shouldBeResized = true,
            isLanguageKey = true,
            shouldShowLanguageName = true,
            code = 32
        ),*/
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
