package com.example.cmpstudy

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.HttpClient

// NOTE: 웹은 Debug 빌드하면 안되고 Run 빌드 해야함.
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App(engine = remember { HttpClient().engine })
    }
}
