package com.sora.sora.features.dashboard

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.sora.sora.core.AppTexts
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.widgets.ProductSection
import com.sora.sora.ui.theme.PrimaryColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.ui.input.pointer.pointerInput
import com.google.accompanist.pager.PagerState
import com.sora.sora.core.controller.GlobalController
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.features.category.CategoryDetailModel
import java.net.URLEncoder

// Data classes
data class Category(val id: Int, val title: String, val icon: Painter, val bgColor: Color)
data class Product(val id: Int, val title: String, val price: String, val discountPercent: Int, val oldPrice: String, val image: Painter)

//@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    var categoryList =  listOf(
        AppTexts.soraDeals,
        AppTexts.clothing,
        AppTexts.towels,
        AppTexts.mugs,
        AppTexts.disountProducts
    )

    // Dummy categories list
    val categories = listOf(
        Category(1, "Toys & Plushies", painterResource(R.drawable.ic_temp_toy), Color(0xFFFFE680)),
        Category(2, "Clothing Products", painterResource(R.drawable.ic_temp_shirts), Color(0xFFB0A9F2)),
        Category(3, "Baby Essentials", painterResource(R.drawable.ic_temp_baby_essentials), Color(0xFFF6A2DB)),
        Category(4, "Cups & Mugs", painterResource(R.drawable.ic_temp_cup), Color(0xFFB7E9BC)),
//        Category(5, "Accessories", painterResource(R.drawable.ic_temp_), Color(0xFFD5C2E3))
    )

    // Dummy products list
    val newArrivals = listOf(
        Product(1, "Soft Plush Bear Toys", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_teddy)),
        Product(2, "Stainless Steel Water Bottle", "1.500", discountPercent = 20, oldPrice = "2.500",painterResource(R.drawable.img_temp_bottel)),
        Product(3, "Classic Sunglasses", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_teddy))
    )

    val clothingProducts = listOf(
        Product(4, "Brown Men Full T-shirt", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Baby Denim Blue Jean Outfit", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_shorts)),
        Product(6, "Brown Men Full T-shirt", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_tshirt)),
    )

    val towels = listOf(
        Product(6, "Basket of clean towels", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_clean_towel)),
        Product(7, "Stack of Soft Bath Towels 5 S...","1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_soft_towel)),
        Product(9, "Stack of Soft Bath Towels 5 S...", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_clean_towel))
    )
    val mugs = listOf(
        Product(6, "Elegant Mug A Black Mug", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_black_mug)),
        Product(7, "Elegant Mug A Creamy Mug","1.500", discountPercent = 20, oldPrice = "2.500",  painterResource(R.drawable.img_temp_cream_mug)),
        Product(9, "Elegant Mug A Black Mug", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_black_mug))
    )

    val discountProducts = listOf(
        Product(6, "Soft Plush Bear Toys", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_kids_toy)),
        Product(7, "Elegant Mug A Creamy Mug", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_cream_mug)),
        Product(9, "Stack of Soft Bath Towels 5 S...", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_soft_towel))
    )

    // Scaffold for general layout
    Scaffold(
//        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(scrollState)
        ) {
            WelcomeTopBar()
            Spacer(modifier = Modifier.height(8.dp))
            BannerSlider()
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Spacer(modifier = Modifier.height(24.dp))
                CategorySection(categories)
                Spacer(modifier = Modifier.height(24.dp))
                ProductSection(title = AppTexts.soraDeals, products = newArrivals,categoryList = categoryList)
                Spacer(modifier = Modifier.height(24.dp))
                ProductSection(title = "Clothings", products = clothingProducts,categoryList = categoryList)
                Spacer(modifier = Modifier.height(24.dp))
                OfferCard()
                Spacer(modifier = Modifier.height(24.dp))
                ProductSection(title = "Towels", products = towels,categoryList = categoryList)
                Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
                ProductSection(title = AppTexts.soraDeals, products = towels,categoryList = categoryList)
                Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
                ProductSection(title = "Cups & Mugs", products = mugs,categoryList = categoryList)
                Spacer(modifier = Modifier.height(48.dp)) // extra bottom padding
                ProductSection(title = "Discount Product", products = discountProducts,categoryList = categoryList)
                Spacer(modifier = Modifier.height(65.dp)) // extra bottom padding
            }
        }
    }
}


// Change icon type to Int resource ID
data class NavItem(val title: String, val icon: Int, val icon_selected: Int)


@Composable
fun WelcomeTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_sora_logo),
            contentDescription = "Sora Logo",
            modifier = Modifier.size(80.dp)
        )

//        Row {
//            IconButton(onClick = { /* TODO: Search Action */ }) {
//                Icon(
//                    painter = painterResource(R.drawable.ic_search),
//                    contentDescription = "Search",
//                    tint = Color.Unspecified
//                )
//            }
//            IconButton(onClick = { /* TODO: Notification Action */ }) {
//                Icon(
//                    painter = painterResource(R.drawable.ic_notification),
//                    contentDescription = "Notification",
//                    tint = Color.Unspecified
//                )
//            }
//        }
    }
}

/**Working without smothness and proper indicator2*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSlider(

) {
    val bannerImages = listOf(
        R.drawable.img_temp_slider,
        R.drawable.img_temp_slider,
        R.drawable.img_temp_slider
    )
    val pagerState = rememberPagerState()

    // Only ONE coroutine that never interrupts in-progress scrolls
    LaunchedEffect(pagerState) {
        while (true) {
            if (!pagerState.isScrollInProgress) {
                kotlinx.coroutines.delay(2000)
                // If still not scrolling and on same page, animate
                if (!pagerState.isScrollInProgress) {
                    val nextPage = (pagerState.currentPage + 1) % bannerImages.size
                    pagerState.animateScrollToPage(
                        page = nextPage,
                        animationSpec = tween(
                            durationMillis = 5000,   // 800ms for quick yet smooth
                            easing = FastOutSlowInEasing // Makes slide look ‘material’
                        )
                    )
                }
            }
            // Check every 100ms to avoid busy looping
            kotlinx.coroutines.delay(100)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = bannerImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(bannerImages[page]),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // (Your overlay here)

                // Text Overlay
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 32.dp)
                        .align(Alignment.Center)
                ) {
                    CustomMontserratText(
                        text = "Effortless Style, Timeless Elegance",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .widthIn(max = 200.dp)
                    )
                    CustomMontserratText(
                        text = "Limited-Time Sale! Get up to 30% off on selected items.",
                        color = Color.Black,
                        fontSize = 14.sp,
                        maxLines = 3,
                        modifier = Modifier.padding(bottom = 16.dp).widthIn(max = 200.dp)
                    )
                    Button(
                            onClick = {

                                val categoryDetailModel = CategoryDetailModel(
                                    title = "Toys",
                                    themeColor = "#FFFADA7A"  // e.g., "#FFFADA7A"
                                )

                                // URL encode both the title and themeColor
                                val encodedTitle = URLEncoder.encode(categoryDetailModel.title, "UTF-8")
                                val encodedThemeColor = URLEncoder.encode(categoryDetailModel.themeColor, "UTF-8")

                                Log.d("MyTag", "CategoryCard: ------------------${categoryDetailModel.title}")

                                // Pass the encoded title and themeColor in the navigation URL
                                navController.navigate("${Dest.CategoryDetailScreen::class.toRoute()}?title=$encodedTitle&themeColor=$encodedThemeColor")

                            },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(PrimaryColor),
                    contentPadding = PaddingValues(0.dp), // Remove internal padding
                    modifier = Modifier
                        .width(105.dp) // Make button stretch across
                        .height(28.dp) // Adjust button height as needed

                    ) {
                    Text(
                        text = "Explore Now",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 14.sp,
//                        modifier = Modifier.fillMaxSize(), // Ensure text is centered within button
                        textAlign = TextAlign.Center
                    )
                }

                }


            }
        }
        Spacer(Modifier.height(12.dp))
        FancyPagerIndicator(
            pagerState = pagerState,
            pageCount = bannerImages.size,
            activeColor = Color(0xFF86544E),
            inactiveColor = Color.LightGray
        )
    }
}



@Composable
fun FancyPagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    activeColor: Color = Color(0xFF86544E),
    inactiveColor: Color = Color.LightGray,
    modifier: Modifier = Modifier.padding(horizontal = 16.dp)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = pagerState.currentPage == index
            val animatedWidth by animateDpAsState(targetValue = if (isSelected) 25.dp else 8.dp)
            val animatedHeight by animateDpAsState(targetValue = if (isSelected) 6.dp else 6.dp)
            val animatedColor by animateColorAsState(targetValue = if (isSelected) activeColor else inactiveColor)
            val animatedShape = if (isSelected) RoundedCornerShape(4.dp) else RoundedCornerShape(4.dp)

            Box(
                modifier = Modifier
                    .width(animatedWidth)
                    .height(animatedHeight)
                    .clip(animatedShape)
                    .background(animatedColor)
            )
            if (index < pageCount - 1) {
                Spacer(Modifier.width(5.dp))
            }
        }
    }
}


/**Working without smothness and proper indicator1*/
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun BannerSlider(
//    modifier: Modifier = Modifier
//        .padding(horizontal = 8.dp) // small left-right padding
//) {
//    val pagerState = rememberPagerState()
//    val bannerImages = listOf(R.drawable.img_temp_slider, R.drawable.img_temp_slider, R.drawable.img_temp_slider) // your images
//
//    // Auto-scroll logic
//    LaunchedEffect(key1 = pagerState.currentPage) {
//        kotlinx.coroutines.delay(3000) // delay 3 seconds
//        val nextPage = (pagerState.currentPage + 1) % bannerImages.size
//        pagerState.animateScrollToPage(nextPage)
//    }
//
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Horizontal Pager for the banner slider
//        HorizontalPager(
//            count = bannerImages.size,
//            state = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp) // Adjust the height of the slider
//        ) { page ->
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            ) {
//                // Use ContentScale.Crop to ensure the images fill the area completely without stretching
//                Image(
//                    painter = painterResource(bannerImages[page]),
//                    contentDescription = "Banner Image",
//                    contentScale = ContentScale.Crop, // Ensure the image fits within the box
//                    modifier = Modifier
//                        .fillMaxSize() // Ensure it fills the container completely
//                )
//
//                // Text Overlay
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp)
//                        .padding(top = 32.dp)
//                        .align(Alignment.Center)
//                ) {
//                    CustomMontserratText(
//                        text = "Effortless Style, Timeless Elegance",
//                        color = Color.Black,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        maxLines = 3,
//                        modifier = Modifier
//                            .padding(bottom = 8.dp)
//                            .widthIn(max = 200.dp)
//                    )
//                    CustomMontserratText(
//                        text = "Limited-Time Sale! Get up to 30% off on selected items.",
//                        color = Color.Black,
//                        fontSize = 14.sp,
//                        maxLines = 3,
//                        modifier = Modifier.padding(bottom = 16.dp).widthIn(max = 200.dp)
//                    )
//                    Button(
//                            onClick = { /* Handle button click */ },
//                    shape = RoundedCornerShape(25.dp),
//                    colors = ButtonDefaults.buttonColors(PrimaryColor),
//                    contentPadding = PaddingValues(0.dp), // Remove internal padding
//                    modifier = Modifier
//                        .width(105.dp) // Make button stretch across
//                        .height(28.dp) // Adjust button height as needed
//                    ) {
//                    Text(
//                        text = "Explore Now",
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White,
//                        fontSize = 14.sp,
////                        modifier = Modifier.fillMaxSize(), // Ensure text is centered within button
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Pager indicator
//        HorizontalPagerIndicator(
//            pagerState = pagerState,
//            activeColor = Color(0xFF86544E),
//            inactiveColor = Color.LightGray,
//            indicatorWidth = 25.dp,
//            indicatorHeight = 6.dp,
//            spacing = 6.dp
//        )
//    }
//}
//


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
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomMontserratText(
                text = AppTexts.categories,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Black,
            )
            CustomMontserratText(
                text = AppTexts.seeAll,
                color = PrimaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    GlobalController.updateSelectedIndex(1)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .horizontalScroll(rememberScrollState()),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            categories.forEach { category ->
                Spacer(modifier = Modifier.width(14.dp))
                CategoryItem(category)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategorySection(){
    val categories = listOf(
        Category(1, "Toys & Plushies", painterResource(R.drawable.ic_temp_toy), Color(0xFFFFFAF1)),
        Category(2, "Clothing Products", painterResource(R.drawable.img_temp_categories2), Color(0xFFF9F8FF)),
        Category(3, "Baby Essentials", painterResource(R.drawable.img_temp_categories3), Color(0xFFFEF8F8)),
        Category(4, "Cups & Mugs", painterResource(R.drawable.img_temp_categories4), Color(0xFFF6FFF2)),
        Category(5, "Accessories", painterResource(R.drawable.img_temp_categories4), Color(0xFFFFF7F7))
    )

//    CategorySection(categories = categories, onSeeAllClick = {})

    CategoryItem(categories[0])
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {

                val categoryDetailModel = CategoryDetailModel(
                    title = "Toys",
                    themeColor = "#FFFADA7A"  // e.g., "#FFFADA7A"
                )

                // URL encode both the title and themeColor
                val encodedTitle = URLEncoder.encode(categoryDetailModel.title, "UTF-8")
                val encodedThemeColor = URLEncoder.encode(categoryDetailModel.themeColor, "UTF-8")

                Log.d("MyTag", "CategoryCard: ------------------${categoryDetailModel.title}")

                // Pass the encoded title and themeColor in the navigation URL
                navController.navigate("${Dest.CategoryDetailScreen::class.toRoute()}?title=$encodedTitle&themeColor=$encodedThemeColor")

        })
        }
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(category.bgColor.copy(alpha = 0.15f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = category.icon,
                contentDescription = category.title,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomMontserratText(
            category.title,
            fontSize = 10.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(65.dp).align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OfferCard() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(vFactor(194))
            .clip(RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.img_temp_discount_banner,),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp))
        )
        // (Your overlay here)

        // Text Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 50.dp)
                .align(Alignment.Center)
        ) {
            CustomMontserratText(
                text = "Adorable Plush Toys",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .widthIn(max = 150.dp)
            )
            CustomMontserratText(
                text = "Up to 30% off!",
                color = Color.Black,
                fontSize = 14.sp,
                maxLines = 3,
                modifier = Modifier.padding(bottom = 16.dp).widthIn(max = 200.dp)
            )
            Button(
                onClick = {

                    val categoryDetailModel = CategoryDetailModel(
                        title = "Toys",
                        themeColor = "#FFFADA7A"  // e.g., "#FFFADA7A"
                    )

                    // URL encode both the title and themeColor
                    val encodedTitle = URLEncoder.encode(categoryDetailModel.title, "UTF-8")
                    val encodedThemeColor = URLEncoder.encode(categoryDetailModel.themeColor, "UTF-8")

                    Log.d("MyTag", "CategoryCard: ------------------${categoryDetailModel.title}")

                    // Pass the encoded title and themeColor in the navigation URL
                    navController.navigate("${Dest.CategoryDetailScreen::class.toRoute()}?title=$encodedTitle&themeColor=$encodedThemeColor")

                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(PrimaryColor),
                contentPadding = PaddingValues(0.dp), // Remove internal padding
                modifier = Modifier
                    .width(105.dp) // Make button stretch across
                    .height(28.dp) // Adjust button height as needed
            ) {
                CustomMontserratText(
                    text = "Explore Now",
                    fontWeight = FontWeight.W500,
                    color = Color.White,
                    fontSize = 14.sp,
//                        modifier = Modifier.fillMaxSize(), // Ensure text is centered within button
                    textAlign = TextAlign.Center
                )
            }

        }


    }
}
