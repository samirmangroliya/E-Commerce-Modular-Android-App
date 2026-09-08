package com.samir.analytics.events

import com.samir.analytics.common.AnalyticsEvent
import com.samir.analytics.common.AnalyticsParameter
import com.samir.analytics.common.AnalyticsValue

sealed interface CartAnalyticsEvent : AnalyticsEvent {

    data object Viewed : CartAnalyticsEvent {

        override val name = "cart_viewed"
    }

    data class ItemAdded(
        val productId: String,
        val quantity: Long
    ) : CartAnalyticsEvent {

        override val name = "cart_item_added"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "product_id",
                value = AnalyticsValue.StringValue(productId)
            ),
            AnalyticsParameter(
                key = "quantity",
                value = AnalyticsValue.LongValue(quantity)
            )
        )
    }

    data class ItemRemoved(
        val productId: String
    ) : CartAnalyticsEvent {

        override val name = "cart_item_removed"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "product_id",
                value = AnalyticsValue.StringValue(productId)
            )
        )
    }
}