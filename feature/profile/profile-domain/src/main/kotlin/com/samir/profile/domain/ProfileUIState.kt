package com.samir.profile.domain

data class ProfileUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)