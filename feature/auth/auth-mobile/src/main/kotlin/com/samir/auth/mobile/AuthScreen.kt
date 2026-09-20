package com.samir.auth.mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.auth.domain.AuthUiState
import com.samir.auth.domain.AuthViewModel
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView

@Composable
fun AuthScreen(
    viewModel: AuthViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        AuthScreenContent(
            state = AuthUiState(isLoading = true),
            onRetry = {}
        )
    } else {
        val actualViewModel: AuthViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        AuthScreenContent(
            state = state,
            onRetry = actualViewModel::login
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenContent(
    state: AuthUiState,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
                actions = {

                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when {
                state.isLoading -> LoadingView(modifier = Modifier.weight(1f))
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage ?: "",
                    modifier = Modifier.weight(1f),
                    onRetry = onRetry,
                )

                else -> {}
            }
        }
    }
}