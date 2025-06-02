package com.sora.sora.features.favourites.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*


@Composable
fun RatingReviews() {
    var expanded by remember { mutableStateOf(true) }

    val reviews = listOf(
        Review(
            text = "Soft, cute, and perfect for my daughter! She sleeps with it every night, and it still looks brand new after multiple washes.",
            rating = 4,
            reviewer = "Eleanor Pena"
        ),
        Review(
            text = "Great quality, my niece loves it! The fabric is super soft, and the stitching is well done — definitely worth it",
            rating = 3,
            reviewer = "Esther Howard"
        ),
        Review(
            text = "So comfy, I got one for myself too! It adds a nice touch to my sofa and is perfect for lounging.",
            rating = 4,
            reviewer = "Arlene McCoy"
        ),
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 1.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row with title and expand/collapse icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rating & Reviews",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }



            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))

                reviews.forEachIndexed { index, review ->
                    ReviewItem(review = review)
                    if (index != reviews.lastIndex) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

data class Review(val text: String, val rating: Int, val reviewer: String)

@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = review.text,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(6.dp))
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
                    painter = painterResource(R.drawable.ic_filled_star),
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = review.reviewer,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B3F00),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

