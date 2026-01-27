package com.example.cmpstudy

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.cmpstudy.bookpedia.di.initKoin
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

// NOTE: 웹은 Debug 빌드하면 안되고 Run 빌드 해야함.
@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    Firebase.initialize(
        options = FirebaseConfig.getOptions()
    )

    initKoin()
    ComposeViewport {
        App()
    }
}
