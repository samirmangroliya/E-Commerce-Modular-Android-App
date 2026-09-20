package com.samir.common.network

/**
 * Generic wrapper for any async operation result.
 * Used across ALL data/feature modules so every layer speaks the same language.
 */
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val message: String, val throwable: Throwable? = null) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}


