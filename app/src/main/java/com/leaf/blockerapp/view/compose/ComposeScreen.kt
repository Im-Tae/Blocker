/*
 * Create by Im-Tae on 2024. 8. 5.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.leaf.blocker.compose.material3.DebounceButton
import com.leaf.blocker.compose.material3.ThrottleButton
import timber.log.Timber

@Composable
fun ComposeScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThrottleButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            onClick = {
                Toast.makeText(context, "`Throttle [Default]` Click", Toast.LENGTH_SHORT).show()
                Timber.tag("[Compose] ").i("Throttle [Default]` Click")
            }
        ) {
            Text(text = "Throttle [Default]")
        }
        ThrottleButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            onClick = {
                Toast.makeText(context, "`Throttle [5000Ms]` Click", Toast.LENGTH_SHORT).show()
                Timber.tag("[Compose] ").i("Throttle [5000Ms]` Click")
            },
            skipInterval = 5000L
        ) {
            Text(text = "Throttle [5000Ms]")
        }
        DebounceButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            onClick = {
                Toast.makeText(context, "`Debounce [Default]` Click", Toast.LENGTH_SHORT).show()
                Timber.tag("[Compose] ").i("Debounce [Default]` Click")
            }
        ) {
            Text(text = "Debounce [Default]")
        }
        DebounceButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            onClick = {
                Toast.makeText(context, "`Debounce [5000Ms]` Click", Toast.LENGTH_SHORT).show()
                Timber.tag("[Compose] ").i("Debounce [5000Ms]` Click")
            },
            waitInterval = 5000L
        ) {
            Text(text = "Debounce [5000Ms]")
        }
    }
}

@Preview
@Composable
private fun ComposeViewPreview() {
    ComposeScreen()
}