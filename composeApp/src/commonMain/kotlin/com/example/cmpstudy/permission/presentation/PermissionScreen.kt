package com.example.cmpstudy.permission.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState

// NOTE: Permission 관련 설정 참고 (https://mohamedrejeb.github.io/Calf/permissions/)
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
            Text(
                text = "Camera Permission is Granted",
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            Text(
                text = "Gallery Permission is Granted",
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
