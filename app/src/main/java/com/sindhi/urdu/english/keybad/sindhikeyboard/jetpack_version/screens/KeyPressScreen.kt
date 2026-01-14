package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents.ToggleSwitch
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.MyPreferences

@Composable
fun KeyPressScreen(context: Context) {
    val myPreferences = MyPreferences(context)

    Scaffold() { paddingValues ->
        Column {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(paddingValues)) {
                ToggleSwitch(
                    isChecked = myPreferences.getSound(),
                    text = stringResource(id = R.string.soundOnKeyPress),
                    showIcon = false,
                    drawableResource = R.drawable.ic_sound_keypress) { isChecked ->
                    /*Preferences.edit { putBoolean(Preferences.playSound, isChecked) }*/
                    MyPreferences(context = context).setKeyPressSounds(isChecked)
                }
            }
        }
    }
}