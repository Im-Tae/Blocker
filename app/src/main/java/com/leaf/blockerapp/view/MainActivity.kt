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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leaf.blockerapp.view.compose.ComposeActivity
import com.leaf.blockerapp.view.databinding.DataBindingActivity
import com.leaf.blockerapp.view.xml.XmlActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val buttonData = arrayListOf(
        "Compose",
        "XML",
        "DataBinding"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ButtonListView(buttonData = buttonData)
        }
    }

    @Composable
    fun ButtonListView(
        buttonData: List<String>
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = buttonData) {
                ButtonView(buttonData.indexOf(it), it)
            }
        }
    }

    @Composable
    fun ButtonView(
        index: Int,
        title: String
    ) {
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        ) {

            Button(
                onClick = {
                    when (index) {
                        0 -> { startActivity(ComposeActivity::class.java) }
                        1-> { startActivity(XmlActivity::class.java) }
                        2 -> { startActivity(DataBindingActivity::class.java) }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = android.R.color.holo_red_light)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewButtonListView() {
        ButtonListView(buttonData)
    }

    private fun startActivity(activityName : Class<*>) = startActivity(Intent(this, activityName))
}