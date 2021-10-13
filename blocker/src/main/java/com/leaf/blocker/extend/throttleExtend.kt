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

fun <T> throttleFirst(
    skipInterval: Long,
    coroutineScope: CoroutineScope,
    callback: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    return { param: T ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                callback(param)
                delay(skipInterval)
            }
        }
    }
}

fun <T, G> throttleFirst(
    skipInterval: Long,
    coroutineScope: CoroutineScope,
    callback: (T, G) -> Unit
): (T, G) -> Unit {
    var throttleJob: Job? = null
    return { param1: T, param2: G ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                callback(param1, param2)
                delay(skipInterval)
            }
        }
    }
}

fun <T> throttleLatest(
    skipInterval: Long,
    coroutineScope: CoroutineScope,
    callback: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var lastParam: T
    return { param: T ->
        lastParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                delay(skipInterval)
                lastParam.let(callback)
            }
        }
    }
}

fun <T, G> throttleLatest(
    skipInterval: Long,
    coroutineScope: CoroutineScope,
    callback: (T, G) -> Unit
): (T, G) -> Unit {
    var throttleJob: Job? = null
    var lastParam1: T
    var lastParam2: G
    return { param1: T, param2: G ->
        lastParam1 = param1
        lastParam2 = param2
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                delay(skipInterval)
                callback(lastParam1, lastParam2)
            }
        }
    }
}