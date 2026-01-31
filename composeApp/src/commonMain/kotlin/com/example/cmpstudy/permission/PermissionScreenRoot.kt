package com.example.cmpstudy.permission

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmpstudy.core.presentation.koinViewModel
import com.example.cmpstudy.permission.presentation.PermissionScreen
import com.example.cmpstudy.permission.presentation.PermissionViewModel

@Composable
fun PermissionScreenRoot() {

    val viewModel = koinViewModel<PermissionViewModel>()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {
        PermissionScreen(viewModel)
    }
}
