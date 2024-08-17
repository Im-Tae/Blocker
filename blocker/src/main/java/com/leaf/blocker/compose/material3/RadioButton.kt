/*
 * Create by Im-Tae on 2024. 8. 14.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.compose.material3

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.leaf.blocker.Blocker
import com.leaf.blocker.extend.debounce
import com.leaf.blocker.extend.throttleFirst

/**
 * A ThrottleRadioButton is a RadioButton that prevents multiple clicks in a short time.
 * This callback emit the most recent items emitted by an Observable within periodic time intervals.
 *
 * @param skipInterval skip interval; default interval is 2000Ms
 */
@Composable
fun ThrottleRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    skipInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    RadioButton(
        selected = selected,
        onClick = onClick?.let { callback ->
            throttleFirst(
                skipInterval = skipInterval,
                coroutineScope = lifecycleOwner.lifecycleScope,
                callback = callback
            )
        } ?: run { { } },
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}

/**
 * A DebounceRadioButton is a RadioButton that prevents multiple clicks in a short time.
 * This callback only emit an item from an Observable if a particular timespan has passed without it emitting another item.
 *
 * @param waitInterval wait interval; default interval is 2000Ms
 */
@Composable
fun DebounceRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    waitInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    RadioButton(
        selected = selected,
        onClick = onClick?.let { callback ->
            debounce(
                waitInterval = waitInterval,
                coroutineScope = lifecycleOwner.lifecycleScope,
                callback = callback
            )
        } ?: run { { } },
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}