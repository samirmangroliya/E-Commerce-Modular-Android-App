package com.samir.analytics.common

import android.os.Bundle
sealed interface AnalyticsValue {

    data class StringValue(
        val value: String
    ) : AnalyticsValue

    data class LongValue(
        val value: Long
    ) : AnalyticsValue

    data class DoubleValue(
        val value: Double
    ) : AnalyticsValue

    data class BooleanValue(
        val value: Boolean
    ) : AnalyticsValue

    data class BundleValue(
        val value: Bundle
    ) : AnalyticsValue

}