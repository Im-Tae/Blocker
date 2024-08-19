plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.android.jvm) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.dokka) apply false
}

buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("org.jetbrains.dokka:dokka-base:1.9.20")
    }
    repositories {
        google()
    }
}

repositories {
    google()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}