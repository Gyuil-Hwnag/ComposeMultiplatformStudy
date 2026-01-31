package com.example.cmpstudy.permission.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen() {

    val cameraPermissionState = rememberPermissionState(Permission.Camera)
    val galleryPermissionState = rememberPermissionState(Permission.Gallery)

    val isGrantedCameraPermission = cameraPermissionState.status.isGranted
    val isGrantedGalleryPermission = galleryPermissionState.status.isGranted

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { cameraPermissionState.launchPermissionRequest() }
        ) {
            Text(
                text = "Request Camera Permission",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (isGrantedCameraPermission) {
            Text(text = "Camera Permission is Granted")
        }

        Button(
            onClick = { galleryPermissionState.launchPermissionRequest() }
        ) {
            Text(
                text = "Request Gallery Permission",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (isGrantedGalleryPermission) {
            Text(text = "Gallery Permission is Granted")
        }
    }
}
