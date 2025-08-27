package com.sora.sora.utils.constant

import androidx.compose.ui.graphics.Color

enum class OrderStatusEnum(val color: Color, val bgColor: Color, val text: String) {
    Pending(Color(0xFFB58353), Color(0x1AB58353), "Pending"),
    Processing(Color(0xFFB58353), Color(0x1AB58353), "Processing"),
    Shipped(Color(0xFF006FEE), Color(0x1A006FEE),  "Shipped"),
    Delivered(Color(0xFF07BD74), Color(0x0D07BD74),  "Delivered"),
    Canceled(Color(0xFFDB5A5A), Color(0x0DDB5A5A),  "Canceled"),
    Completed(Color(0xFF07BD74), Color(0xFF006FEE),  "Completed"),
    Returned(Color(0xFF000000), Color(0x0D1C0F0C),  "Returned"),
    Refund(Color(0xFF8A4C3D), Color(0xFF006FEE),  "Refund");

    companion object {
        fun fromText(text: String): OrderStatusEnum? {
            return values().find { it.text.equals(text, ignoreCase = true) }
        }
    }
}