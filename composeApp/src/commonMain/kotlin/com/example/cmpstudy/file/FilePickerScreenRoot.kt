package com.example.cmpstudy.file

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmpstudy.file.presentation.MultipleFilePickerScreen
import com.example.cmpstudy.file.presentation.SingleFilePickerScreen

@Composable
fun FilePickerScreenRoot() {
    var showSingleFilePicker by remember { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { showSingleFilePicker = !showSingleFilePicker },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = if (showSingleFilePicker) "Single File Picker" else "Multi File Picker")
            }
            if (showSingleFilePicker) {
                SingleFilePickerScreen(modifier = Modifier.fillMaxWidth().weight(1f))
            } else {
                MultipleFilePickerScreen(modifier = Modifier.fillMaxWidth().weight(1f))
            }
        }
    }
}
