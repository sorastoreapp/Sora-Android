package com.sora.sora.features.common.view.screen

import com.sora.sora.features.dashboard.Product


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.controller.GlobalController
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.temp.SeeAllModel
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.widgets.ProductCard
import com.sora.sora.features.category.screen.CategoryResultRow
import com.sora.sora.features.category.screen.FilterChips
import com.sora.sora.features.favourites.widgets.FavoriteEmptyStateView
import com.sora.sora.ui.theme.PrimaryColor100


@Composable
fun SeeAllProductScreen(seeAllModel : SeeAllModel) {
    val favProductList = remember {
        mutableStateListOf<Product>()
    }



    var themeColor = PrimaryColor
//    val filters = listOf("All", "Soft & Cuddly", "Action & Adventure", "Educational", "Outdoor")
    val filters = seeAllModel.list


    val products = listOf(
        Product(1, "Soft Plush Bear Toy", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_teddy)),
        Product(2, "Kids colorful car toy", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(3, "Multiple Kids toys Collection", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(4, "Blue teddy bear wearing", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(5, "Colorful building blocks castle", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy)),
        Product(6, "Adorable teddy bear loves", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_multi_toy))
    )

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
                .padding(vertical = 8.dp)
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp,)
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Button
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(PrimaryColor100)
                        .clickable {
                            navController.popBackStack()
                        }

                ) {
                   Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(25.dp)
                    )
                }

                Spacer(Modifier.weight(1f))



                CustomMontserratText(
                    text = seeAllModel.title,
//                    text = "${filters.size}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )

//
//                Image(
//                    painter = painterResource(R.drawable.ic_sora_logo),
//                    colorFilter = tint(PrimaryColor),
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .size(70.dp),
//                    contentDescription = "Cart",
//                )
//



                Spacer(Modifier.weight(1f))
                // Cart
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(PrimaryColor100)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    GlobalController.updateSelectedIndex(2)
                                 navController.navigate(Dest.DashBoardScreen::class.toRoute())
                                }
                            )}

                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_empty_order),
                        colorFilter = tint(PrimaryColor),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(5.dp)
                            .size(25.dp),
                        contentDescription = "Cart",
                    )
                }
            }

//            Row(
//                Modifier
//                    .padding(horizontal = 16.dp)
//                    .fillMaxWidth()
//                    .padding(top = 5.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Back Button
//                Box(
//                    modifier = Modifier
//                        .size(35.dp)
//                        .clip(CircleShape)
//                        .background(PrimaryColor100)
//                        .clickable {
//                            navController.popBackStack()
//                        }
//
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "Back",
//                        tint = PrimaryColor,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .size(25.dp)
//                    )
//                }
//
//                Spacer(Modifier.weight(1f))
//
//
//
//                CustomMontserratText(
//                    text = "See All",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 10.dp),
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                )
//
//
//                Spacer(Modifier.weight(1f))
//                // Cart
//                Box(
//                    modifier = Modifier
//                        .size(35.dp)
//                        .clip(CircleShape)
//                        .background(PrimaryColor100)
//
//                ) {
//                    Image(
//                        painter = painterResource(R.drawable.ic_empty_order),
//                        colorFilter = tint(PrimaryColor),
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .padding(5.dp)
//                            .size(25.dp),
//                        contentDescription = "Cart",
//                    )
//                }
//            }



            if( filters[0] != "" ) {Column {
                var selectedFilter by remember { mutableStateOf(filters[0]) }

                Spacer(modifier = Modifier.height(16.dp))

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

                CategoryResultRow(resultCount = products.size, horizontalPadding = 16.dp)
                //            CategoryResultRow(resultCount = products.size, onApplyFilter = { _, _, _, _ -> })

            }}
            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp,)
            ) {

                items(favProductList, key = { it.id }) { product ->
                    var qty by remember { mutableIntStateOf(0) }

                    ProductCard(
                        product = product,
                        onFavorite = {  }, // ✅ Remove item
                        onShare = { /* … */ },
                        onAddToCart = { qty++ },
                        onRemoveFromCart = { if (qty > 0) qty-- },
                        quantity = qty
                    )



                }
            }
        }
    }
}





