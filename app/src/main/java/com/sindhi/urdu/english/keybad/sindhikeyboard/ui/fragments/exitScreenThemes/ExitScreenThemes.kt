package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.exitScreenThemes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme

@Composable
fun ExitScreenThemes(onApplyThemeClick: (CustomTheme) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp)) {
        item {
            ExitScreenThemesPage(onApplyThemeClick)
        }
    }
}