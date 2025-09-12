package com.sora.sora.features.dashboard

import android.content.Intent
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sora.sora.core.controller.GlobalController
import com.sora.sora.core.customButtons.CountButton
import com.sora.sora.core.customButtons.CustomButton

import com.sora.sora.core.customButtons.PrimaryButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest

import com.sora.sora.core.navigations.NavigationManager

import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.widgets.AnimatedAddToCart
import com.sora.sora.core.widgets.ProductCard
import com.sora.sora.features.favourites.widgets.RatingReviews
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.widgets.ProductSection
import com.sora.sora.features.favourites.widgets.AddToCartBottomSheet
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.IconBackgroundColor
import com.sora.sora.ui.theme.PrimaryColor100
import com.sora.sora.ui.theme.ProductCardColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ItemDetailScreen(

) {
    // Example state
    var featuresExpanded by remember { mutableStateOf(true) }
    var productDetailsExpanded by remember { mutableStateOf(true) }
    val productDescription = remember { mutableStateOf("Soft, cozy, and designed for unicorn lovers, this plush pillow adds comfort and charm to any space , Soft, cozy, and designed for unicorn lovers, this plush pillow adds comfort and charm to any space") }
    // Dummy products list
    val discountProductList = listOf(
        Product(1, "Kids Colorful car toy", "1.500", discountPercent = 20, oldPrice = "2.500",    painterResource(R.drawable.img_temp_kids_toy)),
        Product(2, "Brown Men Full T-Short", "1.500", discountPercent = 20, oldPrice = "2.500",       painterResource(R.drawable.img_temp_tshirt)),
        Product(3, "Stainless Steel Water Bottle", "1.500", discountPercent = 20, oldPrice = "2.500", painterResource(R.drawable.img_temp_bottel))
    )
    var textExpanded by remember { mutableStateOf(false) }

    //back button
    // Remember the state to control the back button click
    val isBackPressed = remember { mutableStateOf(false) }
    // This ensures that LaunchedEffect is used in a Composable scope
    if (isBackPressed.value) {
        LaunchedEffect(Unit) {
            delay(700)  // Delay to prevent multiple pops in quick succession
            isBackPressed.value = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(color = Color.White)  // soft gradient can be done with Brush.verticalGradient if needed
    ) {

        Row(
            Modifier
                .padding(horizontal = 16.dp)
//                .background(Color.Red)
                .fillMaxWidth(),
//                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor100)
                    .clickable() {

                        // If the button is pressed and no previous action was triggered, handle the navigation
                        if (!isBackPressed.value) {
                            isBackPressed.value = true

                            // Call the provided onBackClick action or default navigation action
                            navController?.popBackStack()

                        }

                    }

            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(25.dp)
                )
            }

            Spacer(Modifier.weight(1f))


            Image(
                painter = painterResource(R.drawable.ic_sora_logo),
                modifier = Modifier
                    .size(70.dp),

                contentDescription = "Cart",

            )

            Spacer(Modifier.weight(1f))
            // Cart
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor100)

                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { /* No animation on press */ },
                            onTap = {
//                                navController.navigate(Dest.CartScreen::class.toRoute())
                                navController.navigate(Dest.CartScreen::class.toRoute() + "?isBackButton=true")


                            }
                        )
                    }


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

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ){

            ItemSlider()

            // Teddy Bear Image
//            Image(
//                painter = painterResource(R.drawable.img_temp_teddy),
//                contentDescription = "Product Image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp)
//                    .clip(RoundedCornerShape(24.dp)),
//                contentScale = ContentScale.Fit
//            )

//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp)
//                    .background(Color(0xFFFDF6F0))
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.img_temp_teddy),
//                    contentDescription = "product.title",
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier.align(Alignment.Center).fillMaxSize()
//                )
//
//                // Heart
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(30.dp)
//                        .background(Color(0xFFF2ECE7), CircleShape)
//                        .align(Alignment.TopStart)
//                        .clickable {
////                            onFavorite()
//                                   },
//                    contentAlignment = Alignment.Center
//                ) {
//                    androidx.compose.material3.Icon(
//                        Icons.Default.FavoriteBorder,
//                        contentDescription = null,
//                        tint = PrimaryColor,
//                        modifier = Modifier.padding(7.dp)
//                    )
//                }
//
//                // Share
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(30.dp)
//                        .background(Color(0xFFF2ECE7), CircleShape)
//                        .align(Alignment.TopEnd)
//                        .clickable {
////                            onShare()
//                                   },
//                    contentAlignment = Alignment.Center
//                ) {
//                    androidx.compose.material3.Icon(
//                        Icons.Default.Share,
//                        contentDescription = null,
//                        tint = PrimaryColor,
//                        modifier = Modifier.padding(7.dp)
//                    )
//                }
//            }

          Column(modifier = Modifier.padding(horizontal = 16.dp)){
              Spacer(modifier = Modifier.height(24.dp))

              // Title + Price + Share Button
              Row(
                  modifier = Modifier.fillMaxWidth(),
                  verticalAlignment = Alignment.Bottom
              ) {
                  Column(modifier = Modifier.weight(1f)) {
                      CustomMontserratText(
                          "Soft Plush Bear Toy",
                          fontWeight = FontWeight.Bold,
                          fontSize = 24.sp
                      )
                      Spacer(modifier = Modifier.height(12.dp))
                      Row(
                          verticalAlignment = Alignment.Bottom,
                          modifier = Modifier.fillMaxSize()
                              .padding(0.dp),

                      ) {
                          CustomMontserratText(
                              text = "KD 5.500",
                              fontWeight = FontWeight.Bold,
                              color = Color(0xFF7B3F00), // brown accent
                              fontSize = 18.sp
                          )
                          Spacer(Modifier.width(4.dp))
                          CustomMontserratText(
                              "2.500",
                              fontSize = 12.sp,
                              color = Color.Gray,
                              textDecoration = TextDecoration.LineThrough,
                              modifier = Modifier.padding(bottom = 1.6.dp)
                          )
                          Spacer(Modifier.width(4.dp))
                          CustomMontserratText(
                              "${20}%",
                              fontSize = 12.sp,
                              fontWeight = FontWeight.Bold,
                              color = Color(0xFFD93025),
                                      modifier = Modifier.padding(bottom = 1.6.dp)
                          )
                      }
                  }
              }


              Spacer(modifier = Modifier.height(12.dp))

              // Description with Read More (simplified)
              CustomMontserratText(
                  text = productDescription.value,
                  fontSize = 14.sp,
                  color = AppTextGray,
                  maxLines = if(textExpanded)  100 else  3,
                  overflow = TextOverflow.Ellipsis
              )
              CustomMontserratText(
                  text = if(textExpanded) "Read Less" else "Read More",
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF7B3F00),
                  modifier = Modifier.clickable { textExpanded = !textExpanded}
              )

              Spacer(modifier = Modifier.height(20.dp))

              //             Divider(color = AppGray, thickness = 1.dp)
              //
              //             Spacer(modifier = Modifier.height(12.dp))

              // Available Colors Row
              //        Text("Available Colors", fontWeight = FontWeight.Bold, fontSize = 18.sp)
              //        Spacer(modifier = Modifier.height(12.dp))
              //        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
              //            listOf(R.drawable.img_temp_teddy1, R.drawable.img_temp_teddy2, R.drawable.img_temp_teddy3).forEach { imageRes ->
              //                Image(
              //                    painter = painterResource(imageRes),
              //                    contentDescription = "Color Option",
              //                    modifier = Modifier
              //                        .size(60.dp)
              //                        .clip(RoundedCornerShape(12.dp))
              //                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
              //                    contentScale = ContentScale.Crop
              //                )
              //            }
              //        }
              //
              //        Spacer(modifier = Modifier.height(20.dp))
              //
              //        Divider(color = AppGray, thickness = 1.dp)
              //
              //        Spacer(modifier = Modifier.height(20.dp))


//              CountButton()
               AddToCartBottomSheet()


              Spacer(modifier = Modifier.height(24.dp))

              // Features Expandable Card
              Card(
                  modifier = Modifier.fillMaxWidth(),
                  shape = RoundedCornerShape(16.dp),
                  elevation = 0.dp
              ) {
                  Column(modifier = Modifier.padding(vertical = 5.dp)) {
                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .clickable { featuresExpanded = !featuresExpanded },
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                      ) {
                          Text("Features", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                          Icon(
                              imageVector = if (featuresExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                              contentDescription = null,
                              tint = PrimaryColor
                          )
                      }
                      if (featuresExpanded) {
                          Spacer(modifier = Modifier.height(12.dp))
                          FeatureItem(text = "Ultra-soft plush fabric with premium cotton filling")
                          FeatureItem(text = "Adorable unicorn design with embroidered details")
                          FeatureItem(text = "Lightweight and easy to carry")
                      }
                  }
              }

              Spacer(modifier = Modifier.height(16.dp))

              // Product Details Table (simplified)
              Card(
                  modifier = Modifier.fillMaxWidth(),
                  shape = RoundedCornerShape(16.dp),
                  elevation = 0.dp
              ) {
                  Column(modifier = Modifier.padding(vertical = 5.dp)) {

                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .clickable { productDetailsExpanded = !productDetailsExpanded },
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                      ) {
                          CustomMontserratText("Product Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                          Icon(
                              imageVector = if (productDetailsExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                              contentDescription = null,
                              tint = PrimaryColor

                          )
                      }
                      if (productDetailsExpanded) {

                          Spacer(modifier = Modifier.height(12.dp))
                          ProductDetailRow("Brand", "DreamCuddle")
                          ProductDetailRow("Product Name", "Soft Plush Unicorn Pillow")
                          ProductDetailRow(
                              "Material",
                              "Ultra-soft plush fabric with premium cotton filling"
                          )
                          ProductDetailRow("Shape", "Unicorn design with embroidered details")
                          ProductDetailRow("Usage", "Sleeping, lounging, décor, travel")
                      }
                  }


              }

              //        Spacer(modifier = Modifier.height(24.dp))
              //        RatingReviews()
              Spacer(modifier = Modifier.height(16.dp))

          }
            ProductSection(title = "Similar Products", products = discountProductList)

            Spacer(modifier = Modifier.height(20.dp)) // extra bottom padding
        }

    }
    }


@Composable
fun FeatureItem(text: String) {

    Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(vertical = 4.dp)) {
        Image(
            painter = painterResource(R.drawable.img_correct_diamond),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomMontserratText(text, fontSize = 14.sp, color = AppTextGray)
    }
}


@Composable
fun ProductDetailRow(label: String, value: String) {
    Spacer(modifier = Modifier.height(6.dp))
//
//    Divider(color = AppGray, thickness = 1.dp)
//
//    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomMontserratText(label, color = Color.Gray, fontSize = 12.sp)
        CustomMontserratText(value, fontSize = 12.sp, fontWeight = FontWeight.W500, textAlign = TextAlign.End)
    }
}

data class DiscountProduct(val title: String, val price: String, val imageRes: Int)
//
//@Composable
//fun DiscountProductCard(product: DiscountProduct) {
//    Card(
//        modifier = Modifier
//            .width(150.dp)
//            .height(220.dp),
//        shape = RoundedCornerShape(16.dp),
//        elevation = 4.dp
//    ) {
//        Column(modifier = Modifier.padding(12.dp)) {
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
//                IconButton(onClick = { /* Favorite action */ }) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_remove),
//                        contentDescription = "Favorite",
//                        tint = Color.Gray,
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//            }
//            Image(
//                painter = painterResource(product.imageRes),
//                contentDescription = product.title,
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(RoundedCornerShape(16.dp)),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(product.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
//            Text(product.price, color = Color(0xFF7B3F00), fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.weight(1f))
//            Box(
//                modifier = Modifier
//                    .size(28.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(Color(0xFF7B3F00))
//                    .clickable { /* Add to cart */ },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add",
//                    tint = Color.White,
//                    modifier = Modifier.size(16.dp)
//                )
//            }
//        }
//    }
//}


@Preview
@Composable
fun PreviewItemDetails(){
    return ItemDetailScreen();
}

@Composable
fun ItemSlider(
    modifier: Modifier = Modifier.padding(horizontal = 0.dp)
) {
    val bannerImages = listOf(
        R.drawable.img_temp_teddy,
        R.drawable.img_temp_teddy,
        R.drawable.img_temp_teddy
    )
    val pagerState = rememberPagerState()
    var isFavourite by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Only ONE coroutine that never interrupts in-progress scrolls
//    LaunchedEffect(pagerState) {
//        while (true) {
//            if (!pagerState.isScrollInProgress) {
//                kotlinx.coroutines.delay(4000)
//                // If still not scrolling and on same page, animate
//                if (!pagerState.isScrollInProgress) {
//                    val nextPage = (pagerState.currentPage + 1) % bannerImages.size
//                    pagerState.animateScrollToPage(
//                        page = nextPage,
//                        animationSpec = tween(
//                            durationMillis = 5000,   // 800ms for quick yet smooth
//                            easing = FastOutSlowInEasing // Makes slide look ‘material’
//                        )
//                    )
//                }
//            }
//            // Check every 100ms to avoid busy looping
//            kotlinx.coroutines.delay(100)
//        }
//    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var boxSize = 370.dp
        var iconSize = 25.dp
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxSize)

        ) {
            HorizontalPager(
                count = bannerImages.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ProductCardColor)
                    .height(boxSize)
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(boxSize)
                ) {
                    Image(
                        painter = painterResource(bannerImages[page]),
                        contentDescription = "product.title",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    )

            }
        }

            // Heart
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
//                    .background(IconBackgroundColor, CircleShape)
                    .align(Alignment.TopStart)
                    .clickable {
                        //                            onFavorite()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_full_screen),
                    contentDescription = null,
                    modifier = Modifier.padding(1.dp).size(iconSize)
                )
            }

            // Share
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .background(IconBackgroundColor, CircleShape)
                        .clickable {
                            //                            onShare()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter =  if (isFavourite) painterResource(R.drawable.ic_selected_favorite ) else painterResource(R.drawable.ic_favorite_outline),
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier
                            .padding(7.dp)
                            .size(iconSize)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {

                                        isFavourite = !isFavourite
                                    }
                                )
                            }

                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .background(IconBackgroundColor, CircleShape)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    Log.d("ProductCard", "Share clicked") // Add debug log
                                    // Debugging log to ensure this block is reached
                                    Log.d("MyTag", "Share button clicked!")

                                    // Ensuring context is not null and start activity safely
                                    context?.let {
                                        val sendIntent: Intent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, "https://www.sora.com")
                                            type = "text/plain"
                                        }

                                        val shareIntent = Intent.createChooser(sendIntent, null)
                                        it.startActivity(shareIntent)
                                    }
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier.padding(7.dp).size(iconSize)
                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .align(Alignment.BottomCenter)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                ) {
                    FancyPagerIndicator(
                        pagerState = pagerState,
                        pageCount = bannerImages.size,
                        activeColor = Color(0xFF86544E),
                        inactiveColor = Color.LightGray
                    )
                }
            }
        }

    }

}
