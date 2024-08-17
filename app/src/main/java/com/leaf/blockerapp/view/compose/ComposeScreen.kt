/*
 * Create by Im-Tae on 2024. 8. 5.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leaf.blockerapp.view.compose.ui.ButtonContent
import com.leaf.blockerapp.view.compose.ui.CheckBoxContent
import com.leaf.blockerapp.view.compose.ui.RadioButtonContent

@Composable
fun ComposeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonContent()
        RadioButtonContent()
        CheckBoxContent()
    }
}

@Preview
@Composable
private fun ComposeViewPreview() {
    ComposeScreen()
}