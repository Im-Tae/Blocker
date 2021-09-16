/*
 * Create by Im-Tae on 2021. 9. 15.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blocker.extend

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    waitInterval: Long = 2000L,
    coroutineScope: CoroutineScope,
    callback: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitInterval)
            callback(param)
        }
    }
}

fun <T, G> debounce(
    waitInterval: Long = 2000L,
    coroutineScope: CoroutineScope,
    callback: (T, G) -> Unit
): (T, G) -> Unit {
    var debounceJob: Job? = null
    return { param1: T, param2: G ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitInterval)
            callback(param1, param2)
        }
    }
}
