/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.extend

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["onThrottleFirstClick", "skipInterval"], requireAll = false)
fun View.onThrottleFirstClick(listener: View.OnClickListener, skipInterval: Long?) {

    if (skipInterval != null) setOnThrottleFirstListener(skipInterval, listener)
    else setOnThrottleFirstListener(listener = listener)
}

@BindingAdapter(value = ["onThrottleLastClick", "skipInterval"], requireAll = false)
fun View.onThrottleLastClick(listener: View.OnClickListener, skipInterval: Long?) {

    if (skipInterval != null) setOnThrottleLastListener(skipInterval, listener)
    else setOnThrottleLastListener(listener = listener)
}

@BindingAdapter(value = ["onDebounceClick", "waitInterval"], requireAll = false)
fun View.onDebounceClick(listener: View.OnClickListener, waitInterval: Long?) {

    if (waitInterval != null) setOnDebounceClickListener(waitInterval, listener)
    else setOnDebounceClickListener(listener = listener)
}