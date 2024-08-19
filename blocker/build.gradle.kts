import com.leaf.buildsrc.Configuration
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration

val libVersion = "1.0.0"

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.vanniktech.maven.publish") version "0.29.0"
    id("org.jetbrains.dokka")
}

rootProject.extra.apply {
    val snapshot = System.getenv("SNAPSHOT").toBoolean()
    val libVersion =
        if (snapshot) {
            Configuration.snapshotVersionName
        } else {
            Configuration.versionName
        }
    set("libVersion", libVersion)
}
val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

mavenPublishing {
    val artifactId = "blocker"
    coordinates(
        Configuration.artifactGroup,
        artifactId,
        rootProject.extra.get("libVersion").toString()
    )
    pom {
        name.set(artifactId)
        description.set("Set a delay on Android listener")
    }
}

android {
    compileSdk = Configuration.compileSdk
    namespace = "com.leaf.blocker"

    defaultConfig {
        minSdk = Configuration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "META-INF/proguard/androidx-annotations.pro"
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    kotlin {
        jvmToolchain(17)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configuration.kotlinCompilerExtensionVersion
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(file("../dokka/html"))
    moduleName.set(project.name)
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage =
            """© 2024 by <a href="https://github.com/Im-Tae">@Im-Tae</a>"""
        separateInheritedMembers = true
    }
    dokkaSourceSets {
        configureEach {
            jdkVersion.set(17)
            skipDeprecated.set(true)
            includeNonPublic.set(false)
            skipEmptyPackages.set(true)
            reportUndocumented.set(false)
            perPackageOption {
                matchingRegex.set(".*\\.internal.*")
                suppress.set(true)
            }
        }
    }
}

afterEvaluate {
    tasks["dokkaHtml"].dependsOn(tasks.getByName("kaptReleaseKotlin"), tasks.getByName("kaptDebugKotlin"))
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Material
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Hilt
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    // Compose
    implementation(libs.compose.material3.android)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.androidx.compose.ui.android)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.activity.compose)
}