package com.example.cmpstudy.file.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.io.getPath
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.coil.KmpFileFetcher
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch

@Composable
fun SingleFilePickerScreen(
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val calfContext = LocalPlatformContext.current
    val coilContext = coil3.compose.LocalPlatformContext.current

    var byteArray by remember { mutableStateOf(ByteArray(0)) }
    var platformSpecificFile by remember { mutableStateOf<KmpFile?>(null) }
    var platformSpecificFilePath by remember { mutableStateOf("") }

    val pickerLauncher = rememberFilePickerLauncher(
        type = FilePickerFileType.Image,
        selectionMode = FilePickerSelectionMode.Single,
        onResult = { files ->
            scope.launch {
                files.firstOrNull()?.let {
                    byteArray = it.readByteArray(calfContext)
                    platformSpecificFile = it
                    platformSpecificFilePath = it.getPath(calfContext) ?: ""
                }
            }
        }
    )

    val imageLoader = ImageLoader
        .Builder(coilContext)
        .components { add(KmpFileFetcher.Factory()) }
        .build()

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { pickerLauncher.launch() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Open File Picker")
            }
            if (!byteArray.contentEquals(ByteArray(0))) {
                AsyncImage(
                    model = byteArray,
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (platformSpecificFile != null) {
                AsyncImage(
                    imageLoader = imageLoader,
                    model = platformSpecificFile,
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Text(
                text = "Byte Array File Path : $platformSpecificFilePath",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
