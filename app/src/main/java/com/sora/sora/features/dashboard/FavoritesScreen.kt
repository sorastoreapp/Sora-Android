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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.widgets.ProductCard
import com.sora.sora.features.favourites.widgets.FavoriteEmptyStateView

// Sample Product data class
data class ProductFavorites(
    val id: Int,
    val name: String,
    val price: String,
    val imagePainter: Painter,
    val bgColor: Color
)

@Composable
fun FavoritesScreen() {
    val favProductList = remember {
        mutableStateListOf<Product>()
    }

    // Load images inside composable scope
    val imgTshirt = painterResource(R.drawable.img_temp_tshirt)
    val imgShorts = painterResource(R.drawable.img_temp_shorts)

    // Initialize list only once (if it's empty)
    LaunchedEffect(Unit) {
        if (favProductList.isEmpty()) {
            favProductList.addAll(
                listOf(
                    Product(4, "Brown Men Full T-shirt", "1.500", discountPercent = 20, oldPrice = "2.500", imgTshirt),
                    Product(5, "Baby Denim Blue Jean Outfit", "1.500", discountPercent = 20, oldPrice = "2.500", imgShorts),
                    Product(6, "Brown Men Full T-shirt", "1.500", discountPercent = 20, oldPrice = "2.500", imgTshirt)
                )
            )
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CustomMontserratText(
                text = "Favorite",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

           if(favProductList.isNotEmpty()) LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(favProductList, key = { it.id }) { product ->
                    var qty by remember { mutableIntStateOf(0) }

                    ProductCard(
                        product = product,
                        onFavorite = { favProductList.remove(product) }, // ✅ Remove item
                        isFavoriteScreen = true,
                        onShare = { /* … */ },
                        onAddToCart = { qty++ },
                        onRemoveFromCart = { if (qty > 0) qty-- },
                        quantity = qty
                    )
                }
            }
            else{
               FavoriteEmptyStateView()
        }
        }
    }
}





