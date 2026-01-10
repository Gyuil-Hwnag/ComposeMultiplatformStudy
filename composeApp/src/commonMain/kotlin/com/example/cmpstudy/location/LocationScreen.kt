package com.example.cmpstudy.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cmpstudy.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LocationScreen() {
    var timeAtLocation by remember { mutableStateOf("No location selected!") }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = timeAtLocation,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        )
        Button(
            onClick = { timeAtLocation = "13:30" }
        ) {
            Text("Show Time At Location")
        }
    }
}
