package com.sora.sora.features.category.screen

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.sora.sora.R
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.core.customText.CustomMontserratText

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.features.dashboard.Product
import com.sora.sora.core.widgets.ProductCard
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.SecondaryColor100
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sora.sora.core.controller.GlobalController
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.features.category.CategoryDetailModel
import com.sora.sora.features.dashboard.FancyPagerIndicator


/** Working but there is no bottom bar**/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryDetailScreen(categoryDetailModel : CategoryDetailModel) {

//    var themeColor = Color(0xFFFADA7A)
    val color1 = categoryDetailModel.themeColor1
    val color2 = categoryDetailModel.themeColor2

    // If themeColor is a hex string (e.g., "#FFFADA7A"), use it as a color
    val themeColor = Color(android.graphics.Color.parseColor(color1))
    val themeColor2 = Color(android.graphics.Color.parseColor(color2))

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
    val maxTeddySize = 100.dp
    val minTeddySize = 45.dp
    val maxTeddyTopPadding = 85.dp
    val minTeddyTopPadding = 20.dp
    val maxTeddyStartPadding = 0.dp  // Initially at the center horizontally
    val minTeddyStartPadding = -115.dp // Move it to the left

    val maxTitleStartPadding = 0.dp
    val minTitleStartPadding = 20.dp

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

    // Animated Spacer height
    val spacerHeight by animateDpAsState(45.dp - (12.dp * collapseFraction)) // Spacer height shrinks as we scroll

    val scrollState = rememberScrollState()

    Log.d("MyTag", "--------${categoryDetailModel.title}---")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 40.dp)) {
        val topPadding = 22.dp

        Column(
            Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            themeColor.copy(alpha = 0.8f),        // Opaque at the top with 50% opacity
//                        Color(0xFFFFFFFF),        // Opaque at the top
                            themeColor.copy(alpha = 0f),  // Partially transparent mid-way
                        ),
                    )
                )
                .verticalScroll(scrollState)
        ){

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding)) {
                    // Top bar - fixed height with top padding

                    Row(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Back Button
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                                .background(themeColor2.copy(alpha = 0.1f))

                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = themeColor2,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(30.dp)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                        }

                        Spacer(Modifier.weight(1f))
                        // Animated title
                        CustomMontserratText(
//                            text = "Toys & Plushies",
                            text = categoryDetailModel.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3A260C),
                            modifier = Modifier
                                .padding(start = titleStartPadding)
                                .graphicsLayer {
                                    alpha = 1f // Optionally animate alpha
                                }
                        )
                        Spacer(Modifier.weight(1f))
                        // Cart
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                                .background(themeColor.copy(alpha = 0.1f))
                        ) {
                            Icon(painter = painterResource(R.drawable.ic_empty_order), tint = themeColor2 ,modifier = Modifier
                                .align(Alignment.Center)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = { /* No animation on press */ },
                                        onTap = {
                                            GlobalController.updateSelectedIndex(2)
                                            navController.navigate(Dest.DashBoardScreen::class.toRoute())
                                        }
                                    )
                                }
                                .size(30.dp),
                                contentDescription = "Cart",
                                )
                        }
                    }
                    // Teddy Icon with animated transition to the left of the title
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth() // Ensure it takes up the full width for centering
                            .offset(
                                x = teddyStartPadding,
                                y = teddyTopPadding
                            ) // Horizontal movement
                            .graphicsLayer {
                                scaleX = teddyScale
                                scaleY = teddyScale
                                alpha = teddyAlpha
                            }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cat_toy),
                            contentDescription = "Teddy",
                            tint = themeColor2,
                            modifier = Modifier.size(teddySize)
                        )
                    }
                }
            }

            // Dynamic Spacer height as we scroll
            Spacer(modifier = Modifier.height(spacerHeight)) // This spacer height shrinks as we scroll

            // Filter Chips - pinned at bottom
            LazyRow(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    FilterChips(
                        text = filter,
                        isSelected = filter == selectedFilter,
                        themeColor = themeColor,
                        onClick = { selectedFilter = filter }
                    )
                }
            }

            CategoryResultRow(resultCount = products.size)
            //            CategoryResultRow(resultCount = products.size, onApplyFilter = { _, _, _, _ -> })
            Spacer(modifier = Modifier.height(10.dp))

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
                var qty by remember { mutableIntStateOf(0) }
                ProductCard(product = product,  onFavorite = { /* … */ },
                    onShare = { /* … */ },
                    onAddToCart = { qty++ },
                    onRemoveFromCart = { if (qty > 0) qty-- },
                    color = themeColor,
                    quantity = qty)
            }

        }
    }
}

@Composable
fun FilterChips(text: String, isSelected: Boolean,themeColor : Color, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        onClick()
                        awaitRelease()
                    }
                )
            },
        shape = RoundedCornerShape(40),
        color = if (isSelected) themeColor else  Color(0xFF8A4C3D).copy(alpha = 0.1f),
    ) {
        CustomMontserratText(
            text = text,
            fontSize = 12.sp,
            fontWeight = if(isSelected) FontWeight.SemiBold else FontWeight.W500,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

data class CategoryModel(val title: String, val bgColor: String, val img: ImageBitmap)

@Composable
fun ItemSlider(
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
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
        modifier = modifier.fillMaxWidth(),
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
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .widthIn(max = 200.dp)
                    )
                    Button(
                        onClick = { /* Handle button click */ },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(PrimaryColor),
                        contentPadding = PaddingValues(0.dp), // Remove internal padding
                        modifier = Modifier
                            .width(105.dp) // Make button stretch across
                            .height(28.dp) // Adjust button height as needed
                    ) {
                        Text(
                            text = "Explore Now",
                            fontWeight = FontWeight.Bold,
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
