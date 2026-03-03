package com.example.twitterclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterclone.models.User
import com.example.twitterclone.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val currentUser: User? = null,
    val error: String? = null,
    val message: String? = null
)

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    
    private val _authState = MutableStateFlow(AuthUiState())
    val authState: StateFlow<AuthUiState> = _authState
    
    init {
        checkUserLoggedIn()
    }
    
    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = authRepository.isUserLoggedIn()
            if (isLoggedIn) {
                val result = authRepository.getCurrentUser()
                result.onSuccess { user ->
                    _authState.value = AuthUiState(
                        isLoggedIn = true,
                        currentUser = user
                    )
                }.onFailure { error ->
                    _authState.value = AuthUiState(
                        error = error.message ?: "Error loading user"
                    )
                }
            }
        }
    }
    
    fun register(email: String, password: String, username: String, displayName: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            val result = authRepository.register(email, password, username, displayName)
            result.onSuccess { user ->
                _authState.value = AuthUiState(
                    isLoading = false,
                    isLoggedIn = true,
                    currentUser = user,
                    message = "Registration successful!"
                )
            }.onFailure { error ->
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Registration failed"
                )
            }
        }
    }
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            val result = authRepository.login(email, password)
            result.onSuccess {
                // Obtener el usuario actual
                val userResult = authRepository.getCurrentUser()
                userResult.onSuccess { user ->
                    _authState.value = AuthUiState(
                        isLoading = false,
                        isLoggedIn = true,
                        currentUser = user,
                        message = "Login successful!"
                    )
                }.onFailure { error ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load user"
                    )
                }
            }.onFailure { error ->
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Login failed"
                )
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            
            val result = authRepository.logout()
            result.onSuccess {
                _authState.value = AuthUiState()
            }.onFailure { error ->
                _authState.value = _authState.value.copy(
                    error = error.message ?: "Logout failed"
                )
            }
        }
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
    
    fun clearMessage() {
        _authState.value = _authState.value.copy(message = null)
    }
}
