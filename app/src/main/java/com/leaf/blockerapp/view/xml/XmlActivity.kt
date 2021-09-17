/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.xml

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.leaf.blocker.extend.setOnDebounceClickListener
import com.leaf.blocker.extend.setOnThrottleFirstListener
import com.leaf.blocker.extend.setOnThrottleLastListener
import com.leaf.blockerapp.BR
import com.leaf.blockerapp.R
import com.leaf.blockerapp.databinding.ActivityXmlBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class XmlActivity : AppCompatActivity() {

    private lateinit var binding : ActivityXmlBinding

    private val viewModel: XmlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_xml)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this

        initListener()
    }

    private fun initListener() {

        binding.apply {

            throttleFirstButton.setOnThrottleFirstListener {
                Timber.i("Button Click (throttle first)")
                showToast("Button Click (throttle first)")
            }

            throttleFirstButton.setOnThrottleFirstListener(listener = {
                Timber.i("Button Click (throttle first)")
                showToast("Button Click (throttle first)")
            })

            throttleLastButton.setOnThrottleLastListener {
                Timber.i("Button Click (throttle last)")
                showToast("Button Click (throttle last)")
            }

            throttleLastButton.setOnThrottleLastListener(listener = {
                Timber.i("Button Click (throttle last)")
                showToast("Button Click (throttle last)")
            })

            debounceButton.setOnDebounceClickListener {
                Timber.i("Button Click (debounce)")
                showToast("Button Click (debounce)")
            }

            debounceButton.setOnDebounceClickListener(listener = {
                Timber.i("Button Click (debounce)")
                showToast("Button Click (debounce)")
            })
        }
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}