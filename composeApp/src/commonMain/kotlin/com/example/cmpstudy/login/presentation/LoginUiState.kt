package com.example.cmpstudy.login.presentation

import com.example.cmpstudy.login.domain.User

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val currentUser: User? = null,
    val errorMessage: String? = null
) {
    val isButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading

    val hasError: Boolean
        get() = errorMessage != null

    val isLoggedIn: Boolean
        get() = currentUser != null && !currentUser.isAnonymous
}
