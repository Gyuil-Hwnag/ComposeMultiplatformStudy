package com.example.cmpstudy

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.cmpstudy.bookpedia.di.initKoin

fun main() {
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
            App()
        }
    }

}
