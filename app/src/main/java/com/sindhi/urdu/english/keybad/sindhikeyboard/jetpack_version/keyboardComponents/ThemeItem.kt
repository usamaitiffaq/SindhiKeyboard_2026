package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.theme.defaultColor
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.theme.white

@Composable
fun ThemeItem(
    backgroundColor: Color,
    backgroundColor2: Color? = null,
    modifier: Modifier,
    onClick: () -> Unit,
    theme: CustomTheme,
    isSelected: Boolean
) {

    val background = if (backgroundColor2 == null) {
        Brush.linearGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor2
            )
        )
    }

    Column(
        modifier = modifier
            .width(150.dp)
            .fillMaxHeight(0.16f)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(background)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painterResource(id = R.drawable.ic_keypad_for_themes),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().padding(top = 10.dp))
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(20.dp)
                        .background(defaultColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null,
                        tint = white
                    )
                }
            }
        }
    }
}