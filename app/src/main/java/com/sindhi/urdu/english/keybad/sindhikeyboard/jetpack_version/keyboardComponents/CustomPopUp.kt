package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.sindhi.urdu.english.keybad.R

@Composable
fun PopupScreen(
    label: String,
    isVisible: Boolean,
    offset: IntOffset,
    onClick: () -> Unit
) {
    val cornerSize = dimensionResource(id = R.dimen.key_borderRadius)
    val popUpWidth = dimensionResource(id = R.dimen.key_width)
    val popUpHeight = dimensionResource(id = R.dimen.key_height)

    if (isVisible) {
        Popup(
            offset = offset,
            properties = PopupProperties(clippingEnabled = true, dismissOnClickOutside = true), alignment = Alignment.Center) {
            Box(Modifier
                    .size(width = popUpWidth + 15.dp, height = popUpHeight * 1.1f)
                    .background(MaterialTheme.colorScheme.onTertiaryContainer, RoundedCornerShape(100))
                    /*.clip(RoundedCornerShape(100))*/
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        onClick()
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = label,
                    /*modifier = Modifier.clip(RoundedCornerShape(100)),*/
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = dimensionResource(id = R.dimen.key_popup_textSize).value.sp
                )
            }
        }
    }
}
