plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"

            export("com.arkivanov.decompose:decompose:2.0.1")
            export("com.arkivanov.essenty:lifecycle:1.2.0-alpha-06")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.arkivanov.decompose:decompose:2.0.1")
                api("com.arkivanov.essenty:lifecycle:1.2.0-alpha-06")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "uz.javokhir.decompose.sample"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
}