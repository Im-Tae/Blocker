/*
 * Create by Im-Tae on 2022. 11. 7.
 *
 * Copyright (c) 2022. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leaf.blocker.extend.DebounceButton
import com.leaf.blocker.extend.ThrottleButton
import com.leaf.blockerapp.compose.DefaultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DefaultTheme {
                ComposeView()
            }
        }
    }

    @Composable
    fun ComposeView() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ThrottleButton(
                onClick = { showToast("`Throttle [Default]` Click") }
            ) {
                Text(text = "Throttle [Default]")
            }
            ThrottleButton(
                onClick = { showToast("`Throttle [5000Ms]` Click") },
                skipInterval = 5000L
            ) {
                Text(text = "Throttle [Default]")
            }
            DebounceButton(
                onClick = { showToast("`Debounce [Default]` Click") }
            ) {
                Text(text = "Debounce [Default]")
            }
            DebounceButton(
                onClick = { showToast("`Debounce [5000Ms]` Click") },
                waitInterval = 5000L
            ) {
                Text(text = "Debounce [Default]")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewComposeView() {
        ComposeView()
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}