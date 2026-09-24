package com.samir.product.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samir.common.network.NetworkResult
import com.samir.model.Product
import com.samir.product.domain.usecase.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductDetails: GetProductDetailsUseCase
) : ViewModel() {

    private val productId: Int? = savedStateHandle["productId"]

    private val _uiState = MutableStateFlow(
        ProductDetailsUiState(isLoading = true)
    )

    val uiState: StateFlow<ProductDetailsUiState> =
        _uiState.asStateFlow()

    init {
        getProductById()
    }

    private fun getProductById() {
        viewModelScope.launch {

            when (val result = getProductDetails(productId)) {

                is NetworkResult.Success -> {
                    _uiState.value = ProductDetailsUiState(
                        isLoading = false,
                        product = result.data
                    )
                }

                is NetworkResult.Error -> {
                    _uiState.value = ProductDetailsUiState(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                NetworkResult.Loading -> {
                    _uiState.value = ProductDetailsUiState(
                        isLoading = true
                    )
                }
            }
        }
    }

    fun addToCart(product: Product, id: Int) {

    }

    fun buyNow(product: Product, id: Int) {

    }

    fun increaseQuantity(product: Product, id: Int) {

    }

    fun decreaseQuantity(product: Product, id: Int) {

    }

    fun shareProduct(product: Product) {

    }

    fun changeAddress() {

    }


    fun toggleWishlist(product: Product) {

    }
}