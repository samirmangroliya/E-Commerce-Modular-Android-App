package com.samir.profile.mobile

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
import com.samir.profile.domain.ProfileUIState
import com.samir.profile.domain.ProfileViewModel

@Composable
fun ProfileScreen(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    viewModel: ProfileViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        ProfileScreenContent(
            state = ProfileUIState(isLoading = true)
        )
    } else {
        val actualViewModel: ProfileViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        ProfileScreenContent(
            state = state
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    state: ProfileUIState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("E-Shop") },
            )
        },
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {

        }
    }
}