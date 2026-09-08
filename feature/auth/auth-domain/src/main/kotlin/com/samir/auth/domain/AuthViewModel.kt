package com.samir.auth.domain

import androidx.lifecycle.ViewModel
import com.samir.domain.GetProductsUseCase
import com.samir.domain.SearchProductsUseCase
import com.samir.domain.SortProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Shared by BOTH home-mobile and home-tv. Neither Compose UI knows about
 * GetProductsUseCase, ProductRepository, or dummyjson — they only ever see this class.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val sortProductsUseCase: SortProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState(isLoading = true))
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

}