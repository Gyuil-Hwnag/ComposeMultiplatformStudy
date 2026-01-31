package com.example.cmpstudy.permission

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmpstudy.permission.presentation.PermissionScreen

@Composable
fun PermissionScreenRoot() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        PermissionScreen()
    }
}
