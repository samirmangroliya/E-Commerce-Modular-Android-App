package com.samir.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkConfig: NetworkConfig,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (networkConfig.enableLogging) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            addInterceptor(authInterceptor)
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, networkConfig: NetworkConfig): Retrofit =
        Retrofit.Builder()
            .baseUrl(networkConfig.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}