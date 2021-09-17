/*
 * Create by Im-Tae on 2021. 9. 17.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.compose

import android.R
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonView(
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    ) {

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.holo_red_light)),
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