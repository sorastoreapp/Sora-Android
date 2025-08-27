package com.sora.sora.utils.models

import com.sora.sora.utils.constant.OrderStatusEnum


data class OrderModel(
        val orderId: String,
        val items: Int,
        val date: String,
        val productName: String,
        val amount: String,
        val status: OrderStatusEnum,
        val image: Int // Image resource for the product
    )
