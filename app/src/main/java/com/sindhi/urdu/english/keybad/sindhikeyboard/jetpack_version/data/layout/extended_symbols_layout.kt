package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val extendedSymbolsLayout = arrayOf(
    arrayOf(
        Key("~", 1f, code = 126),
        Key("`", 1f, code = 96),
        Key("|", 1f, code = 124),
        Key("•", 1f, code = 8226),
        Key("√", 1f, code = 8730),
        Key("π", 1f, code = 960),
        Key("÷", 1f, code = 247),
        Key("×", 1f, code = 215),
        Key("¶", 1f, code = 182),
        Key("∆", 1f, code = 8710)
    ),
    arrayOf(
        Key("¥", 1f, code = 165),
        Key("£", 1f, code = 163),
        Key("€", 1f, code = 8364),
        Key("¢", 1f, code = 162),
        Key("^", 1f, code = 94),
        Key("°", 1f, code = 176),
        Key("=", 1f, code = 61),
        Key("{", 1f, code = 123),
        Key("}", 1f, code = 125),
        Key("\\", 1f, code = 92)
    ),
    arrayOf(
        Key(
            "?123",
            1.5f,
            isCharacter = false,
            isSpecialCharacter = true,
            shouldBeResized = true,
            shouldShowPopUp = false
        ),
        Key("_", 1f, code = 95),
        Key("©", 1f, code = 169),
        Key("®", 1f, code = 174),
        Key("™", 1f, code = 8482),
        Key("✓", 1f, code = 10003),
        Key("[", 1f, code = 91),
        Key("]", 1f, code = 93),
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
            "ABC",
            weight = 1.5f,
            isSpecialCharacter = true,
            shouldShowPopUp = false,
            isCharacter = false,
            shouldShowLanguageName = true,
            shouldBeResized = true,
            shouldBeRounded = true
        ),
        Key("<", 1f, code = 60),
        Key(
            "Numbers",
            1f,
            icon = R.drawable.numbers,
            shouldShowPopUp = false,
            shouldShowIcon = true,
            contentDescription = "Numbers",
        ),
        Key(
            labelMain = "Space",
            weight = 4f,
            valueMain = " ",
            shouldBeResized = true,
            shouldShowPopUp = false,
            isCharacter = false,
            shouldShowLanguageName = true,
            isLanguageKey = true,
            code = 32
        ),
        Key(">", 1f, code = 62),
        Key(
            labelMain = "Done",
            weight = 1.5f,
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
