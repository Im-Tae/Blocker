/*
 * Create by Im-Tae on 2021. 9. 16.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.view

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import com.leaf.blocker.Blocker
import com.leaf.blocker.extend.debounce
import com.leaf.blocker.extend.throttleFirst
import com.leaf.blocker.extend.throttleLatest

/**
 * Register a callback to be invoked when this view is clicked. If this view is not clickable, it becomes clickable.
 *
 * This callback emit the most recent items emitted by an Observable within periodic time intervals.
 *
 * interval 기준으로 가장 처음에 들어온 요청만 처리합니다.
 *
 * Example of use:
 * ```
 * (view).setOnThrottleFirstListener {
 *     // code
 * }
 * ```
 *
 * @param lifecycle View Lifecycle
 * @param skipInterval skip interval; default interval is 2000Ms
 * @param listener OnClick Callback
 * */
fun View.setOnThrottleFirstListener(
    lifecycle: Lifecycle,
    skipInterval: Long = Blocker.getInterval(),
    listener: View.OnClickListener?
) {
    if (listener != null) {
        val onThrottleClick: (view: View) -> Unit =
            throttleFirst(skipInterval = skipInterval, coroutineScope = lifecycle.coroutineScope) { view: View ->
                listener.onClick(view)
            }
        setOnClickListener(onThrottleClick)
    }
}

/**
 * Register a callback to be invoked when this view is clicked. If this view is not clickable, it becomes clickable.
 *
 * This callback emit the last items emitted by an Observable within periodic time intervals.
 *
 * interval 기준으로 가장 마지막에 들어온 요청만 처리합니다.
 *
 * Example of use:
 * ```
 * (view).setOnThrottleLastListener {
 *     // code
 * }
 * ```
 * @param lifecycle View Lifecycle
 * @param skipInterval skip interval; default interval is 2000Ms
 * @param listener OnClick Callback
 * */
fun View.setOnThrottleLastListener(
    lifecycle: Lifecycle,
    skipInterval: Long = Blocker.getInterval(),
    listener: View.OnClickListener?
) {
    if (listener != null) {
        val onThrottleClick: (view: View) -> Unit =
            throttleLatest(skipInterval = skipInterval, coroutineScope = lifecycle.coroutineScope) { view: View ->
                listener.onClick(view)
            }
        setOnClickListener(onThrottleClick)
    }
}

/**
 * Register a callback to be invoked when this view is clicked. If this view is not clickable, it becomes clickable.
 *
 * This callback only emit an item from an Observable if a particular timespan has passed without it emitting another item.
 *
 * 첫 요청 이후 interval 기준으로 요청이 들어오지 않으면 처리합니다.
 *
 * Example of use:
 * ```
 * (view).setOnDebounceClickListener {
 *     // code
 * }
 * ```
 * @param lifecycle View Lifecycle
 * @param waitInterval wait interval; default interval is 2000Ms
 * @param listener OnClick Callback
 * */
fun View.setOnDebounceClickListener(
    lifecycle: Lifecycle,
    waitInterval: Long = Blocker.getInterval(),
    listener: View.OnClickListener?
) {
    if (listener != null) {
        val onDebounceClick: (view: View) -> Unit =
            debounce(waitInterval = waitInterval, coroutineScope = lifecycle.coroutineScope) { view: View ->
                listener.onClick(view)
            }
        setOnClickListener(onDebounceClick)
    }
}