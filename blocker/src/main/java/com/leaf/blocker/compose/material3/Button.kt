/*
 * Create by Im-Tae on 2022. 11. 7.
 *
 * Copyright (c) 2022. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.compose.material3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.leaf.blocker.Blocker
import com.leaf.blocker.extend.debounce
import com.leaf.blocker.extend.throttleFirst

/**
 * A ThrottleButton is a button that prevents multiple clicks in a short time.
 * This callback emit the most recent items emitted by an Observable within periodic time intervals.
 *
 * @param skipInterval skip interval; default interval is 2000Ms
 */
@Composable
fun ThrottleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    skipInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
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
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * A DebounceButton is a button that prevents multiple clicks in a short time.
 * This callback only emit an item from an Observable if a particular timespan has passed without it emitting another item.
 *
 * @param waitInterval wait interval; default interval is 2000Ms
 */
@Composable
fun DebounceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    waitInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
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
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}