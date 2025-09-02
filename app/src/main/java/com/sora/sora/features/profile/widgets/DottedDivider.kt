package com.sora.sora.features.profile.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DottedDivider(
    color: Color,
    dashWidth: Dp = 4.dp,
    dashGap: Dp = 4.dp,
    height: Dp = 1.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val dashWidthPx = dashWidth.toPx()
        val dashGapPx = dashGap.toPx()
        var startX = 0f
        val y = size.height / 2

        while (startX < size.width) {
            drawLine(
                color = color,
                start = Offset(startX, y),
                end = Offset(startX + dashWidthPx, y),
                strokeWidth = height.toPx(),
                cap = StrokeCap.Round
            )
            startX += dashWidthPx + dashGapPx
        }
    }
}