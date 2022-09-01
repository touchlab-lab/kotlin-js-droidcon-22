import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    // Uncomment this for just serialization and full example
    kotlin("plugin.serialization") version "1.7.10"
}

version = "0.1.0"

kotlin {
    android()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    js(IR) {
        moduleName = "kmp-lib"
        browser {
            webpackTask {
                outputFileName = "kmp_lib.js"
                output.library = "kmpLib"
            }
        }
        binaries.executable()
        nodejs()
        binaries.library()
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:2.1.0")
                // Uncomment this for just coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                // Uncomment this for just datetime and full example
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                // Uncomment this for just serialization and full example
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}
