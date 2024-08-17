/*
 * Create by Im-Tae on 2024. 8. 17.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.compose.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.leaf.blocker.Blocker
import com.leaf.blocker.extend.debounce
import com.leaf.blocker.extend.throttleFirst

/**
 * A ThrottleCheckBox is a Checkbox that prevents multiple clicks in a short time.
 * This callback emit the most recent items emitted by an Observable within periodic time intervals.
 *
 * @param skipInterval skip interval; default interval is 2000Ms
 */
@Composable
fun ThrottleCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    skipInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var internalChecked by remember { mutableStateOf(checked) }

    val throttledOnClick = remember {
        throttleFirst(
            skipInterval = skipInterval,
            coroutineScope = lifecycleOwner.lifecycleScope
        ) {
            internalChecked = !internalChecked
            onCheckedChange?.invoke(internalChecked)
        }
    }

    Box(
        modifier = Modifier
            .clickable(
                enabled = enabled,
                onClick = onCheckedChange?.let {
                    throttledOnClick
                } ?: run { { } }
            )
    ) {
        TriStateCheckbox(
            state = ToggleableState(internalChecked),
            onClick = null,
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource
        )
    }
}

/**
 * A DebounceCheckBox is a CheckBox that prevents multiple clicks in a short time.
 * This callback only emit an item from an Observable if a particular timespan has passed without it emitting another item.
 *
 * @param waitInterval wait interval; default interval is 2000Ms
 */
@Composable
fun DebounceCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    waitInterval: Long = Blocker.getInterval(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    TriStateCheckbox(
        state = ToggleableState(checked),
        onClick = onCheckedChange?.let { checkCallback ->
            debounce(
                waitInterval = waitInterval,
                coroutineScope = lifecycleOwner.lifecycleScope,
                callback = {
                    checkCallback.invoke(!checked)
                }
            )
        } ?: run { null },
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}