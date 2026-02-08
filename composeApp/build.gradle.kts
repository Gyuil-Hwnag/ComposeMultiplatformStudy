@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinCocoapods)
}

// NOTE: MapLibre Helper function for Desktop JVM native bindings
fun detectTarget(): String {
    val hostOs = when (val os = System.getProperty("os.name").lowercase()) {
        "mac os x" -> "macos"
        else -> os.split(" ").first()
    }
    val hostArch = when (val arch = System.getProperty("os.arch").lowercase()) {
        "x86_64" -> "amd64"
        "arm64" -> "aarch64"
        else -> arch
    }
    val renderer = when (hostOs) {
        "macos" -> "metal"
        else -> "opengl"
    }
    return "${hostOs}-${hostArch}-${renderer}"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    // NOTE: CocoaPods Configuration for KMP
    cocoapods {
        version = "1.0.0"
        summary = "CmpStudy Compose Multiplatform shared module"
        homepage = "https://github.com/example/cmpstudy"
        ios.deploymentTarget = "14.0"

        pod("MapLibre", "6.17.1")

        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    // NOTE: WasmJS는 Firebase를 지원하지 않으므로 주석 처리
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    // --- Room Configuration ---
    room {
        schemaDirectory("$projectDir/schemas")
    }

    // --- Source Sets ---
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.kotlinx.datetime)

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.jetbrains.compose.navigation)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                api(libs.koin.core)
                implementation(compose.materialIconsExtended)

                implementation(libs.bundles.ktor)
                implementation(libs.bundles.coil)

                implementation(libs.firebase.auth)

                implementation(libs.file.picker)
                implementation(libs.file.picker.coil)

                implementation(libs.permissions)
                implementation(libs.maplibre.compose)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        // --- Intermediate Source Sets ---

        // 1. Non-Web (Supports Room & SQLite)
        // Includes: Android, iOS, JVM
        val nonWebMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.androidx.room.runtime)
                implementation(libs.sqlite.bundled)
                api(libs.compose.webview)
            }
        }

        // 2. Web (In-Memory Storage)
        // Includes: JS, Wasm
        val webMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(npm("@js-joda/timezone", "2.22.0"))
                implementation(libs.ktor.client.js)
            }
        }

        // --- Platform Source Sets ---
        androidMain {
            dependsOn(nonWebMain)
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.ktor.client.okhttp)
            }
        }

        jvmMain {
            dependsOn(nonWebMain)
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
                implementation(libs.ktor.client.okhttp)
                // MapLibre native bindings for Desktop
                runtimeOnly("org.maplibre.compose:maplibre-native-bindings-jni:0.12.1") {
                    capabilities {
                        requireCapability("org.maplibre.compose:maplibre-native-bindings-jni-${detectTarget()}")
                    }
                }
            }
        }

        val iosMain by creating {
            dependsOn(nonWebMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        jsMain {
            dependsOn(webMain)
        }

//        wasmJsMain {
//            dependsOn(webMain)
//        }
    }
}

android {
    namespace = "com.example.cmpstudy"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.cmpstudy"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Update: https://issuetracker.google.com/u/0/issues/342905180
    listOf(
        "kspAndroid",
        "kspJvm",
        "kspIosSimulatorArm64",
        "kspIosArm64"
    ).forEach {
        add(it, libs.androidx.room.compiler)
    }
}

compose.desktop {
    application {
        mainClass = "com.example.cmpstudy.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.cmpstudy"
            packageVersion = "1.0.0"
        }
    }
}

// NOTE: Desktop(Jvm) WebView 기능을 위한 설정 추가.
afterEvaluate {
    tasks.withType<JavaExec> {
        jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
        jvmArgs("--add-opens", "java.desktop/java.awt.peer=ALL-UNNAMED") // recommended but not necessary

        if (System.getProperty("os.name").contains("Mac")) {
            jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
            jvmArgs("--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED")
            jvmArgs("--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED")
        }
    }
}
