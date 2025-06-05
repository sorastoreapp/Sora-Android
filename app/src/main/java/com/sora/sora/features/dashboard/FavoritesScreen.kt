package com.sora.sora.features.dashboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.widgets.ProductCard

// Sample Product data class
data class ProductFavorites(
    val id: Int,
    val name: String,
    val price: String,
    val imagePainter: Painter,
    val bgColor: Color
)

// Product Card composable, styled similarly to your screenshot
@Composable
fun ProductFavoritesCard(product: Product) {
    Card(
        modifier = Modifier.size(width = 150.dp, height = 220.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Product Image
            Image(
                painter = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 10.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Wrap the heart icon box inside another Box with padding acting as margin
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp) // This is the margin from top and left
                        .align(Alignment.TopStart)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(PrimaryColorFaded, shape = CircleShape)
                            .clickable { /* Handle favorite click */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = PrimaryColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Other content like image, title, etc.
            }

            // Title & Price Column just below the image
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 12.dp, top = 130.dp, end = 12.dp,) // below image
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Text(
                    text = product.price,
                    fontSize = 13.sp,
                    color = PrimaryColor,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Add button with padding from bottom and right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 8.dp, end = 8.dp)
                    .size(width = 36.dp, height = 32.dp)
                    .background(
                        color = PrimaryColor,
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp ,topEnd = 10.dp)
                    )
                    .clickable { /* Handle add to cart click */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun FavoritesScreen() {
    val favProductList = listOf(
        Product(4, "Brown Men Full T-shirt", "KD 1.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Baby Denim Blue Jean Outfit", "KD 1.500", painterResource(R.drawable.img_temp_shorts)),
        Product(6, "Brown Men Full T-shirt", "KD 1.500", painterResource(R.drawable.img_temp_tshirt)),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Favorites",
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(favProductList) { product ->
                    ProductCard(product = product,)
                }
            }
        }
    }
}



