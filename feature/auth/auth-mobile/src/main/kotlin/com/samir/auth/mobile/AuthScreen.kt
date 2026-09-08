package com.samir.auth.mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.auth.domain.AuthUiState
import com.samir.auth.domain.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        AuthScreenContent(
            state = AuthUiState(isLoading = true)
        )
    } else {
        val actualViewModel: AuthViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        AuthScreenContent(
            state = state
        )
    }
}

@Composable
fun AuthScreenContent(state: AuthUiState) {
    TODO("Not yet implemented")
}
