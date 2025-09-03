package com.sora.sora.features.profile.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.ui.theme.PrimaryColor

@Composable
fun ProfileMenuItem(
    iconRes: Int? = null,
    title: String,
    trailingText: String? = null,
    textFontSize: TextUnit? = null,
    testFontWeight: FontWeight? = null,
    showArrow: Boolean = true,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
    modifier: Modifier? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier?:Modifier
            .fillMaxWidth()
            .height(48.dp)
            .pointerInput(Unit) {
                // Custom touch handling for Skip Button without ripple effect
                detectTapGestures {
                    onClick()
                }
            }
            .padding(horizontal = hFactor(10))
    ) {

        if(iconRes!=null)
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier
                    .size(24.dp)
            )
        Spacer(modifier = Modifier. width(12.dp))
        CustomMontserratText(
            text = title,
            fontSize = textFontSize?:15.sp,
            lineHeight = 18.sp,
            fontWeight = testFontWeight?:FontWeight(500),
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        if (trailingText != null) {
            CustomMontserratText(
                text = trailingText,
                color = Color(0xFF8A4C3D),
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                fontSize = 12.sp
            )

        }
        if (trailingContent != null) {
            trailingContent()
        }
        Spacer(modifier = Modifier.width(12.dp))
        if (showArrow) {

            Icon(

                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow",

                tint = PrimaryColor
                ,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}