package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.MyPreferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes.tabPages.DefaultPage
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes.tabPages.GradientPage
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes.tabPages.SolidDarkPage
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes.tabPages.SolidLightPage

@Composable
fun ThemesScreen(context: Context, onApplyThemeClick: (CustomTheme) -> Unit) {
    val myPreferences = MyPreferences(context)

    var selectedTab by remember { mutableIntStateOf(0) }
    val titleAndIcons = listOf("Default", "Gradient", "Dark", "Light")

    Scaffold(topBar = {
        Column {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            ) {
                titleAndIcons.forEachIndexed { index, title ->
                    Tab(selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text = title, maxLines = 2) })
                }
            }
        }
    }, content = { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                when (selectedTab) {
                    0 -> DefaultPage(myPreferences, onApplyThemeClick, innerPadding)
                    1 -> GradientPage(myPreferences, onApplyThemeClick, innerPadding)
                    2 -> SolidDarkPage(myPreferences, onApplyThemeClick, innerPadding)
                    3 -> SolidLightPage(myPreferences, onApplyThemeClick, innerPadding)
                }
            }



        }
    }
    )
}