package com.samir.analytics.common
interface AnalyticsTracker {

    fun log(event: AnalyticsEvent)
}