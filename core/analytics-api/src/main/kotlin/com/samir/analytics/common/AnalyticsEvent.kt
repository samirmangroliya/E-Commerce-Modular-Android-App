package com.samir.analytics.common
interface AnalyticsEvent {
    val name: String
    val parameters: List<AnalyticsParameter>
        get() = emptyList()
}