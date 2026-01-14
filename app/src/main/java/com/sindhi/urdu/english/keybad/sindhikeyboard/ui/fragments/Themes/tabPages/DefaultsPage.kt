package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes.tabPages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.myDefaultThemes
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents.ThemeItem
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.MyPreferences

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DefaultPage(myPreferences: MyPreferences, onApplyThemeClick: (CustomTheme) -> Unit, innerPadding: PaddingValues) {
    val selectedTheme = myPreferences.getTheme()
    val isSelectedLists = myDefaultThemes.mapIndexed { _, categoryThemes ->
        categoryThemes.mapIndexed { _, theme ->
            mutableStateOf(selectedTheme == theme.name)
        }
    }

    myDefaultThemes.forEachIndexed { categoryIndex, themes ->
//        themes.firstOrNull()?.category?.let { ThemesCategory(title = it) }

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 2) {
            themes.forEachIndexed { themeIndex, theme ->
                val isSelected by isSelectedLists[categoryIndex][themeIndex]

                ThemeItem(
                    backgroundColor = theme.backgroundColor,
                    backgroundColor2 = theme.backgroundColor2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .weight(1f),
                    onClick = {
                        isSelectedLists.forEachIndexed { i, _ ->
                            isSelectedLists[i].forEachIndexed { j, _ ->
                                isSelectedLists[i][j].value = (i == categoryIndex && j == themeIndex)
                            }
                        }

                        onApplyThemeClick(theme)
                    },
                    theme = theme,
                    isSelected = isSelected
                )
            }
        }
    }
}