package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmpstudy.date.DateTimeScreen
import com.example.cmpstudy.location.LocationScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        LocationScreen()
    }
}
