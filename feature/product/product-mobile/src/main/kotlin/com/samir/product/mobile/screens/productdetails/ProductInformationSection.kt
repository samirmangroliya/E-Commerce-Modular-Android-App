package com.samir.product.mobile.screens.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samir.model.Product

@Composable
fun ProductInformationSection(
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        product.brand
            ?.takeIf { it.isNotBlank() }
            ?.let { brand ->
                Text(
                    text = brand,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )
            }

        Text(
            text = product.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingView(
                rating = product.rating
            )

            product.reviews?.count()?.let { reviewCount ->
                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "$reviewCount reviews",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}