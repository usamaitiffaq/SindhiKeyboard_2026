package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val husneguftar = arrayOf(
    arrayOf(
        arrayOf(
            Key(
                labelMain = "اردو",
                weight = 1f,
                icon = R.drawable.languages,
                shouldShowPopUp = false,
                shouldShowIcon = true,
                isCharacter = false,
                contentDescription = "Languages",
            ),
            Key("،", 1f, code = 1548),
            Key(
                " حُسن گفتار ",
                5f,
                valueMain = " ",
                shouldShowPopUp = false,
                isCharacter = false,
                shouldBeResized = true,
                shouldShowLanguageName = true,
                isLanguageKey = true,
                code = 32
            ),
            Key("۔", 1f),
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
        )
    )
)