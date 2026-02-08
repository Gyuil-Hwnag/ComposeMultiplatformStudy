package com.example.cmpstudy.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.maplibre.compose.map.MaplibreMap

@Composable
fun MapScreenRoot() {
    MaplibreMap(
        modifier = Modifier.fillMaxSize()
    )
}
