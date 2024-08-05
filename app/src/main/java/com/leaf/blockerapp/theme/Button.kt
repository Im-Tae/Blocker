/*
 * Create by Im-Tae on 2021. 9. 17.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leaf.blockerapp.R

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
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_200)),
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