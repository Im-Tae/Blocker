/*
 * Create by Im-Tae on 2024. 8. 14.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.binding

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.leaf.blocker.view.setOnDebounceClickListener
import com.leaf.blocker.view.setOnThrottleFirstListener
import com.leaf.blocker.view.setOnThrottleLastListener

@BindingAdapter(value = ["onThrottleFirstClick", "skipInterval"], requireAll = false)
fun View.onThrottleFirstClick(listener: View.OnClickListener, skipInterval: Long?) {
    val lifecycle = findViewTreeLifecycleOwner()?.lifecycle ?: run {
        Log.e("${this::class.java.simpleName} >> onThrottleFirstClick", "Cannot find ViewLifecycle. ViewLifecycle is null")
        return
    }

    if (skipInterval != null) {
        setOnThrottleFirstListener(
            lifecycle = lifecycle,
            skipInterval = skipInterval,
            listener = listener
        )
    }
    else {
        setOnThrottleFirstListener(
            lifecycle = lifecycle,
            listener = listener
        )
    }
}

@BindingAdapter(value = ["onThrottleLastClick", "skipInterval"], requireAll = false)
fun View.onThrottleLastClick(listener: View.OnClickListener, skipInterval: Long?) {
    val lifecycle = findViewTreeLifecycleOwner()?.lifecycle ?: run {
        Log.e("${this::class.java.simpleName} >> onThrottleLastClick", "Cannot find ViewLifecycle. ViewLifecycle is null")
        return
    }

    if (skipInterval != null) {
        setOnThrottleLastListener(
            lifecycle = lifecycle,
            skipInterval = skipInterval,
            listener = listener
        )
    }
    else {
        setOnThrottleLastListener(
            lifecycle = lifecycle,
            listener = listener
        )
    }
}

@BindingAdapter(value = ["onDebounceClick", "waitInterval"], requireAll = false)
fun View.onDebounceClick(listener: View.OnClickListener, waitInterval: Long?) {
    val lifecycle = findViewTreeLifecycleOwner()?.lifecycle ?: run {
        Log.e("${this::class.java.simpleName} >> onDebounceClick", "Cannot find ViewLifecycle. ViewLifecycle is null")
        return
    }

    if (waitInterval != null) {
        setOnDebounceClickListener(
            lifecycle = lifecycle,
            waitInterval = waitInterval,
            listener = listener
        )
    }
    else {
        setOnDebounceClickListener(
            lifecycle = lifecycle,
            listener = listener
        )
    }
}