package com.example.cmpstudy.map.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.pin_marker
import org.jetbrains.compose.resources.painterResource

@Composable
fun PinMarker(modifier: Modifier) {
    Image(
        painter = painterResource(Res.drawable.pin_marker),
        contentDescription = "PinMarker",
        modifier = modifier
            .offset(y = (-24).dp)
            .size(48.dp)
    )
}
