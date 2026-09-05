package com.samir.network

/**
 * Every consuming app (mobile, tv, or a completely different project) provides its OWN
 * implementation of this via Hilt. core-network never hardcodes a base URL or auth logic —
 * that's what makes this module portable across apps.
 */
interface NetworkConfig {
    val baseUrl: String
    val enableLogging: Boolean

    /** Return null if the app has no auth requirement (e.g. public product catalog APIs). */
    val authTokenProvider: (() -> String?)?
}