package com.sora.sora.features.category.screen

/**BY NOW ONLY WORKING CODE */
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.features.dashboard.Product
import com.sora.sora.core.widgets.ProductCard

@Composable
fun CategoryDetailScreen() {
    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
    var selectedFilter by remember { mutableStateOf(filters[0]) }

    val products = listOf(
        Product(1, "Soft Plush Bear Toy", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_teddy)),
        Product(2, "Kids colorful car toy", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(3, "Multiple Kids toys Collection", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(4, "Blue teddy bear wearing", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Colorful building blocks castle", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy))
    )

    val gridState = rememberLazyGridState()

    // Header heights in dp
    val maxHeaderHeight = 170.dp
    val minHeaderHeight = 80.dp

    // Teddy parameters
    val maxTeddySize = 55.dp
    val minTeddySize = 28.dp
    val maxTeddyTopPadding = 36.dp
    val minTeddyTopPadding = 8.dp
    val maxTeddyStartPadding = 12.dp  // Fine-tuning for the transition from below the title
    val minTeddyStartPadding = 0.dp   // The final position near the title

    val maxTitleStartPadding = 0.dp
    val minTitleStartPadding = 36.dp

    // Scroll progress calculation
    val scrollPx = gridState.firstVisibleItemScrollOffset.toFloat()
    val isPastFirstItem = gridState.firstVisibleItemIndex > 0
    val collapseRangePx = with(LocalDensity.current) { (maxHeaderHeight - minHeaderHeight).toPx() }
    val collapseFraction = when {
        isPastFirstItem -> 1f
        else -> (scrollPx / collapseRangePx).coerceIn(0f, 1f)
    }

    // Animated values for transformations
    val headerHeight by animateDpAsState(maxHeaderHeight - (maxHeaderHeight - minHeaderHeight) * collapseFraction)
    val teddySize by animateDpAsState(maxTeddySize - (maxTeddySize - minTeddySize) * collapseFraction)
    val teddyTopPadding by animateDpAsState(maxTeddyTopPadding - (maxTeddyTopPadding - minTeddyTopPadding) * collapseFraction)
    val teddyStartPadding by animateDpAsState(maxTeddyStartPadding + (minTeddyStartPadding - maxTeddyStartPadding) * collapseFraction)
    val teddyScale = 1f - 0.25f * collapseFraction
    val teddyAlpha = 1f - 0.2f * collapseFraction
    val titleStartPadding by animateDpAsState(maxTitleStartPadding + (minTitleStartPadding - maxTitleStartPadding) * collapseFraction)
    val titleVerticalOffset by animateDpAsState(10.dp * collapseFraction)

    Column(modifier = Modifier.fillMaxSize().padding(bottom = 40.dp)) {
        val topPadding = 22.dp
        val topPaddingForTopBar = 44.dp
        val bottomPadding = 16.dp

        Box(
            Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .background(Brush.verticalGradient(listOf(Color(0xFFF8DC82), Color.Transparent)))
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(top = topPadding)) {
                // Top bar - fixed height with top padding

                Row(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                        .padding(top = 22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button
                    IconButton(
                        onClick = { /* Back logic */ },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFFA48963))
                    }

                    // Animated title
                    Text(
                        text = "Toys & Plushies",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A260C),
                        modifier = Modifier
                            .padding(start = titleStartPadding)
                            .offset(y = titleVerticalOffset)
                            .graphicsLayer {
                                alpha = 1f // Optionally animate alpha
                            }
                    )
                    Spacer(Modifier.weight(1f))
                    // Cart
                    IconButton(
                        onClick = { /* cart */ },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color(0xFFA48963))
                    }
                }
                // Teddy Icon with animated transition to the left of the title
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
//                        .padding(
//                            start = teddyStartPadding,
//                            top = teddyTopPadding,
//                            end = 4.dp,
//                            bottom = 4.dp
//                        )
//                        .size(teddySize)
                        .offset(x = teddyStartPadding, y = titleVerticalOffset) // Horizontal movement
                        .graphicsLayer {
                            scaleX = teddyScale
                            scaleY = teddyScale
                            alpha = teddyAlpha
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_temp_black_teddy),
                        contentDescription = "Teddy",
                        modifier = Modifier.fillMaxSize()
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
                ProductCard(product)
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


/**Totally New Approach */
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.ScrollState
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.rememberImagePainter
//
//@Composable
//fun com.sora.sora.features.category.screen.CategoryDetailScreen(category: CategoryModel?) {
//    val viewModel = remember { CategoriesDetailViewModel() }
//    val favoriteModel = remember { ProductDetailViewModel() }
//    val bottomValueUpdate = remember { BottomViewModel.shared }
//    val store = remember { ProductStore.shared }
//
//    var selectedTab by remember { mutableStateOf<SubCategoryModel?>(null) }
//    val scrollState = rememberScrollState()
//
//    val scrollOffset = remember { mutableStateOf(100f) }
//
//    val accentColor = Color.Yellow
//    val backgroundColor = Color(0xFFF2C549)
//    val foregroundColor = Color(0xFFFFFAF1)
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        // Scrollable content with a header
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(scrollState)
//        ) {
//            Spacer(modifier = Modifier.height(10.dp))
//
//            // Product List view
//            ProductListView()
//        }
//
//        // Header View
//        HeaderView(
//            category = category,
//            scrollState = scrollState,
//            accentColor = accentColor,
//            backgroundColor = backgroundColor
//        )
//    }
//}
//
//@Composable
//fun HeaderView(
//    category: CategoryModel?,
//    scrollState: ScrollState,
//    accentColor: Color,
//    backgroundColor: Color
//) {
//    val scale = animateFloatAsState(
//        targetValue = 1.0f - (scrollState.value * 0.6f / 1000),
//        animationSpec = tween(durationMillis = 300)
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(accentColor, Color.White)
//                )
//            )
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .align(Alignment.CenterStart)
//        ) {
//            IconButton(onClick = { /* handle back action */ }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//            }
//            Text(
//                text = category?.name ?: "Category",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Image(
//                painter = rememberImagePainter(category?.icon ?: "default_url"),
//                contentDescription = "Teddy Bear",
//                modifier = Modifier
//                    .size(120.dp)
//                    .scale(scale.value)
//            )
//        }
//    }
//}
//
//@Composable
//fun ProductListView() {
//    // Placeholder for product list view
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//        items(20) { index ->
//            ProductCard(index)
//        }
//    }
//}
//
//@Composable
//fun ProductCard(index: Int) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = rememberImagePainter("https://example.com/product_$index.jpg"),
//                contentDescription = "Product Image",
//                modifier = Modifier.size(50.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "Product $index", fontSize = 18.sp)
//        }
//    }
//}
//
//@Composable
//fun ProductDetailView() {
//    // Placeholder for product details screen
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text("Product Detail", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        // Other UI elements like price, description, etc.
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyAppTheme {
//        com.sora.sora.features.category.screen.CategoryDetailScreen(category = CategoryModel(id = 1, name = "Toys & Plushies", icon = "https://example.com/icon.png"))
//    }
//}
//
//data class CategoryModel(val id: Int, val name: String, val icon: String)
//data class SubCategoryModel(val id: Int, val name: String)
//
//object AppColors {
//    val primary = Color(0xFFF2C549)
//}
//
//class CategoriesDetailViewModel
//class ProductDetailViewModel
//object BottomViewModel {
//    val shared = BottomViewModel
//}
//object ProductStore {
//    val shared = ProductStore
//}
//
//@Composable
//fun MyAppTheme(content: @Composable () -> Unit) {
//    MaterialTheme(
//        content = content
//    )
//}





















/**WORKING CODE WITH Improper  ANIMATION*/
//@Composable
//fun com.sora.sora.features.category.screen.CategoryDetailScreen() {
//    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
//    var selectedFilter by remember { mutableStateOf(filters[0]) }
//
//    val products = listOf(
//        Product(1, "Soft Plush Bear Toy", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_teddy)),
//        Product(2, "Kids colorful car toy", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(3, "Multiple Kids toys Collection", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
//        Product(4, "Blue teddy bear wearing", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_tshirt)),
//        Product(5, "Colorful building blocks castle", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_multi_toy)),
//    )
//
//    val gridState = rememberLazyGridState()
//
//    // Maximum height of the header and min height when collapsed
//    val maxHeaderHeight = 230.dp
//    val minHeaderHeight = 90.dp
//
//    // Convert scroll offset to px
//    val scrollOffsetPx = gridState.firstVisibleItemScrollOffset.toFloat()
//    val scrollItemIndex = gridState.firstVisibleItemIndex
//
//    // Calculate collapse fraction based on scroll offset
//    val collapseRangePx = with(LocalDensity.current) { (maxHeaderHeight - minHeaderHeight).toPx() }
//
//    val collapseFraction = when {
//        scrollItemIndex > 0 -> 1f // fully collapsed if scrolled past first item
//        else -> (scrollOffsetPx / collapseRangePx).coerceIn(0f, 1f)
//    }
//
//    // Animate header height and teddy size based on collapseFraction
//    val headerHeight by animateDpAsState(maxHeaderHeight - (maxHeaderHeight - minHeaderHeight) * collapseFraction)
//    val teddySize by animateDpAsState(80.dp - (80.dp - 36.dp) * collapseFraction)
//
//    Column(modifier = Modifier.fillMaxSize().padding(bottom = 40.dp)) {
//        val topPadding = 22.dp
//        val topPaddingForTopBar = 44.dp
//        val bottomPadding = 16.dp
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(headerHeight + bottomPadding) // Removed topPadding here
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(Color(0xFFF8DC82), Color.Transparent)
//                    )
//                )
//                .padding(bottom = bottomPadding) // Only bottom padding here
//        ) {
//            Column(modifier = Modifier.fillMaxSize().padding(top = topPadding)) {
//                // Top bar - fixed height with top padding
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(minHeaderHeight)
//                        .padding(horizontal = 8.dp)
//                        .padding(top = topPaddingForTopBar), // Add top padding here only
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // Back button on the left
//                    Image(
//                        painter = painterResource(id = R.drawable.img_back_circular),
//                        contentDescription = "Back",
//                        modifier = Modifier
//                            .size(70.dp)
//                            .clickable {   } // If onBackClick is provided, it will be invoked
//                    )
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
//                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
//                    }
//                }
//
//                // Teddy Image - animated size and alpha, reduced height to headerHeight - minHeaderHeight - some extra margin
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(headerHeight - minHeaderHeight - 12.dp), // Reduce height a bit to remove gap
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.img_temp_black_teddy),
//                        contentDescription = "",
//                        modifier = Modifier.size(teddySize),
//                        contentScale = ContentScale.Fit,
//                        alpha = (1f - collapseFraction)
//                    )
//                }
//            }
//        }
//
//        // Filter Chips - pinned at bottom
//        LazyRow(
//            modifier = Modifier
//                .padding(start = 16.dp, bottom = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(filters) { filter ->
//                com.sora.sora.features.category.screen.FilterChip(
//                    text = filter,
//                    isSelected = filter == selectedFilter,
//                    onClick = { selectedFilter = filter }
//                )
//            }
//        }
//
//        // Product grid scrollable content
//        LazyVerticalGrid(
//            state = gridState,
//            columns = GridCells.Fixed(2),
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(products) { product ->
//                ProductCard(product,)
//            }
//        }
//    }
//}
//@Composable
//fun com.sora.sora.features.category.screen.FilterChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
//    Surface(
//        modifier = Modifier.clickable(onClick = onClick),
//        shape = RoundedCornerShape(50),
//        color = if (isSelected) Color.White else Color(0xFFF2F2F2),
//        elevation = 2.dp
//    ) {
//        Text(
//            text = text,
//            fontSize = 14.sp,
//            fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
//            color = if (isSelected) Color.Black else Color.Gray,
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//        )
//    }
//}

/**WORKING CODE WITHOUT ANIMATION*/
//@Composable
//fun com.sora.sora.features.category.screen.CategoryDetailScreen() {
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
//                    com.sora.sora.features.category.screen.FilterChip(
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


