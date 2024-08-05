/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leaf.blocker.extend.setOnDebounceClickListener
import com.leaf.blocker.extend.setOnThrottleFirstListener
import com.leaf.blocker.extend.setOnThrottleLastListener
import com.leaf.blockerapp.BR
import com.leaf.blockerapp.databinding.FragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ViewFragment : Fragment() {

    private lateinit var binding : FragmentViewBinding

    private val viewModel: ViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        binding.apply {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@ViewFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {

        binding.apply {

            throttleFirstButton.setOnThrottleFirstListener {
                Timber.tag("[View] ").i("Button Click (throttle first)")
                showToast("Button Click (throttle first)")
            }

            throttleLastButton.setOnThrottleLastListener {
                Timber.tag("[View] ").i("Button Click (throttle last)")
                showToast("Button Click (throttle last)")
            }

            debounceButton.setOnDebounceClickListener {
                Timber.tag("[View] ").i("Button Click (debounce)")
                showToast("Button Click (debounce)")
            }
        }
    }

    private fun showToast(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}