package com.example.cmpstudy.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmpstudy.login.presentation.LoginScreen
import com.example.cmpstudy.login.presentation.LoginViewModel

@Composable
fun LoginScreenRoot() {
    val viewModel = viewModel<LoginViewModel>()
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
    ) {
        LoginScreen(onLoginSuccess = {}, viewModel = viewModel)
    }
}
