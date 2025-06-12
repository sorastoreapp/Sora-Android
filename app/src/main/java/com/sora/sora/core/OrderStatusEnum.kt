package com.sora.sora.core

import androidx.compose.ui.graphics.Color
import com.sora.sora.ui.theme.SecondaryColor

enum class OrderStatusEnum(val color: Color, val text: String) {
    Pending(Color(0xFF006FEE), "Pending"),
    Processing(SecondaryColor, "Processing"),
    Shipped(Color(0xFF006FEE), "Shipped"),
    Delivered(Color(0xFF07BD74), "Delivered"),
    Canceled(Color(0xFFDB5A5A), "Canceled"),
    Completed(Color(0xFF07BD74), "Completed"),
    Returned(Color(0xFF006FEE), "Returned"),
    Refund(Color(0xFF8A4C3D), "Refund");

    companion object {
        fun fromText(text: String): OrderStatusEnum? {
            return values().find { it.text.equals(text, ignoreCase = true) }
        }
    }
}
