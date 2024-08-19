/*
 * Create by Im-Tae on 2024. 8. 17.
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leaf.blocker.compose.material3.DebounceCheckBox
import com.leaf.blocker.compose.material3.ThrottleCheckBox
import timber.log.Timber

@Composable
fun CheckBoxContent(
    modifier: Modifier = Modifier
) {

    var firstCheckbox by remember { mutableStateOf(false) }
    var secondCheckbox by remember { mutableStateOf(false) }
    var thirdCheckbox by remember { mutableStateOf(false) }
    var fourCheckbox by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ThrottleLabelCheckBox(
                label = "Throttle [Default]",
                checked = firstCheckbox,
                onCheckedChange = { isChecked ->
                    Toast.makeText(context, "`ThrottleCheckBox [Default]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("ThrottleCheckBox [Default]` Click")
                    firstCheckbox = isChecked
                }
            )
            ThrottleLabelCheckBox(
                label = "Throttle [1000L]",
                checked = secondCheckbox,
                skipInterval = 1000L,
                onCheckedChange = { isChecked ->
                    Toast.makeText(context, "`ThrottleCheckBox [1000L]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("ThrottleCheckBox [1000L]` Click")
                    secondCheckbox = isChecked
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DebounceLabelCheckBox(
                label = "Debounce [Default]",
                checked = thirdCheckbox,
                onCheckedChange = { isChecked ->
                    Toast.makeText(context, "`DebounceCheckBox [Default]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("DebounceCheckBox [Default]` Click")
                    thirdCheckbox = isChecked
                }
            )
            DebounceLabelCheckBox(
                label = "Debounce [1000L]",
                checked = fourCheckbox,
                waitInterval = 1000L,
                onCheckedChange = { isChecked ->
                    Toast.makeText(context, "`DebounceCheckBox [1000L]` Click", Toast.LENGTH_SHORT).show()
                    Timber.tag("[Compose] ").i("DebounceCheckBox [1000L]` Click")
                    fourCheckbox = isChecked
                }
            )
        }
    }
}

@Composable
private fun ThrottleLabelCheckBox(
    modifier: Modifier = Modifier,
    label: String,
    checked: Boolean,
    skipInterval: Long? = null,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        skipInterval?.let { interval ->
            ThrottleCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                skipInterval = interval
            )
        } ?: run {
            ThrottleCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

        }
    }
}

@Composable
private fun DebounceLabelCheckBox(
    modifier: Modifier = Modifier,
    label: String,
    checked: Boolean,
    waitInterval: Long? = null,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        waitInterval?.let { interval ->
            DebounceCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                waitInterval = interval
            )
        } ?: run {
            DebounceCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

        }
    }
}

@Preview
@Composable
private fun CheckBoxContentPreview() {
    CheckBoxContent()
}