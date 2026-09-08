package com.samir.data

import com.samir.model.Product

internal fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand ?: "Generic",
    category = category,
    thumbnail = thumbnail,
    images = images,
)