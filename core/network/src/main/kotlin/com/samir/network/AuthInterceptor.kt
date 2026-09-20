package com.samir.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val networkConfig: NetworkConfig
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = networkConfig.authTokenProvider?.invoke()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}