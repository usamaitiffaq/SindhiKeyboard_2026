package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sindhi.urdu.english.keybad.R

@Composable
fun ToggleSwitch(
    isChecked: Boolean,
    text: String,
    enabled: Boolean = true,
    showIcon: Boolean = true,
    showSwitch: Boolean = true,
    drawableResource: Int,
    description: String = "",
    onCheck: (Boolean) -> Unit
) {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(isChecked = isChecked, text = text, description = description))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        /*Box(modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            contentAlignment = Alignment.Center
        ) {*/
        if (showIcon) {
            Image(painterResource(id = drawableResource),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(35.dp, 35.dp).padding(end = 5.dp).clip(RoundedCornerShape(30)))
        }
        /*}*/
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(start = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            Text(text = switch.text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily(Font(R.font.lexend_medium))))
            if (switch.description.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = switch.description,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily(Font(R.font.lexend_semibold)),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(450)
                    )
                )
            }
        }
        if (showSwitch) {
            Switch(
                modifier = Modifier
                    .weight(1f)
                    .size(50.dp),
                checked = switch.isChecked,
                enabled = enabled,
                onCheckedChange = { isChecked ->
                    switch = switch.copy(isChecked = isChecked)
                    onCheck.invoke(isChecked)
                }
            )
        }
    }
}

@Composable
fun SettingItem(
    text: String,
    description: String = "",
    drawableResource: Int,
    onCheck: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheck() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = drawableResource),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp, 35.dp)
                    .padding(end = 5.dp)
                    .clip(RoundedCornerShape(30))
            )

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily(Font(R.font.lexend_medium))),
                modifier = Modifier
                    .padding(start = 5.dp)
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = FontFamily(Font(R.font.lexend_semibold)),
                        fontSize = 13.sp,
                        fontWeight = FontWeight(450)
                    )
                )
            }
        }
    }
}


data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String,
    val description: String,
)