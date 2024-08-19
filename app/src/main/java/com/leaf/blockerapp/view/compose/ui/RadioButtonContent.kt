/*
 * Create by Im-Tae on 2024. 8. 14.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.compose.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leaf.blocker.compose.material3.DebounceRadioButton
import com.leaf.blocker.compose.material3.ThrottleRadioButton
import timber.log.Timber

@Composable
fun RadioButtonContent(
    modifier: Modifier = Modifier
) {

    var selectedThrottleId by remember { mutableIntStateOf(0) }
    var selectedDebounceId by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ThrottleRadioLabelButton(
                label = "Throttle [Default]",
                selected = selectedThrottleId == 0,
                onClick = {
                    Toast.makeText(context, "`ThrottleRadioButton [Default]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("ThrottleRadioButton [Default]` Click")
                    selectedThrottleId = 0
                }
            )
            ThrottleRadioLabelButton(
                label = "Throttle [1000L]",
                selected = selectedThrottleId == 1,
                skipInterval = 1000L,
                onClick = {
                    Toast.makeText(context, "`ThrottleRadioButton [1000L]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("ThrottleRadioButton [1000L]` Click")
                    selectedThrottleId = 1
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DebounceRadioLabelButton(
                label = "Debounce [Default]",
                isSelected = selectedDebounceId == 0,
                onClick = {
                    Toast.makeText(context, "`DebounceRadioButton [Default]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("DebounceRadioButton [Default]` Click")
                    selectedDebounceId = 0
                }
            )
            DebounceRadioLabelButton(
                label = "Debounce [1000L]",
                isSelected = selectedDebounceId == 1,
                waitInterval = 1000L,
                onClick = {
                    Toast.makeText(context, "`DebounceRadioButton [1000L]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("DebounceRadioButton [1000L]` Click")
                    selectedDebounceId = 1
                }
            )
        }
    }
}

@Composable
private fun ThrottleRadioLabelButton(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    skipInterval: Long? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        skipInterval?.let { interval ->
            ThrottleRadioButton(
                selected = selected,
                onClick = onClick,
                skipInterval = interval
            )
        } ?: run {
            ThrottleRadioButton(
                selected = selected,
                onClick = onClick
            )

        }
    }
}

@Composable
private fun DebounceRadioLabelButton(
    modifier: Modifier = Modifier,
    label: String,
    isSelected: Boolean,
    waitInterval: Long? = null,
    onClick: (() -> Unit)?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        waitInterval?.let { interval ->
            DebounceRadioButton(
                selected = isSelected,
                onClick = onClick,
                waitInterval = interval
            )
        } ?: run {
            DebounceRadioButton(
                selected = isSelected,
                onClick = onClick
            )
        }
    }

}

@Preview
@Composable
private fun RadioButtonContentPreview() {
    RadioButtonContent()
}