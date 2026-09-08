package com.samir.analytics.events

import com.samir.analytics.common.AnalyticsEvent
import com.samir.analytics.common.AnalyticsParameter
import com.samir.analytics.common.AnalyticsValue

sealed interface OrderAnalyticsEvent : AnalyticsEvent {

    data class Placed(
        val orderId: String,
        val totalAmount: Double,
        val currency: String
    ) : OrderAnalyticsEvent {

        override val name = "order_placed"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "order_id",
                value = AnalyticsValue.StringValue(orderId)
            ),
            AnalyticsParameter(
                key = "total_amount",
                value = AnalyticsValue.DoubleValue(totalAmount)
            ),
            AnalyticsParameter(
                key = "currency",
                value = AnalyticsValue.StringValue(currency)
            )
        )
    }

    data class Viewed(
        val orderId: String
    ) : OrderAnalyticsEvent {

        override val name = "order_viewed"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "order_id",
                value = AnalyticsValue.StringValue(orderId)
            )
        )
    }

    data class Cancelled(
        val orderId: String,
        val reason: String?
    ) : OrderAnalyticsEvent {

        override val name = "order_cancelled"

        override val parameters = buildList {

            add(
                AnalyticsParameter(
                    key = "order_id",
                    value = AnalyticsValue.StringValue(orderId)
                )
            )

            reason?.let {
                add(
                    AnalyticsParameter(
                        key = "reason",
                        value = AnalyticsValue.StringValue(it)
                    )
                )
            }
        }
    }
}