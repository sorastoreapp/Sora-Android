package com.sora.sora.features.favourites.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomTopBar2


@Composable
fun ReviewDetailScreen() {
    var expanded by remember { mutableStateOf(true) }

    val reviews = listOf(
        ReviewDetailScreen(
            text = "Soft, cute, and perfect for my daughter! She sleeps with it every night, and it still looks brand new after multiple washes.",
            rating = 4,
            reviewer = "Wade Warren"
        ),
        ReviewDetailScreen(
            text = "Great quality, my niece loves it! The fabric is super soft, and the stitching is well done — definitely worth it.",
            rating = 5,
            reviewer = "John Doe"
        ),
        ReviewDetailScreen(
            text = "So comfy, I got one for myself too! It adds a nice touch to my sofa and is perfect for lounging.",
            rating = 5,
            reviewer = "Jane Doe"
        ),
    )
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth().statusBarsPadding(),) {
        // Header Row with title and expand/collapse icon

        CustomTopBar2(title = "Reviews", space = 110.dp)
        Spacer(modifier = Modifier.height(40.dp))
        reviews.forEachIndexed { index, review ->
            ReviewItemDetails(review = review)
            if (index != reviews.lastIndex) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

data class ReviewDetailScreen(val text: String, val rating: Int, val reviewer: String)

@Composable
fun ReviewItemDetails(review: ReviewDetailScreen) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${review.rating} Star",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFC107),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.width(4.dp))
            repeat(review.rating) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filled_star_review), // Use the filled star icon
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = review.text,
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = review.reviewer,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B3F00),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp)
        )

    }
}
