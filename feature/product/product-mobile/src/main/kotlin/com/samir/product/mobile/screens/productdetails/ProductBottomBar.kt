package com.samir.product.mobile.screens.productdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samir.model.Product

@Composable
fun ProductBottomBar(
    product: Product,
    onAddToCart: (Product, Int) -> Unit,
    onBuyNow: (Product, Int) -> Unit
) {
    var quantity by remember {
        mutableIntStateOf(1)
    }

    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(
                onClick = {
                    onAddToCart(
                        product,
                        quantity
                    )
                },
                modifier = Modifier.weight(1f),
                enabled = product.isAvailable
            ) {
                Text("Add to Cart")
            }

            Button(
                onClick = {
                    onBuyNow(
                        product,
                        quantity
                    )
                },
                modifier = Modifier.weight(1f),
                enabled = product.isAvailable
            ) {
                Text("Buy Now")
            }
        }
    }
}