import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomTopBar
import com.sora.sora.features.dashboard.Product
import com.sora.sora.core.widgets.ProductCard

@Composable
fun CategoryDetailScreen() {
    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
    var selectedFilter by remember { mutableStateOf(filters[0]) }

    val products = listOf(
        Product(1, "Soft Plush Bear Toy", "KD 1.500", painterResource(R.drawable.img_temp_teddy)),
        Product(2, "Kids colorful car toy", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(3, "Multiple Kids toys Collection", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(4, "Blue teddy bear wearing", "KD 1.500",  painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Colorful building blocks castle", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
    )

    val gridState = rememberLazyGridState()

    // Maximum height of the header and min height when collapsed
    val maxHeaderHeight = 230.dp
    val minHeaderHeight = 90.dp

    // Convert scroll offset to px
    val scrollOffsetPx = gridState.firstVisibleItemScrollOffset.toFloat()
    val scrollItemIndex = gridState.firstVisibleItemIndex

    // Calculate collapse fraction based on scroll offset
    val collapseRangePx = with(LocalDensity.current) { (maxHeaderHeight - minHeaderHeight).toPx() }

    val collapseFraction = when {
        scrollItemIndex > 0 -> 1f // fully collapsed if scrolled past first item
        else -> (scrollOffsetPx / collapseRangePx).coerceIn(0f, 1f)
    }

    // Animate header height and teddy size based on collapseFraction
    val headerHeight by animateDpAsState(maxHeaderHeight - (maxHeaderHeight - minHeaderHeight) * collapseFraction)
    val teddySize by animateDpAsState(80.dp - (80.dp - 36.dp) * collapseFraction)

    Column(modifier = Modifier.fillMaxSize().padding(bottom = 40.dp)) {
        val topPadding = 22.dp
        val topPaddingForTopBar = 44.dp
        val bottomPadding = 16.dp

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight + bottomPadding) // Removed topPadding here
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFF8DC82), Color.Transparent)
                    )
                )
                .padding(bottom = bottomPadding) // Only bottom padding here
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(top = topPadding)) {
                // Top bar - fixed height with top padding

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(minHeaderHeight)
                        .padding(horizontal = 8.dp)
                        .padding(top = topPaddingForTopBar), // Add top padding here only
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back button on the left
                    Image(
                        painter = painterResource(id = R.drawable.img_back_circular),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(70.dp)
                            .clickable {   } // If onBackClick is provided, it will be invoked
                    )

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
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                }

                // Teddy Image - animated size and alpha, reduced height to headerHeight - minHeaderHeight - some extra margin
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight - minHeaderHeight - 12.dp), // Reduce height a bit to remove gap
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_temp_black_teddy),
                        contentDescription = "",
                        modifier = Modifier.size(teddySize),
                        contentScale = ContentScale.Fit,
                        alpha = (1f - collapseFraction)
                    )
                }
            }
        }

        // Filter Chips - pinned at bottom
        LazyRow(
            modifier = Modifier
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

        // Product grid scrollable content
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(product,)
            }
        }
    }
}
@Composable
fun FilterChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(50),
        color = if (isSelected) Color.White else Color(0xFFF2F2F2),
        elevation = 2.dp
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.Black else Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

/**WORKING CODE WITHOUT ANIMATION*/
//@Composable
//fun CategoryDetailScreen() {
//    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
//    var selectedFilter by remember { mutableStateOf(filters[0]) }
//
//    // Sample product list, replace with your data
//    val products = listOf(
//        Product(1, "Soft Plush Bear Toy", "KD 1.500", painterResource(R.drawable.img_temp_teddy)),
//        Product(2, "Kids colorful car toy", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(3, "Multiple Kids toys Collection", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(4, "Blue teddy bear wearing", "KD 1.500",  painterResource(R.drawable.img_temp_tshirt)),
//        Product(5, "Colorful building blocks castle", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "KD 1.500",  painterResource(R.drawable.img_temp_multi_toy)),
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = 56.dp)
//    ) {
//        // Gradient background top quarter
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(230.dp)  // roughly quarter of typical phone height
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(Color(0xFFF8DC82), Color.Transparent)
//                    )
//                )
//        ) {
//            // Top bar icons and title inside the gradient box
//
//            Column( modifier = Modifier.fillMaxSize()) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 1.dp)
////                        .background(color= Color.Red)
//                        .padding(top = 44.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    IconButton(
//                        onClick = { /* Back action */ },
//                        modifier = Modifier
//                            .size(40.dp)
//                            .background(Color(0xFFFCE9C8), shape = CircleShape)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//
//                    Text(
//                        text = "Toys & Plushies",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp
//                    )
//
//                    IconButton(
//                        onClick = { /* Cart action */ },
//                        modifier = Modifier
//                            .size(40.dp)
//                            .background(Color(0xFFFCE9C8), shape = CircleShape)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.ShoppingCart,
//                            contentDescription = "Cart"
//                        )
//                    }
//                }
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(90.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.img_temp_black_teddy),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(80.dp)
////                            .background(Color.Blue)
//                    )
//                }
//
//            }
//
//            // Black mug image below the top bar with some vertical padding
//
//
//            // Filter chips row near bottom of gradient
//            LazyRow(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(start = 16.dp, bottom = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(filters) { filter ->
//                    FilterChip(
//                        text = filter,
//                        isSelected = filter == selectedFilter,
//                        onClick = { selectedFilter = filter }
//                     )
//                }
//            }
//
//        }
//
//        // Products grid
//        val gridState = rememberLazyGridState()
//        LazyVerticalGrid(
//            state = gridState,
//            columns = GridCells.Fixed(2),  // Use columns instead of cells
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(products) { product ->
//                ProductCard(product = product)
//            }
//        }
//
//    }
//    }
//
//


