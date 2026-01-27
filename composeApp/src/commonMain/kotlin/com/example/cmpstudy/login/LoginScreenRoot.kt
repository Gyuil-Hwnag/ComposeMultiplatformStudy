package com.example.cmpstudy.login

import androidx.compose.foundation.layout.fillMaxWidth
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
        modifier = Modifier.fillMaxWidth(),
    ) {
        LoginScreen(onLoginSuccess = {}, viewModel = viewModel)
    }
}
