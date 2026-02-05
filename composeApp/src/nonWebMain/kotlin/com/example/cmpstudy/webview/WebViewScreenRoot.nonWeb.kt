package com.example.cmpstudy.webview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmpstudy.web.presentation.WebViewScreen

@Composable
actual fun WebViewScreenRoot() {
    MaterialTheme {
        WebViewScreen()
    }
}
