package com.samir.analytics.events

import com.samir.analytics.common.AnalyticsEvent
import com.samir.analytics.common.AnalyticsParameter
import com.samir.analytics.common.AnalyticsValue

sealed interface ProductAnalyticsEvent : AnalyticsEvent {

    data class Viewed(
        val productId: Int,
        val category: String
    ) : ProductAnalyticsEvent {

        override val name = "product_viewed"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "product_id",
                value = AnalyticsValue.LongValue(productId.toLong())
            ),
            AnalyticsParameter(
                key = "category",
                value = AnalyticsValue.StringValue(category)
            )
        )
    }

    data class AddedToCart(
        val productId: String,
        val quantity: Long
    ) : ProductAnalyticsEvent {

        override val name = "product_added_to_cart"

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

    data class Shared(
        val productId: String
    ) : ProductAnalyticsEvent {

        override val name = "product_shared"

        override val parameters = listOf(
            AnalyticsParameter(
                key = "product_id",
                value = AnalyticsValue.StringValue(productId)
            )
        )
    }
}