package com.sora.sora.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sora.sora.R
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded

// Data classes
data class Category(val id: Int, val title: String, val icon: Painter, val bgColor: Color)
data class Product(val id: Int, val title: String, val price: String, val image: Painter)

// Main Screen
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    // Dummy categories list
    val categories = listOf(
        Category(1, "Toys & Plushies", painterResource(R.drawable.img_temp_item), Color(0xFFFFE680)),
        Category(2, "Clothing Products", painterResource(R.drawable.img_temp_item), Color(0xFFB0A9F2)),
        Category(3, "Baby Essentials", painterResource(R.drawable.img_temp_item), Color(0xFFF6A2DB)),
        Category(4, "Cups & Mugs", painterResource(R.drawable.img_temp_item), Color(0xFFB7E9BC)),
        Category(5, "Accessories", painterResource(R.drawable.img_temp_item), Color(0xFFD5C2E3))
    )

    // Dummy products list
    val newArrivals = listOf(
        Product(1, "Soft Plush Bear Toy", "KD 1.500", painterResource(R.drawable.img_temp_item)),
        Product(2, "Stainless Steel Water Bottle", "KD 1.500", painterResource(R.drawable.img_temp_item)),
        Product(3, "Classic Sunglasses", "KD 1.500", painterResource(R.drawable.img_temp_item))
    )

    val clothingProducts = listOf(
        Product(4, "Brown Men Full T-shirt", "KD 1.500", painterResource(R.drawable.img_temp_item)),
        Product(5, "Baby Denim Blue Jean Outfit", "KD 1.500", painterResource(R.drawable.img_temp_item))
    )

    val towels = listOf(
        Product(6, "Basket of clean towels", "KD 1.500", painterResource(R.drawable.img_temp_item)),
        Product(7, "Stack of Soft Bath Towels 5 S...", "KD 1.500", painterResource(R.drawable.img_temp_item))
    )

    // Scaffold for general layout
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TopBar()
            Spacer(modifier = Modifier.height(16.dp))
            BannerSlider()
            Spacer(modifier = Modifier.height(16.dp))
            CategoryRow(categories)
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "New Arrivals", products = newArrivals)
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "Clothings", products = clothingProducts)
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "Towels", products = towels)
            Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
        }
    }
}

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        val items = listOf(
            NavItem("Home", Icons.Filled.Home),
            NavItem("Categories", Icons.Filled.ShoppingCart),
            NavItem("Cart", Icons.Filled.ShoppingCart),
            NavItem("Favorites", Icons.Filled.FavoriteBorder),
            NavItem("Profile", Icons.Filled.Person)
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = null,  // No text label, like your design
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

data class NavItem(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_sora_logo),
            contentDescription = "Sora Logo",
            modifier = Modifier.size(40.dp)
        )

        Row {
            IconButton(onClick = { /* TODO: Search Action */ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* TODO: Notification Action */ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_notification),
                    contentDescription = "Notification",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSlider(

) {
    val pagerState = rememberPagerState()
    val bannerImages = listOf(R.drawable.img_discount_card, R.drawable.img_discount_card, R.drawable.img_discount_card)

    // Auto-scroll logic
    LaunchedEffect(key1 = pagerState.currentPage) {
        kotlinx.coroutines.delay(3000) // delay 3 seconds
        val nextPage = (pagerState.currentPage + 1) % bannerImages.size
        pagerState.animateScrollToPage(nextPage)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = bannerImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) { page ->
            Image(
                painter = painterResource(bannerImages[page]),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color(0xFF86544E),
            inactiveColor = Color.LightGray,
            indicatorWidth = 25.dp,
            indicatorHeight = 6.dp,
            spacing = 6.dp
        )
    }
}


@Composable
fun CategoryRow(categories: List<Category>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categories.forEach { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* TODO: Handle click */ }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(category.bgColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = category.icon,
                contentDescription = category.title,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            category.title,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(65.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProductSection(title: String, products: List<Product>) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                "See All",
                color = PrimaryColor,
                modifier = Modifier.clickable { /* TODO: See all */ },
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.size(width = 140.dp, height = 200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 12.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 8.dp)
            ) {
                Text(
                    product.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    product.price,
                    fontSize = 12.sp,
                    color = PrimaryColor,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            IconButton(
                onClick = { /* TODO: Favorite/Like action */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Like",
                    tint = PrimaryColor
                )
            }

            IconButton(
                onClick = { /* TODO: Add to cart action */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(28.dp)
                    .background(PrimaryColorFaded, shape = RoundedCornerShape(10.dp))
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = PrimaryColor
                )
            }
        }
    }
}
