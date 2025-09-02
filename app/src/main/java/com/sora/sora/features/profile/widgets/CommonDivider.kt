package com.sora.sora.features.profile.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CommonDivider(
    color: Color = Color.Gray.copy(alpha = 0.1f),
    thickness: Dp = 1.dp,
    horizontalPadding: Dp = 0.dp,
    verticalPadding: Dp = 4.dp
) {
    Divider(
        color = color,
        thickness = thickness,
        modifier = Modifier
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    )
}