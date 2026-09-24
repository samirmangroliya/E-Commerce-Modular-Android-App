package com.samir.product.di

import com.samir.product.data.ProductDetailDetailRepositoryImpl
import com.samir.product.domain.repository.ProductDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDetailModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        implementation: ProductDetailDetailRepositoryImpl
    ): ProductDetailRepository
}