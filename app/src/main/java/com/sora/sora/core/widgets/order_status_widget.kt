package com.sora.sora.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.utils.constant.OrderStatusEnum
import com.sora.sora.core.customText.CustomInterText

@Composable
fun OrderStatusWidget(status: OrderStatusEnum?, ) {
 //   println("status $status enum ${OrderStatusEnum.fromText(status)} ${OrderStatusEnum.Processing.text}")
    Box(
        modifier = Modifier
            .background(
                color = status?.bgColor ?: Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 9.dp, vertical = 5.dp)
    ) {
        CustomInterText(
            text = status?.text?:"",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = status?.color?:Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}