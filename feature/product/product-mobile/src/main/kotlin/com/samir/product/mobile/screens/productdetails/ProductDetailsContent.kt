package com.samir.product.mobile.screens.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samir.model.Product

@Composable
fun ProductDetailsContent(
    product: Product,
    onChangeAddress: () -> Unit,
    modifier: Modifier = Modifier
) {
    var quantity by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {

        ProductImageSection(
            product = product
        )

        HorizontalDivider()

        ProductInformationSection(
            product = product
        )

        HorizontalDivider()

        ProductPriceSection(
            product = product
        )

        HorizontalDivider()

        ProductDescriptionSection(
            product = product
        )

        HorizontalDivider()

        DeliverySection(
            product = product,
            onChangeAddress = onChangeAddress
        )

        HorizontalDivider()

        QuantitySection(
            quantity = quantity,
            onIncrease = {
                quantity++
            },
            onDecrease = {
                if (quantity > 1) {
                    quantity--
                }
            }
        )

        Spacer(
            modifier = Modifier.height(100.dp)
        )
    }
}