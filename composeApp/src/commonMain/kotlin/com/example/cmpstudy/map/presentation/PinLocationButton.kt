package com.example.cmpstudy.map.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.pin_location
import org.jetbrains.compose.resources.painterResource

@Composable
fun PinLocationButton(
    isActive: Boolean,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(Res.drawable.pin_location),
        contentDescription = "PinLocation",
        modifier = Modifier
            .padding(end = 16.dp, bottom = 64.dp)
            .size(40.dp)
            .background(
                color = if (isActive) Color.Red.copy(alpha = 0.5f) else Color.Blue.copy(alpha = 0.3f),
                shape = CircleShape
            )
            .padding(4.dp)
            .clickable(onClick = onClick)
    )
}
