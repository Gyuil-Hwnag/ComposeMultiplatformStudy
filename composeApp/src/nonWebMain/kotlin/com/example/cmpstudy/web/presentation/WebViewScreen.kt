package com.example.cmpstudy.web.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.multiplatform.webview.util.KLogSeverity
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen() {

    val url = "https://github.com"
    val state = rememberWebViewState(url)
    val navigator = rememberWebViewNavigator()
    val loadingState = state.loadingState

    LaunchedEffect(Unit) {
        state.webSettings.apply {
            logSeverity = KLogSeverity.Debug
            customUserAgentString = "Mozilla/5.0"
        }
    }

    Column {
        TopAppBar(
            title = { Text("WebView Example") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (navigator.canGoBack) {
                            navigator.navigateBack()
                        }
                    }
                ) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = { loadingState.progress },
                modifier = Modifier.fillMaxWidth()
            )
        }
        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            navigator = navigator
        )
    }
}
