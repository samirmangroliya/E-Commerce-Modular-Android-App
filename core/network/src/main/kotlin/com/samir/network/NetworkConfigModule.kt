package com.samir.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkConfigModule {

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig = object : NetworkConfig {
        override val baseUrl = "https://dummyjson.com/"
        override val enableLogging = BuildConfig.DEBUG
        override val authTokenProvider: (() -> String?)? = null // dummyjson product endpoints are public
    }
}