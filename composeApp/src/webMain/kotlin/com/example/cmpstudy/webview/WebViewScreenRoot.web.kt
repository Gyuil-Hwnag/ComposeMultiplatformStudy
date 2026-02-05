package com.example.cmpstudy.webview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
actual fun WebViewScreenRoot() {
    MaterialTheme {
        Text("WebView is not supported on Web platform")
    }
}
