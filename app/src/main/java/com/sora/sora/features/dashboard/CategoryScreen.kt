package com.sora.sora.features.dashboard
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.customText.CustomTextBubbleBobble
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.temp.SeeAllModel
import com.sora.sora.features.category.CategoryDetailModel
import java.net.URLEncoder

@Composable
fun CategoryScreen(
    categories: List<CategoryItemData>
) {
    // Use LazyColumn for vertical scroll
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(0.dp)
            .systemBarsPadding()

    ) {
        CustomMontserratText(
            text = "Categories",
            modifier = Modifier
                .fillMaxWidth()
               .padding(vertical = 10.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
               .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(categories) { index, category ->
                CategoryCard(category = category)
            }
        }


    }
}
@Composable
fun CategoryCard(category: CategoryItemData) {
    val shape = RoundedCornerShape(16.dp)

    // Card structure
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)  // Adjust the height of the card
//                .clickable {
//                    // Navigate to category details screen
//                    NavigationManager.navigateTo(Dest.CategoryDetailScreen::class.toRoute())
//                },

                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            val categoryDetailModel = CategoryDetailModel(
                                title = category.title,
                                themeColor1 = category.colorCode1,  // e.g., "#FFFADA7A"
                                themeColor2 = category.colorCode2   // e.g., "#FFFADA7B"
                            )

                            // URL encode both the title and theme colors
                            val encodedTitle = URLEncoder.encode(categoryDetailModel.title, "UTF-8")
                            val encodedThemeColor1 = URLEncoder.encode(categoryDetailModel.themeColor1, "UTF-8")
                            val encodedThemeColor2 = URLEncoder.encode(categoryDetailModel.themeColor2, "UTF-8")

                            Log.d("MyTag", "CategoryCard: ------------------${categoryDetailModel.title}")

                            // Pass the encoded title and theme colors in the navigation URL
                            navController.navigate("${Dest.CategoryDetailScreen::class.toRoute()}?title=$encodedTitle&themeColor1=$encodedThemeColor1&themeColor2=$encodedThemeColor2")
                        }
                    )
                },



                    shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = Color(android.graphics.Color.parseColor(category.colorCode1))
            ) // Apply colorCode1 as the background color
        ) {
            // Content: Text and Icon with white circular background
            Box {
                // Shade image
                Image(
                    painter = painterResource(id = R.drawable.ic_category_shade),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .height(100.dp), // Align the image to the top right corner
                    colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor(category.colorCode2))) // Apply colorCode2 as tint
                )

                // Row for text and icon
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Text with custom font
                    Box(
                        modifier = Modifier
                            .widthIn(max = 190.dp)  // allow wider max width if needed
                            .padding(vertical = 2.dp)  // add vertical padding to avoid clipping
                    ) {
                        CustomMontserratText(
                            text = category.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 2,
                            textAlign = TextAlign.Start,
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
}




data class CategoryItemData(
    val title: String,
    val iconRes: Int,
    val colorCode1: String,  // Left side color
    val colorCode2: String,  // Right side color (if needed for other purposes)
)
