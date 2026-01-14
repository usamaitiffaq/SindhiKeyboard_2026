package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key

val textEditLayout = arrayOf(
    arrayOf(
        arrayOf(
            Key(
                labelMain = "Before",
                weight = 1f,
                icon = R.drawable.arrow_left,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Before"
            )
        ),
        arrayOf(
            Key(
                labelMain = "Up",
                weight = 1f,
                icon = R.drawable.arrow_up,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Up"
            ),
            Key(
                labelMain = "Select",
                weight = 1f,
                shouldShowPopUp = false,
                isSelectionKey = true,
                shouldBeResized = true,
                isCharacter = false,
                contentDescription = "Select"
            ),
            Key(
                labelMain = "Down",
                weight = 1f,
                icon = R.drawable.arrow_down,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Down"
            )
        ),
        arrayOf(
            Key(
                labelMain = "Next",
                weight = 1f,
                icon = R.drawable.arrow_right,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Next"
            )
        ),
        arrayOf(
            Key(
                labelMain = "Select all",
                weight = 1f,
                isSpecialCharacter = true,
                shouldShowPopUp = false,
                shouldBeResized = true,
                isCharacter = false,
                contentDescription = "Select all"
            ),
            Key(
                labelMain = "Copy",
                weight = 1f,
                isSpecialCharacter = true,
                shouldShowPopUp = false,
                shouldBeResized = true,
                isCharacter = false,
                contentDescription = "Copy"
            ),
            Key(
                labelMain = "Paste",
                weight = 1f,
                isSpecialCharacter = true,
                shouldShowPopUp = false,
                isCharacter = false,
                shouldBeResized = true,
                contentDescription = "Paste"
            )
        ),
    ),
    arrayOf(
        arrayOf(
            Key(
                labelMain = "Doc Start",
                weight = 1.5f,
                icon = R.drawable.first_page,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Start"
            )
        ),
        arrayOf(
            Key(
                labelMain = "Doc End",
                weight = 1.5f,
                icon = R.drawable.last_page,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "End"
            )
        ),
        arrayOf(
            Key(
                "Delete",
                1f,
                isSpecialCharacter = true,
                icon = R.drawable.backspace,
                shouldShowIcon = true,
                shouldShowPopUp = false,
                isCharacter = false,
                contentDescription = "Delete"
            )
        )
    )
)
