/*
 * Create by Im-Tae on 2024. 8. 14.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.compose.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.leaf.blocker.Blocker
import com.leaf.blocker.extend.debounce
import com.leaf.blocker.extend.throttleFirst

@Composable
fun ThrottleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    skipInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Button(
        onClick = throttleFirst(
            skipInterval = skipInterval,
            coroutineScope = lifecycleOwner.lifecycleScope,
            callback = onClick
        ),
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun DebounceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    waitInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Button(
        onClick = debounce(
            waitInterval = waitInterval,
            coroutineScope = lifecycleOwner.lifecycleScope,
            callback = onClick
        ),
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}