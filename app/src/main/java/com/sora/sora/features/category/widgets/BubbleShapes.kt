package com.sora.sora.features.category.widgets

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BubbleShape(
    private val cornerRadius: Float = 18f,
    private val pointerWidth: Float = 18f,
    private val pointerHeight: Float = 12f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            // Correct usage of RoundRect for rounded rectangle
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height - pointerHeight,
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            )
            // Draw the pointer ("pin")
            val pointerCenterX = size.width / 2
            moveTo(pointerCenterX - pointerWidth / 2, size.height - pointerHeight)
            lineTo(pointerCenterX, size.height)
            lineTo(pointerCenterX + pointerWidth / 2, size.height - pointerHeight)
            close()
        }
        return Outline.Generic(path)
    }
}
