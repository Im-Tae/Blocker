/*
 * Create by Im-Tae on 2024. 8. 5.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.leaf.blockerapp.R
import com.leaf.blockerapp.theme.DefaultTheme

enum class Button(name: String) {
    View(name = "View"),
    DataBinding(name = "DataBinding"),
    Compose(name = "Compose"),
}

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DefaultTheme {
                    MainView(
                        buttons = Button.values().toList(),
                        onClickButton = ::requestOnClickButton
                    )
                }
            }
        }
    }

    private fun requestOnClickButton(button: Button) {
        when (button) {
            Button.View -> {
                findNavController().navigate(R.id.action_viewFragment)
            }
            Button.DataBinding -> {
                findNavController().navigate(R.id.action_dataBindingFragment)
            }
            Button.Compose -> {
                findNavController().navigate(R.id.action_composeFragment)
            }
        }
    }
}