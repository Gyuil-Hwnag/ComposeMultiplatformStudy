package com.example.cmpstudy.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cmpstudy.composeapp.generated.resources.*
import com.example.cmpstudy.app.AppRoutes
import org.jetbrains.compose.resources.stringResource

@Composable
fun StartScreen(
    onClick: (AppRoutes) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DEFAULT_ROUTES.forEach { route ->
                StartButton(
                    target = route,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun StartButton(
    target: AppRoutes,
    onClick: (AppRoutes) -> Unit
) {
    val labelResId = when (target) {
        AppRoutes.BookPedia -> Res.string.book_pedia
        AppRoutes.DateTime -> Res.string.date_time
        AppRoutes.FilePicker -> Res.string.file_picker
        AppRoutes.Location -> Res.string.location
        AppRoutes.Login -> Res.string.login
        AppRoutes.Map -> Res.string.map
        AppRoutes.Permission -> Res.string.permission
        AppRoutes.WebView -> Res.string.webview
        else -> Res.string.start
    }
    Button(
        onClick = { onClick(target) }
    ) {
        Text(text = stringResource(labelResId))
    }
}

private val DEFAULT_ROUTES = listOf<AppRoutes>(
    AppRoutes.BookPedia,
    AppRoutes.DateTime,
    AppRoutes.FilePicker,
    AppRoutes.Location,
    AppRoutes.Login,
    AppRoutes.Map,
    AppRoutes.Permission,
    AppRoutes.WebView,
)
