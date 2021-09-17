/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.databinding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.leaf.blockerapp.BR
import com.leaf.blockerapp.R
import com.leaf.blockerapp.databinding.ActivityDataBindingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DataBindingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDataBindingBinding

    private val viewModel: DataBindingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.showToastEvent.collect {
                showToast(it)
            }
        }
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}