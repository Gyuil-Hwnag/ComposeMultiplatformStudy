package com.example.cmpstudy.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmpstudy.login.data.AuthService
import com.example.cmpstudy.login.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _emailError = MutableStateFlow(false)
    val emailError = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow(false)
    val passwordError = _passwordError.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing = _isProcessing.asStateFlow()

    val isButtonEnabled: StateFlow<Boolean> = uiState
        .map { it.email.isNotBlank() && it.password.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init {
        viewModelScope.launch {
            authService.currentUser.collect {
                _currentUser.value = it
            }
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
        if (value.isNotBlank()) _emailError.value = true
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
        if (value.isNotBlank()) _passwordError.value = true
    }

    fun onSignInClick() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (email.isEmpty()) {
            _emailError.value = true
            return
        }

        if (password.isEmpty()) {
            _passwordError.value = true
            return
        }

        viewModelScope.launch {
            _isProcessing.value = true
            authService.createUser(
                email = email,
                password = password
            )
            _isProcessing.value = false
        }
    }

    fun onSignOut() {
        viewModelScope.launch {
            authService.signOut()
        }
    }
}
