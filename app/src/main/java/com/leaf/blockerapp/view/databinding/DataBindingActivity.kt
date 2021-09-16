/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blockerapp.view.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leaf.blockerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
    }
}