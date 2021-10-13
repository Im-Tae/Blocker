/*
 * Create by Im-Tae on 2021. 9. 15.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.leaf.blocker

class Blocker {

    companion object {

        private var DEFAULT_INTERVAL = 2000L

        fun getInterval() = DEFAULT_INTERVAL

        fun setInterval(interval: Long) {
            DEFAULT_INTERVAL = interval
        }
    }
}