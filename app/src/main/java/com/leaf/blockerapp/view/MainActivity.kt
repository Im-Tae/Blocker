/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leaf.blockerapp.compose.ButtonView
import com.leaf.blockerapp.compose.DefaultTheme
import com.leaf.blockerapp.view.databinding.DataBindingActivity
import com.leaf.blockerapp.view.xml.XmlActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val buttonData = arrayListOf(
        "XML",
        "DataBinding"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DefaultTheme {
                MainView(buttonData = buttonData)
            }
        }
    }

    @Composable
    fun MainView(
        buttonData: List<String>
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = buttonData) {
                ButtonView(it) {
                    when (buttonData.indexOf(it)) {
                        0 -> { startActivity(XmlActivity::class.java) }
                        1 -> { startActivity(DataBindingActivity::class.java) }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainView() {
        MainView(buttonData)
    }

    private fun startActivity(activityName : Class<*>) = startActivity(Intent(this, activityName))
}