package com.example.cmpstudy.webview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.browser.window

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun WebViewScreenRoot() {
    val url = "https://github.com"
    LaunchedEffect(Unit) {
        window.location.href = url
    }
}
