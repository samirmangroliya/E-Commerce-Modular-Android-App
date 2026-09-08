package com.samir.domain

import com.samir.model.Product
import javax.inject.Inject

enum class ProductSortOrder {
    PriceLowToHigh,
    PriceHighToLow,
    RatingHighToLow,
}

/** Pure logic, no repository — shared by home-mobile AND home-tv ViewModels so sorting
 *  rules never get duplicated or drift between the two UIs. */
class SortProductsUseCase @Inject constructor() {
    operator fun invoke(products: List<Product>, order: ProductSortOrder): List<Product> =
        when (order) {
            ProductSortOrder.PriceLowToHigh -> products.sortedBy { it.discountedPrice }
            ProductSortOrder.PriceHighToLow -> products.sortedByDescending { it.discountedPrice }
            ProductSortOrder.RatingHighToLow -> products.sortedByDescending { it.rating }
        }
}