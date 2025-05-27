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
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
//@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    // Dummy categories list
    val categories = listOf(
        Category(1, "Toys & Plushies", painterResource(R.drawable.img_temp_categories1), Color(0xFFFFE680)),
        Category(2, "Clothing Products", painterResource(R.drawable.img_temp_categories2), Color(0xFFB0A9F2)),
        Category(3, "Baby Essentials", painterResource(R.drawable.img_temp_categories3), Color(0xFFF6A2DB)),
        Category(4, "Cups & Mugs", painterResource(R.drawable.img_temp_categories4), Color(0xFFB7E9BC)),
        Category(5, "Accessories", painterResource(R.drawable.img_temp_categories4), Color(0xFFD5C2E3))
    )

    // Dummy products list
    val newArrivals = listOf(
        Product(1, "Soft Plush Bear Toys", "KD 1.500", painterResource(R.drawable.img_temp_teddy)),
        Product(2, "Stainless Steel Water Bottle", "KD 1.500", painterResource(R.drawable.img_temp_bottel)),
        Product(3, "Classic Sunglasses", "KD 1.500", painterResource(R.drawable.img_temp_teddy))
    )

    val clothingProducts = listOf(
        Product(4, "Brown Men Full T-shirt", "KD 1.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Baby Denim Blue Jean Outfit", "KD 1.500", painterResource(R.drawable.img_temp_shorts)),
        Product(6, "Brown Men Full T-shirt", "KD 1.500", painterResource(R.drawable.img_temp_tshirt)),
    )

    val towels = listOf(
        Product(6, "Basket of clean towels", "KD 1.500", painterResource(R.drawable.img_temp_clean_towel)),
        Product(7, "Stack of Soft Bath Towels 5 S...", "KD 1.500", painterResource(R.drawable.img_temp_soft_towel)),
        Product(9, "Stack of Soft Bath Towels 5 S...", "KD 1.500", painterResource(R.drawable.img_temp_clean_towel))
    )
    val mugs = listOf(
        Product(6, "Elegant Mug A Black Mug", "KD 1.500", painterResource(R.drawable.img_temp_black_mug)),
        Product(7, "Elegant Mug A Creamy Mug", "KD 1.500", painterResource(R.drawable.img_temp_cream_mug)),
        Product(9, "Elegant Mug A Black Mug", "KD 1.500", painterResource(R.drawable.img_temp_black_mug))
    )

    val discountProducts = listOf(
        Product(6, "Soft Plush Bear Toys", "KD 1.500", painterResource(R.drawable.img_temp_kids_toy)),
        Product(7, "Elegant Mug A Creamy Mug", "KD 1.500", painterResource(R.drawable.img_temp_cream_mug)),
        Product(9, "Stack of Soft Bath Towels 5 S...", "KD 1.500", painterResource(R.drawable.img_temp_soft_towel))
    )

    // Scaffold for general layout
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(scrollState)
//                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            TopBar()
            Spacer(modifier = Modifier.height(16.dp))
            BannerSlider()
            Spacer(modifier = Modifier.height(16.dp))
            CategorySection(categories)
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "New Arrivals", products = newArrivals)
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "Clothings", products = clothingProducts)
            Spacer(modifier = Modifier.height(24.dp))
            OfferCard()
            Spacer(modifier = Modifier.height(24.dp))
            ProductSection(title = "Towels", products = towels)
            Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
            ProductSection(title = "Sora Deals", products = towels)
            Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
            ProductSection(title = "Cups & Mugs", products = mugs)
            Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
            ProductSection(title = "Discount Product", products = discountProducts)
            Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        NavItem("Home", R.drawable.ic_home, R.drawable.ic_white_home),
        NavItem("Category", R.drawable.ic_category, R.drawable.ic_white_cat),
        NavItem("Cart", R.drawable.ic_cart, R.drawable.ic_white_cart),
        NavItem("Favorites", R.drawable.ic_favoritess, R.drawable.ic_white_fav),
        NavItem("Profile", R.drawable.ic_profile, R.drawable.ic_white_profile)
    )

    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding() // safe area padding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem == index
                val weight = if (isSelected) 2f else 1f

                Box(
                    modifier = Modifier
                        .weight(weight)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) PrimaryColor else Color.Transparent)
                        .clickable { selectedItem = index }
                        .padding(horizontal = if (isSelected) 16.dp else 0.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                       if(isSelected) {
                           Icon(
                            painter = painterResource(id = item.icon_selected),
                            contentDescription = item.title,
                            tint =  Color.White,
                            modifier = Modifier.size(22.dp)
                        )}else{
                           Icon(
                               painter = painterResource(id = item.icon),
                               contentDescription = item.title,
                               modifier = Modifier.size(22.dp)
                           )
                        }
                        if (isSelected) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

// Change icon type to Int resource ID
data class NavItem(val title: String, val icon: Int, val icon_selected: Int)


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
            modifier = Modifier.size(80.dp)
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
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp) // small left-right padding
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
fun CategorySection(
    categories: List<Category>,
    onSeeAllClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // This centers children horizontally

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "See All",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

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
}


@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* TODO: Handle click */ }
    ) {
        Box(
//            modifier = Modifier
//                .size(60.dp)
//                .background(category.bgColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = category.icon,
                contentDescription = category.title,
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            category.title,
            fontSize = 10.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(65.dp).align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
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
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "See All",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable {  }
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
fun OfferCard() {
    Image(
        painter = painterResource(id = R.drawable.img_discount_card),
        contentDescription = "Offer Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)  // set your desired height
            .clip(RoundedCornerShape(10.dp)), // give some corner radius from all sides
        contentScale = ContentScale.Crop // or ContentScale.Fit depending on design
    )
}
