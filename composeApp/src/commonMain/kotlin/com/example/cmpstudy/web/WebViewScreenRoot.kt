package com.example.cmpstudy.web

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmpstudy.web.presentation.WebViewScreen

@Composable
fun WebViewScreenRoot() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        WebViewScreen()
    }
}
