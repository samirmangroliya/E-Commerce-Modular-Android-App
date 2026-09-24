package com.samir.product.mobile.screens.productdetails

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.samir.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsTopBar(
    onBack: () -> Unit,
    product: Product?,
    onWishlistClick: (Product) -> Unit,
    onShareClick: (Product) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = product?.title.orEmpty(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            if (product != null) {
                IconButton(
                    onClick = {
                        onWishlistClick(product)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to wishlist"
                    )
                }

                IconButton(
                    onClick = {
                        onShareClick(product)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share product"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}