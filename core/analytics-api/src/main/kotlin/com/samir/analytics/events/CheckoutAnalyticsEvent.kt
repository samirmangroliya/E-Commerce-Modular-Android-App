package com.samir.analytics.events

import com.samir.analytics.common.AnalyticsEvent
import com.samir.analytics.common.AnalyticsParameter
import com.samir.analytics.common.AnalyticsValue

sealed interface CheckoutAnalyticsEvent : AnalyticsEvent {

    data class Started(
        val cartId: String
    ) : CheckoutAnalyticsEvent {

        override val name = "checkout_started"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "cart_id",
                value = AnalyticsValue.StringValue(cartId)
            )
        )
    }

    data class PaymentStarted(
        val orderId: String,
        val paymentMethod: String
    ) : CheckoutAnalyticsEvent {

        override val name = "payment_started"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "order_id",
                value = AnalyticsValue.StringValue(orderId)
            ),
            AnalyticsParameter(
                key = "payment_method",
                value = AnalyticsValue.StringValue(paymentMethod)
            )
        )
    }

    data class Completed(
        val orderId: String
    ) : CheckoutAnalyticsEvent {

        override val name = "checkout_completed"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "order_id",
                value = AnalyticsValue.StringValue(orderId)
            )
        )
    }
}