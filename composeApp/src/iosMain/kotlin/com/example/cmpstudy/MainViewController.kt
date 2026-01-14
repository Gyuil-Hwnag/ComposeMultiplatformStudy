package com.example.cmpstudy

import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmpstudy.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
