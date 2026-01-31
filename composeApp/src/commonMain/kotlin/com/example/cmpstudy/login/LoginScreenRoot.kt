package com.example.cmpstudy.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmpstudy.core.presentation.koinViewModel
import com.example.cmpstudy.login.presentation.LoginScreen
import com.example.cmpstudy.login.presentation.LoginViewModel

@Composable
fun LoginScreenRoot() {
    val viewModel = koinViewModel<LoginViewModel>()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        LoginScreen(onLoginSuccess = {}, viewModel = viewModel)
    }
}
