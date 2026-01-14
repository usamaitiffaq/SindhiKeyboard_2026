package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.R

@Composable
fun SettingsCategory(
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge
                .copy(fontFamily = FontFamily(Font(R.font.lexend_medium))),
            color = Color.Black
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun ThemesCategory(title: String) {
    Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)) {
        Text(text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily(Font(R.font.lexend_semibold))),
            color = Color.Black)
    }
    Spacer(modifier = Modifier.height(4.dp))
}
