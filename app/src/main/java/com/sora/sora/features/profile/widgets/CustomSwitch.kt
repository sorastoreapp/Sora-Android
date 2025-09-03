package com.sora.sora.features.profile.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.sora.sora.features.profile.screen.MinimalSwitchColors


@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: MinimalSwitchColors = MinimalSwitchColors()
) {
    // Choose colors based on state
    val targetTrack = when {
        !enabled -> colors.disabledTrack
        checked -> colors.checkedTrack
        else -> colors.uncheckedTrack
    }
    val targetThumb = when {
        !enabled -> colors.disabledThumb
        checked -> colors.checkedThumb
        else -> colors.uncheckedThumb
    }

    val trackColor by animateColorAsState(targetValue = targetTrack, label = "trackColor")
    val thumbColor by animateColorAsState(targetValue = targetThumb, label = "thumbColor")
    val thumbOffset by animateDpAsState(targetValue = if (checked) 33.dp else 4.dp, label = "thumbOffset")

    val shape = CircleShape

    Box(
        modifier = modifier
            .width(55.dp)
            .height(27.dp)
            .clip(shape)
            .background(trackColor)
            .toggleable( // ✅ instead of clickable + semantics
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                onValueChange = { onCheckedChange(it) }
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        // Thumb
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(18.5.dp)
                .clip(shape)
                .background(thumbColor)
//                .border(
//                    width = 1.dp,
//                    color = if (enabled) Color.Black.copy(alpha = 0.05f) else Color.Black.copy(alpha = 0.04f),
//                    shape = shape
//                )
        )
    }
}