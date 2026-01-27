package com.example.cmpstudy.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmpstudy.login.data.AuthService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Get CurrentUser failed") }
        }) {
            authService.currentUser.collect { user ->
                _uiState.update { it.copy(currentUser = user) }
            }
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null) }
    }

    fun onSignInClick() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (email.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Email cannot be blank") }
            return
        }

        if (password.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Password cannot be blank") }
            return
        }

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Authentication failed") }
        }) {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            authService.createUser(email = email, password = password)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onSignOut() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _uiState.update { it.copy(errorMessage = e.message ?: "Sign out failed") }
        }) {
            authService.signOut()
        }
    }
}
