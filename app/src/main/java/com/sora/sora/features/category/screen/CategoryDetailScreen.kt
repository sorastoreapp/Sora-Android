import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R

@Composable
fun CategoryDetailScreen() {
    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
    var selectedFilter by remember { mutableStateOf(filters[0]) }

    // Sample product list, replace with your data
    val products = listOf(
        Product(1, "Soft Plush Bear Toy", "KD 1.500", R.drawable.img_temp_teddy1),
        Product(2, "Kids colorful car toy", "KD 1.500", R.drawable.img_temp_teddy1),
        Product(3, "Multiple Kids toys Collection", "KD 1.500", R.drawable.img_temp_teddy1),
        Product(4, "Blue teddy bear wearing", "KD 1.500", R.drawable.img_temp_teddy1),
        Product(5, "Colorful building blocks castle", "KD 1.500", R.drawable.img_temp_teddy3),
        Product(6, "Adorable teddy bear loves", "KD 1.500", R.drawable.img_temp_teddy2),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Gradient background top quarter
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)  // roughly quarter of typical phone height
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF7D9), Color.Transparent)
                    )
                )
        ) {
            // Top bar icons and title inside the gradient box

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /* Back action */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFCE9C8), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Toys & Plushies",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                IconButton(
                    onClick = { /* Cart action */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFCE9C8), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }
            }

            // Filter chips row near bottom of gradient
            LazyRow(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    FilterChip(
                        text = filter,
                        isSelected = filter == selectedFilter,
                        onClick = { selectedFilter = filter }
                    )
                }
            }
        }

        // Products grid
        val gridState = rememberLazyGridState()
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),  // Use columns instead of cells
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }

    }
    }


@Composable
fun FilterChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(50),
        color = if (isSelected) Color(0xFFF7E2B0) else Color(0xFFF2F2F2),
        elevation = 2.dp
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) Color.Black else Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

data class Product(val id: Int, val title: String, val price: String, val imageRes: Int)

@Composable
fun ProductCard(product: Product) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )
                // Heart icon top-left
                IconButton(
                    onClick = { /* Favorite toggle */ },
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color.White.copy(alpha = 0.8f), shape = CircleShape)
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_remove), // replace with your heart icon
                        contentDescription = "Favorite",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF7B3F00),
                    modifier = Modifier.padding(start = 4.dp)
                )
                IconButton(
                    onClick = { /* Add to cart */ },
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF7B3F00))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
