package com.samir.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.samir.analytics.common.AnalyticsEvent
import com.samir.analytics.common.AnalyticsTracker
import com.samir.analytics.common.AnalyticsValue
import javax.inject.Inject

class FirebaseAnalyticsTracker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsTracker {

    override fun log(event: AnalyticsEvent) {

        val bundle = Bundle()

        event.parameters.forEach { parameter ->

            when (val value = parameter.value) {

                is AnalyticsValue.StringValue -> {
                    bundle.putString(
                        parameter.key,
                        value.value
                    )
                }

                is AnalyticsValue.LongValue -> {
                    bundle.putLong(
                        parameter.key,
                        value.value
                    )
                }

                is AnalyticsValue.DoubleValue -> {
                    bundle.putDouble(
                        parameter.key,
                        value.value
                    )
                }

                is AnalyticsValue.BooleanValue -> {
                    bundle.putBoolean(
                        parameter.key,
                        value.value
                    )
                }

                is AnalyticsValue.BundleValue -> {
                    bundle.putAll(value.value)
                }
            }
        }
        print("Event Name: ${event.name}")
        print("Event Parameters: $bundle")
        firebaseAnalytics.logEvent(
            event.name,
            bundle
        )
    }
}