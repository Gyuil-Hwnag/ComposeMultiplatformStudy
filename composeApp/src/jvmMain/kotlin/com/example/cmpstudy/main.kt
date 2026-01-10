package com.example.cmpstudy

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    // Size: 윈도우 앱의 사이즈
    // Position: 윈도루 앱의 위치
    val state = rememberWindowState(
        size = DpSize(800.dp, 500.dp),
        position = WindowPosition(300.dp, 300.dp)
    )
    Window(
        title = "Local Time App",
        onCloseRequest = ::exitApplication,
        state = state,
        alwaysOnTop = true
    ) {
        App()
    }
}
