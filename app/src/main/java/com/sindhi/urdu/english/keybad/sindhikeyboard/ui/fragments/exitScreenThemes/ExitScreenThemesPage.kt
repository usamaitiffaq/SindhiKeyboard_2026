package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.exitScreenThemes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.myExitScreenThemes
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents.ThemeItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExitScreenThemesPage(onApplyThemeClick: (CustomTheme) -> Unit) {

    myExitScreenThemes.forEachIndexed { categoryIndex, themes ->
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 2) {
            themes.forEachIndexed { themeIndex, theme ->

                ThemeItem(
                    backgroundColor = theme.backgroundColor,
                    backgroundColor2 = theme.backgroundColor2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .weight(1f),
                    onClick = {
                        onApplyThemeClick(theme)
                    },
                    theme = theme,
                    isSelected = false
                )
            }
        }
    }
}