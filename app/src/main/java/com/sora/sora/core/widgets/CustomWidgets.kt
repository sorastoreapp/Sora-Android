package com.sora.sora.core.widgets

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.features.dashboard.Product
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.navigations.NavigationManager
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.sora.sora.R
import com.sora.sora.core.AppTexts
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.temp.SeeAllModel
import com.sora.sora.ui.theme.ProductCardColor
import kotlinx.coroutines.delay


//
//@Composable
//fun ProductSection(title: String, products: List<Product>) {
//
//    Column {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = title,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp,
//                color = Color.Black
//            )
//            Text(
//                text = "See All",
//                color = Color.Gray,
//                fontSize = 12.sp,
//                modifier = Modifier.clickable {  }
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//            items(products) { product ->
//                ProductCard(product = product,)
//            }
//        }
//    }
//}

@Composable
fun ProductSection(title: String, products: List<Product>, categoryList: List<String> = emptyList()) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomMontserratText(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Black,
            )
            CustomMontserratText(
                text = AppTexts.seeAll,
                color = PrimaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {

                            val seeAllModel = SeeAllModel(title = title, list = categoryList.takeIf { it.isNotEmpty() } ?: emptyList())
                            val encodedList = seeAllModel.list.joinToString(",")  // Encoding the list as a string


                            Log.d("MyTag", "-----------------${encodedList}")
                            navController.navigate("${Dest.SeeAllProductScreen::class.toRoute()}?title=${seeAllModel.title}&list=$encodedList")
                        }
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))



        LazyRow(contentPadding = PaddingValues(start = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(products) { product ->
                var qty by remember { mutableIntStateOf(0) }
                ProductCard(product = product,  onFavorite = { /* … */ },
                    onShare = { /* … */ },
                    onAddToCart = { qty++ },
                    onRemoveFromCart = { if (qty > 0) qty-- },
                    quantity = qty)
            }
            item{
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }

}



//@Composable
//fun ProductCard(product: Product,) {
//    Card(
//        modifier = Modifier
//            .size(width = 150.dp, height = 220.dp)
//            .clickable {
//                // Navigate to ItemDetailScreen when card is clicked
//                NavigationManager.navigateTo(Dest.ItemDetailScreen::class.toRoute())
//            },
//        shape = RoundedCornerShape(15.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            // Product Image
//            Image(
//                painter = product.image,
//                contentDescription = product.title,
//                modifier = Modifier
//                    .size(110.dp)
//                    .align(Alignment.TopCenter)
//                    .offset(y = 10.dp)
//            )
//            Box(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                // Favorite Icon Box
//                Box(
//                    modifier = Modifier
//                        .padding(start = 8.dp, top = 8.dp)
//                        .align(Alignment.TopStart)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(28.dp)
//                            .background(PrimaryColorFaded, shape = CircleShape)
//                            .clickable { /* Handle favorite click */ },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.FavoriteBorder,
//                            contentDescription = "Favorite",
//                            tint = PrimaryColor,
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//                }
//            }
//
//            // Title & Price Column
//            Column(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(start = 12.dp, top = 130.dp, end = 12.dp)
//            ) {
//                Text(
//                    text = product.title,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 14.sp,
//                    maxLines = 2,
//                    textAlign = TextAlign.Center,
//                    overflow = TextOverflow.Ellipsis,
//                    color = Color.Black
//                )
//                Text(
//                    text = product.price,
//                    fontSize = 13.sp,
//                    color = PrimaryColor,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//            }
//
//            // Add button bottom right
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(bottom = 8.dp, end = 8.dp)
//                    .size(width = 36.dp, height = 32.dp)
//                    .background(
//                        color = PrimaryColor,
//                        shape = RoundedCornerShape(10.dp)
//                    )
//                    .clickable { /* Handle add to cart click */ },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Add,
//                    contentDescription = "Add",
//                    tint = Color.White,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//        }
//    }
//}

/** WORKED WITHOUT EXPANDABLE CART CONTROLLER */
//@Composable
//fun ProductCard(
//    product: Product,
//    onFavorite: () -> Unit = {},
//    onShare: () -> Unit = {},
//    onAddToCart: () -> Unit = {},
//    onRemoveFromCart: () -> Unit = {}
//) {
//    val cardWidth = 170.dp
//    val cardHeight = 240.dp
//
//    Card(
//        modifier = Modifier
//            .width(cardWidth)
//            .height(cardHeight),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(Color.White),
//        elevation = CardDefaults.cardElevation(2.dp),
//        border = BorderStroke(1.dp, Color(0xFFEADFD0))  // light border
//    ) {
//
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // TOP PRODUCT SECTION
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(cardHeight * 0.6f)
//                    .background(Color(0xFFFDF6F0))    // cartBgColor
//            ) {
//                // product image
//                Image(
//                    painter = product.image,
//                    contentDescription = product.title,
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .height(110.dp)
//                        .width(97.dp)
//                )
//
//                // Favourite button
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(30.dp)
//                        .background(Color(0xFFF2ECE7), CircleShape)
//                        .align(Alignment.TopStart)
//                        .clickable { onFavorite() },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null, tint = PrimaryColor, modifier = Modifier.padding(7.dp))
//                }
//
//                // Share button
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(30.dp)
//                        .background(Color(0xFFF2ECE7), CircleShape)
//                        .align(Alignment.TopEnd)
//                        .clickable { onShare() },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = PrimaryColor , modifier = Modifier.padding(7.dp))
//                }
//
//                // Cart button bottom-right
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(27.dp)
//                        .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
//                        .align(Alignment.BottomEnd)
//                        .clickable { onAddToCart() },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
//                }
//            }
//
//            // BOTTOM TEXT SECTION
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
//            ) {
//                CustomMontserratText(
//                    text = product.title,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    fontWeight = FontWeight.W500,
//                    fontSize = 14.sp,
//                    color = Color(0xFF383B3E)
//                )
//
//                Spacer(modifier = Modifier.height(4.dp))
//
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(
//                        text = "KD ${product.price}",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp,
//                        color = PrimaryColor
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    CustomMontserratText(
//                        text = product.oldPrice,
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        textDecoration = TextDecoration.LineThrough
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    CustomMontserratText(
//                        text = "${product.discountPercent}%",
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFFD93025)
//                    )
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    product: Product,
    onFavorite: () -> Unit = {},
    onShare: () -> Unit = {},
    onAddToCart: () -> Unit = {},
    onRemoveFromCart: () -> Unit = {},
    isFavoriteScreen : Boolean = false,
    color : Color = PrimaryColor,
    quantity: Int = 0 // <- add this (default keeps old calls working)
) {
    val cardWidth = 190.dp
    val cardHeight = 263.dp
    var isFavourite by remember { mutableStateOf(isFavoriteScreen) }
    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        navController.navigate(Dest.ItemDetailScreen::class.toRoute())
                    }
                )
            }
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp),
        border = BorderStroke(1.dp, Color(0xFFEADFD0))
    ) {
        Column(Modifier.fillMaxSize()) {
            // TOP
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight * 0.6f)
                    .background(ProductCardColor)
            ) {
                Image(
                    painter = product.image,
                    contentDescription = product.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(120.dp)
                        .fillMaxWidth()
                )

                // Heart
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                        .background(Color(0xFFF2ECE7), CircleShape)
                        .align(Alignment.TopStart)
                        .clickable { onFavorite() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(if(isFavourite)Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null, tint = PrimaryColor,
                        modifier = Modifier
                            .padding(7.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        onFavorite()
                                        isFavourite = !isFavourite
                                    }
                                )
                            }
                    )


                }

                // Share
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                        .background(Color(0xFFF2ECE7), CircleShape)
                        .align(Alignment.TopEnd)
                        .clickable { onShare() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null, tint = PrimaryColor, modifier = Modifier.padding(7.dp))
                }

                // *** Animated [- qty +] control ***
//                AnimatedAddToCart(
//                    quantity = quantity,
//                    onAdd = onAddToCart,
//                    onRemove = onRemoveFromCart,
//                    color = color,
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .padding(8.dp)
//                )
            }

            // BOTTOM
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
            ) {
                CustomMontserratText(
                    text = product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF383B3E)
                )

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomMontserratText("KD ${product.price}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PrimaryColor)
                    Spacer(Modifier.width(4.dp))
                    CustomMontserratText(product.oldPrice, fontSize =  12.sp, color = Color.Gray, textDecoration = TextDecoration.LineThrough)
                    Spacer(Modifier.width(4.dp))
                    CustomMontserratText("${product.discountPercent}%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD93025))
                }
            }
        }
    }
}

/** CODE IN TESTING */
@Composable
fun AnimatedAddToCart(
    quantity: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = PrimaryColor,
    autoCollapseMillis: Long = 2000L,
    maxQty: Int = 5,
    minQty: Int = 0,
) {
    var expanded by remember { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f, label = "press-scale")


    // Collapse when qty hits min
    LaunchedEffect(quantity) {
        if (quantity <= minQty) expanded = false
    }

    // Auto-collapse after 4s when expanded and qty > 0
    LaunchedEffect(expanded, quantity) {
        if (expanded && quantity > minQty) {
            delay(autoCollapseMillis)
            expanded = false
        }
    }

    Box(modifier = modifier.graphicsLayer { scaleX = scale; scaleY = scale }) {

        // EXPANDED [- qty +]
        AnimatedVisibility(
            visible = expanded, // <- only when expanded
            enter = expandHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
            ) + fadeIn(),
            exit = shrinkHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
            ) + fadeOut()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .height(36.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "Decrease",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            if (quantity > minQty) {
                                pressed = true
                                onRemove()
                                pressed = false
                            }
                        }
                )

                Text(
                    text = quantity.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Increase",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            if (quantity < maxQty) {
                                if (!expanded) expanded = true
                                pressed = true
                                onAdd()
                                pressed = false
                            }
                        }
                )
            }
        }

        // COLLAPSED (quantity pill) when qty > 0
        AnimatedVisibility(
            visible = !expanded && quantity > minQty,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp)
                    .clickable { expanded = true }, // tap to reopen controls
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = quantity.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // COLLAPSED (+) when qty == 0
        AnimatedVisibility(
            visible = !expanded && quantity == minQty,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .clickable {
                        if (quantity < maxQty) {
                            expanded = true
                            onAdd()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    }
}










