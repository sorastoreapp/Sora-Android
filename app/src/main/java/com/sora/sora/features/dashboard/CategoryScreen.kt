package com.sora.sora.features.dashboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomTextBubbleBobble

@Composable
fun CategoryScreen(
    categories: List<CategoryItemData>
) {
    // Background images list, exactly 10 images - change as per your resources
    val backgroundImages = listOf(
        R.drawable.img_bg_cat1,
        R.drawable.img_bg_cat2,
        R.drawable.img_bg_cat3,
        R.drawable.img_bg_cat4,
        R.drawable.img_bg_cat5,
        R.drawable.img_bg_cat6,
        R.drawable.img_bg_cat7,
        R.drawable.img_bg_cat8,
        R.drawable.img_bg_cat9,
//        R.drawable.img_bg_cat10,
    )

    // Use LazyColumn for vertical scroll, map each category with corresponding bg image
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding( top = 16.dp)
    )  {
        androidx.compose.material3.Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(categories) { index, category ->
                val bgImageRes = backgroundImages.getOrNull(index) ?: backgroundImages.last()
                CategoryCard(category = category, backgroundRes = bgImageRes)
            }

        }
    }
}

@Composable
fun CategoryCard(category: CategoryItemData, backgroundRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), // approximate height to match your screenshots
        shape = RoundedCornerShape(16.dp),
        // Optional: set card elevation or shadow here if using Material3 CardDefaults
    ) {
        Box {
            // Background image fill card area
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Content: Text and Icon with white circular background
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Text with custom bubble bobble font
                Box(
                    modifier = Modifier
                        .widthIn(max = 140.dp)  // allow wider max width if needed
                        .padding(vertical = 2.dp)  // add vertical padding to avoid clipping
                ) {
                    CustomTextBubbleBobble(
                        text = category.title,
                        fontSize = 25.sp,
                        color = Color.Black,
                        maxLines = 2,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Start,
//                            softWrap = true   // If your custom text composable supports this parameter
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // White circular background with icon inside
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 6.dp,
                    modifier = Modifier.size(60.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = category.iconRes),
                            contentDescription = category.title,
                            modifier = Modifier.size(36.dp) // icon size similar to your screenshots
                        )
                    }
                }
            }
        }
    }
}
data class CategoryItemData(
    val title: String,
    val iconRes: Int
)
