package com.sora.sora.core.customText

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Text // import material Text instead of BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sora.sora.R


@Composable
fun CustomTextBubbleBobble(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 25.sp,  // use fixed font size or pass explicitly
    color: Color = Color.Black,
    fontWeight: FontWeight? = null,
    lineHeight: TextUnit? = null,
    maxLines: Int = 2,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontFamily = BubbleBobbleFontFamily,
            fontSize = fontSize,
            fontWeight = fontWeight ?: FontWeight.Normal,
            color = color,
            lineHeight = lineHeight ?: (fontSize * 1.5f),
            textAlign = textAlign ?: TextAlign.Start,
            textDecoration = textDecoration,
        ),
        maxLines = maxLines,
        overflow = overflow,
    )
}





val BubbleBobbleFontFamily = FontFamily(
    Font(R.font.bubble_bobble, FontWeight.Normal),
    Font(R.font.bubble_bobble, FontWeight.Bold)
)

