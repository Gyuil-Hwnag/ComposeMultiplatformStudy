package com.example.cmpstudy.file.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.coil.KmpFileFetcher
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch

@Composable
fun MultipleFilePickerScreen(
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current
    var kmpFiles by remember { mutableStateOf(emptyList<KmpFile>()) }
    val imageLoader = ImageLoader
        .Builder(context)
        .components { add(KmpFileFetcher.Factory()) }
        .build()

    val pickerLauncher = rememberFilePickerLauncher(
        type = FilePickerFileType.Image,
        selectionMode = FilePickerSelectionMode.Multiple,
        onResult = { files ->
            scope.launch {
                val photos = mutableListOf<KmpFile>()
                files.forEach { photos.add(it) }
                kmpFiles = photos
            }
        }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().safeContentPadding(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(
                span = { GridItemSpan(2) }
            ) {
                Button(
                    onClick = { pickerLauncher.launch() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Open File Picker")
                }
            }
            items(
                items = kmpFiles,
                itemContent = { kmpFile ->
                    AsyncImage(
                        imageLoader = imageLoader,
                        model = kmpFile,
                        modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
