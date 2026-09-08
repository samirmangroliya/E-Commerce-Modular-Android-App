package com.samir.order.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samir.common.network.NetworkResult
import com.samir.domain.GetProductsUseCase
import com.samir.model.Product
import com.samir.domain.ProductSortOrder
import com.samir.domain.SearchProductsUseCase
import com.samir.domain.SortProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/**
 * Shared by BOTH home-mobile and home-tv. Neither Compose UI knows about
 * GetProductsUseCase, ProductRepository, or dummyjson — they only ever see this class.
 */
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val sortProductsUseCase: SortProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUIState(isLoading = true))
    val uiState: StateFlow<OrderUIState> = _uiState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            when (val result = getProductsUseCase()) {
                is NetworkResult.Success -> {
                    delay(2000.milliseconds)
                    allProducts = result.data
                    applySort()
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
                is NetworkResult.Error -> _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = result.message,
                )
                NetworkResult.Loading -> Unit
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)

        if (query.isBlank()) {
            loadProducts()
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            when (val result = searchProductsUseCase(query)) {
                is NetworkResult.Success -> {
                    allProducts = result.data
                    applySort()
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
                is NetworkResult.Error -> _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = result.message,
                )
                NetworkResult.Loading -> Unit
            }
        }
    }

    fun onSortOrderChanged(order: ProductSortOrder) {
        _uiState.value = _uiState.value.copy(sortOrder = order)
        applySort()
    }

    private fun applySort() {
        val sorted = sortProductsUseCase(allProducts, _uiState.value.sortOrder)
        _uiState.value = _uiState.value.copy(products = sorted)
    }
}