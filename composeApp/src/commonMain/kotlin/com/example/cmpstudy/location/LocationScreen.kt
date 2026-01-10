package com.example.cmpstudy.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cmpstudy.White
import com.example.cmpstudy.utils.getCurrentTimeAt
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LocationScreen() {
    var showCountries by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf<String>("South Korea") }
    var timeAtLocation by remember { mutableStateOf<String>("No location selected!") }
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
        TextField(
            value = location,
            onValueChange = { location = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        )
        Button(
            onClick = {
                val country = COUNTRIES.find { it.name == location }
                timeAtLocation = if (country != null) {
                    getCurrentTimeAt(country.name, country.zone)
                } else {
                    "Invalid Location"
                }
            }
        ) {
            Text("Show Time At Location")
        }
        Button(
            onClick = { showCountries = !showCountries },
            modifier = Modifier.padding(all = 10.dp)
        ) {
            Text("Show Countries")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        ) {
            DropdownMenu(
                expanded = showCountries,
                onDismissRequest = { showCountries = false }
            ) {
                COUNTRIES.forEach { (name, zone, image) ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(image),
                                    contentDescription = "$name image",
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(end = 10.dp),
                                )
                                Text(
                                    text = name,
                                    fontSize = 14.sp,
                                )
                            }
                        },
                        onClick = {
                            timeAtLocation = getCurrentTimeAt(name, zone)
                            location = name
                            showCountries = false
                        }
                    )
                }
            }
        }
    }
}
