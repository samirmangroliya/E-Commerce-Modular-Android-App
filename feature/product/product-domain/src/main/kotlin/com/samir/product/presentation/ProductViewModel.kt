package com.samir.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samir.common.network.NetworkResult
import com.samir.commonproduct.domain.GetProductsUseCase
import com.samir.commonproduct.domain.ProductSortOrder
import com.samir.commonproduct.domain.SearchProductsUseCase
import com.samir.commonproduct.domain.SortProductsUseCase
import com.samir.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val sortProductsUseCase: SortProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUIState(isLoading = true))
    val uiState: StateFlow<ProductUIState> = _uiState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            when (val result = getProductsUseCase()) {
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