package com.example.cmpstudy.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.cmpstudy.login.domain.User

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {},
        isProcessing = false,
        currentUser = User(),
        isError = false,
        onSignOut = {}
    )
}
