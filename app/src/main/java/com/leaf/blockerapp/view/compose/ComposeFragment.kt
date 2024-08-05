/*
 * Create by Im-Tae on 2022. 11. 7.
 *
 * Copyright (c) 2022. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leaf.blockerapp.theme.DefaultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeFragment : Fragment() {

    private val viewModel: ComposeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DefaultTheme {
                    ComposeScreen()
                }
            }
        }
    }
}