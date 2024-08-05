/*
 * Create by Im-Tae on 2024. 8. 5.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.imtae.buildsrc

object Configuration {
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 21
    const val majorVersion = 1
    const val minorVersion = 1
    const val patchVersion = 0
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val versionCode = 1
    const val snapshotVersionName = "$majorVersion.$minorVersion.${patchVersion + 1}-SNAPSHOT"
    const val artifactGroup = "io.github.im-tae"
}