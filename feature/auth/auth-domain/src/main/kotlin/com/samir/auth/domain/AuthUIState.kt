package com.samir.auth.domain

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)