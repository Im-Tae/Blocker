/*
 * Create by Im-Tae on 2021. 9. 17.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColor = darkColors(
    primary = Color.DarkGray,
    primaryVariant = Color.DarkGray,
    secondary = Color.DarkGray
)

private val LightColor = lightColors(
    primary = Color.LightGray,
    primaryVariant = Color.LightGray,
    secondary = Color.LightGray,
    background = Color.White
)

@Composable

fun DefaultTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkColor else LightColor

    MaterialTheme(
        colors = colors,
        content = content
    )
}