package com.sora.sora.utils.constant

import androidx.compose.ui.graphics.Color

enum class OrderStatusEnum(val color: Color, val bgColor: Color, val text: String) {
    Pending(Color(0xFFF2D5B3), Color(0x1AF2D5B3), "Pending"),
    Processing(Color(0xFFB58353), Color(0x1AB58353), "Processing"),
    Shipped(Color(0xFF1E88E5), Color(0x1A1E88E5),  "Shipped"),
    Delivered(Color(0xFF4CAF50), Color(0x1A4CAF50),  "Delivered"),
    Canceled(Color(0xFFDB5A5A), Color(0x0DDB5A5A),  "Canceled"),
    Confirmed(Color(0xFF1E88E5), Color(0x1A1E88E5),  "Confirmed"),
    Returned(Color(0xFFBDBDBD), Color(0x1ABDBDBD),  "Returned"),
    Refund(Color(0xFF8A4C3D), Color(0xFF006FEE),  "Refund");

    companion object {
        fun fromText(text: String): OrderStatusEnum? {
            return values().find { it.text.equals(text, ignoreCase = true) }
        }
    }
}