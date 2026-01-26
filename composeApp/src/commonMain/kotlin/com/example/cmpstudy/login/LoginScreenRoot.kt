package com.example.cmpstudy.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmpstudy.login.data.AuthServiceImpl
import com.example.cmpstudy.login.presentation.LoginScreen
import com.example.cmpstudy.login.presentation.LoginViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

@Composable
fun LoginScreenRoot() {
    val viewModel = LoginViewModel(AuthServiceImpl(Firebase.auth))
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
    ) {
        LoginScreen(onLoginSuccess = {}, viewModel = viewModel)
    }
}
