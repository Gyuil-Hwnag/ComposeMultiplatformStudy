package com.example.cmpstudy

import android.app.Application
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.cmpstudy.bookpedia.di.initKoin
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

fun main() {
    // NOTE: Firebase 초기화 (JVM용 Platform 설정)
    FirebasePlatform.initializeFirebasePlatform(
        object : FirebasePlatform() {
            private val storage = mutableMapOf<String, String>()
            override fun store(key: String, value: String) = storage.set(key, value)
            override fun retrieve(key: String) = storage[key]
            override fun clear(key: String) { storage.remove(key) }
            override fun log(msg: String) = println(msg)
        }
    )

    // NOTE: Firebase 초기화 (Web 설정 재사용, Mock Context 사용)
    Firebase.initialize(
        context = Application(),
        options = FirebaseConfig.getOptions()
    )

    // NOTE: Koin injection
    initKoin()

    application {
        // Size: 윈도우 앱의 사이즈
        // Position: 윈도루 앱의 위치
        val state = rememberWindowState(
            size = DpSize(1200.dp, 800.dp),
            position = WindowPosition(100.dp, 100.dp)
        )
        Window(
            title = "BookPedia",
            onCloseRequest = ::exitApplication,
            state = state,
            alwaysOnTop = true
        ) {
            WebViewSettings()
        }
    }
}
