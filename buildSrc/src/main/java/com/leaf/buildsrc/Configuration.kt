/*
 * Create by Im-Tae on 2024. 8. 13.
 *
 * Copyright (c) 2024. Im-Tae. All rights reserved.
 */

package com.leaf.buildsrc

object Configuration {
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 21
    const val majorVersion = 1
    const val minorVersion = 2
    const val patchVersion = 0
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val versionCode = 2
    const val snapshotVersionName = "$majorVersion.$minorVersion.${patchVersion + 1}-SNAPSHOT"
    const val kotlinCompilerExtensionVersion = "1.5.13"
    const val artifactGroup = "io.github.im-tae"
}