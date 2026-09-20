package com.samir.common.network

import java.io.IOException
suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(apiCall())
    } catch (e: IOException) {
        NetworkResult.Error("No internet connection", e)
    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "Unknown error", e)
    }
}
