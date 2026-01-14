package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val numbersLayout = arrayOf(
    arrayOf(
        // Row 1
        Key("+", 2f, code = 43, isSpecialCharacter = true, shouldShowPopUp = false),
        Key("1", 3f, code = 49, shouldShowPopUp = false),
        Key("2", 3f, code = 50, shouldShowPopUp = false),
        Key("3", 3f, code = 51, shouldShowPopUp = false),
        Key("%", 2f, code = 37, isSpecialCharacter = true, shouldShowPopUp = false)
    ),
    arrayOf(
        // Row 2
        Key("(", 2f, code = 40, isSpecialCharacter = true, shouldShowPopUp = false),
        Key("4", 3f, code = 52, shouldShowPopUp = false),
        Key("5", 3f, code = 53, shouldShowPopUp = false),
        Key("6", 3f, code = 54, shouldShowPopUp = false),
        Key(
            "Space",
            2f,
            code = 32,
            icon = R.drawable.space,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            isCharacter = false,
            isLanguageKey = true,
            contentDescription = "Space",
            isSpecialCharacter = true
        )
    ),
    arrayOf(
        // Row 3
        Key(")", 2f, code = 41, isSpecialCharacter = true, shouldShowPopUp = false),
        Key("7", 3f, code = 55, shouldShowPopUp = false),
        Key("8", 3f, code = 56, shouldShowPopUp = false),
        Key("9", 3f, code = 57, shouldShowPopUp = false),
        Key(
            "Delete",
            2f,
            code = 37,
            icon = R.drawable.backspace,
            shouldShowIcon = true,
            shouldShowPopUp = false,
            contentDescription = "Delete",
            isCharacter = false,
            isSpecialCharacter = true
        )
    ),
    arrayOf(
        // Row 4
        Key(
            "ABC",
            2f,
            isSpecialCharacter = true,
            shouldShowPopUp = false,
            isCharacter = false,
            shouldShowLanguageName = true,
            shouldBeResized = true,
            shouldBeRounded = true
        ),
        Key(
            "!?#",
            1.5f,
            shouldShowPopUp = false,
            shouldBeResized = true,
            isCharacter = false,
        ),
        Key(
            "0",
            2.3f,
            shouldShowPopUp = false,
            code = 48
        ),
        Key("=", 1.5f, code = 61, shouldShowPopUp = false),
        Key(
            labelMain = "Done",
            weight = 2f,
            isSpecialCharacter = true,
            icon = R.drawable.done_icon,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            contentDescription = "Done",
            isCharacter = false,
            shouldBeRounded = true
        )
    )
)
