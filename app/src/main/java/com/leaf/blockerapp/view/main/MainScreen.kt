/*
 * Create by Im-Tae on 2024. 8. 5.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leaf.blockerapp.theme.ButtonView

@Composable
fun MainView(
    buttons: List<Button>,
    onClickButton: (Button) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(
            items = buttons,
            key = { index, _ -> "$index" },
            contentType = { _, item -> item::class.java.name },
        ) { _, item ->
            ButtonView(
                item.name,
                onClick = { onClickButton(item) }
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainView(
        buttons = Button.values().toList(),
        onClickButton = {}
    )
}