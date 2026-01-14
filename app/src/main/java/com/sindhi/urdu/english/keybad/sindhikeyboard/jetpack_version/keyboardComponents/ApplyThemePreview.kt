package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sindhi.urdu.english.keybad.R

@Composable
fun ApplyThemePreview(themeName: String, backgroundColor: Color, backgroundColor2: Color? = null, ctx: Context) {
    val resourceID: Int
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



    resourceID = if (themeName == "Gradient") {
        R.drawable.ic_full_keypad_preview_black
    } else if (themeName.endsWith("LIGHT")) {
        R.drawable.ic_full_keypad_preview_black
    } else {
        R.drawable.ic_full_keypad_preview_off_white
    }

    /*Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(background)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopStart) {
            Image(
                painterResource(id = resourceID),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize())
        }
    }*/

    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(background)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
            /*.padding(10.dp)*/,
            contentAlignment = Alignment.TopStart) {

            val imageBitmap = remember { mutableStateOf<ImageBitmap?>(null) }
            Glide.with(ctx)
                .asBitmap()
                .load(resourceID)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imageBitmap.value = resource.asImageBitmap()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Do nothing
                    }
                })

            imageBitmap.value?.let { bitmap ->
                Image(
                    bitmap = bitmap,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}