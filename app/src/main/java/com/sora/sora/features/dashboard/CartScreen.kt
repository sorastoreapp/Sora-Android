package com.sora.sora.features.dashboard

import EmptyCartScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.features.cart_screen.controller.CartController
import com.sora.sora.ui.components.AppTextField
import com.sora.sora.ui.theme.IconBackgroundColor
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.ProductCardColor
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.YellowApp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {


    // Initialize controller
    val controller = remember { CartController() }

    // Observe the state of cart being empty and refreshing
    val isCartEmpty by controller.isEmptyStateTrue.collectAsState()
    val isRefreshing by controller.isRefreshing.collectAsState()

    // Pull-to-refresh state
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                controller.refreshCart()
            }
        }
    )


    Scaffold (
        backgroundColor = Color.White,
        modifier = Modifier
        .fillMaxSize()
            .systemBarsPadding()
        .pullRefresh(pullRefreshState),
            topBar = {

        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(0.dp),
            title = {
                CustomMontserratText(
                    text = "Cart",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },

            )

    }

    ){
        paddingValues ->
        Box{

            if (isCartEmpty)  CartScreenMainView()
            else  EmptyCartScreen()



            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

        }
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CartScreenMainView() {

    var cartItems by remember {
        mutableStateOf(
            mutableListOf(
                CartItem("Soft Plush Bear Toy", 1, 5.5, R.drawable.img_temp_teddy),
                CartItem("Stainless Water Bottle", 1, 5.5, R.drawable.img_temp_teddy),
                CartItem("Classic Round Sunglasses", 1, 5.5, R.drawable.img_temp_teddy)
            )
        )
    }

    // Coupon‐code state
    var couponCode by remember { mutableStateOf("") }
    val discountPercent = 10
    val deliveryCharges = 0.5
    val otherCharges = 0.5

    // Calculate subtotal, discount, total
    val subtotal = cartItems.sumOf { it.price * it.quantity }
    val discountAmount = subtotal * discountPercent / 100
    val totalAmount = subtotal - discountAmount + deliveryCharges + otherCharges
    val itemCount = cartItems.sumOf { it.quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 32.dp) // so bottom button isn't cut off
        ) {
            item {
                CustomMontserratText(
                    text = "Products",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )

            }


            itemsIndexed(
                items = cartItems,
                key = { index, item -> item.name + index } // unique key
            ) { index, item ->

                CartItemCard(
                    cartItems = cartItems,
                    index = index,
                    item = item,
                    onIncrease = {
                        cartItems = cartItems.toMutableList().also {
                            it[index] =
                                it[index].copy(quantity = it[index].quantity + 1)
                        }
                    },
                    onDecrease = {
                        if (item.quantity > 1) {
                            cartItems = cartItems.toMutableList().also {
                                it[index] =
                                    it[index].copy(quantity = it[index].quantity - 1)
                            }
                        }
                    },
                    onDelete = { i ->
                        cartItems = cartItems.toMutableList().apply {
                            removeAt(i)
                        }
                    }
                )
            }

            if (cartItems.isEmpty()) {
                item {
                    Text(
                        "Your cart is empty",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            }


            item {
                Spacer(modifier = Modifier.height(8.dp))

                CustomMontserratText(
                    text = "Address",
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { /* Unused */ },
                                onTap = {
                                    navController.navigate(Dest.MyAddressScreen::class.toRoute())
                                }
                            )
                        }
                        .padding(vertical = 10.dp),
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )


                //hey chatgpt i wnat row at the center of the box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(57.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ProductCardColor),
                    contentAlignment = Alignment.Center // Centers Row both horizontally & vertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            tint = PrimaryColor,
                            contentDescription = "location",
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        CustomMontserratText(
                            text = "Add your address",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                CustomMontserratText(
                    text = "Coupon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF2F2F2))
                        .height(57.dp)
                        .border(1.dp, Color(0xFFF2F2F2), RoundedCornerShape(16.dp))
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_coupon),
                        contentDescription = "coupon",
                        tint = Color(0xff928C8A),
                        modifier = Modifier.size(18.dp)
                    )

                    SmallCouponTextField(
                        value = couponCode,
                        onValueChange = { couponCode = it },
                        placeholder = "Code (Optional)",
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = { /* Apply coupon logic */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                0xFF7B3F00
                            )
                        ),
                        shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                        modifier = Modifier
                            .height(57.dp)
                            .width(100.dp)
                    ) {
                        Text(
                            "Apply",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }


            item {
                Spacer(modifier = Modifier.height(24.dp))

                PriceRow(
                    label = "Price ($itemCount Items)",
                    value = "KD %.3f".format(subtotal)
                )
                PriceRow(
                    label = "Discount ($discountPercent%)",
                    value = "KD %.3f".format(discountAmount)
                )
                PriceRow(
                    label = "Delivery Charges",
                    value = "KD %.3f".format(deliveryCharges)
                )
                PriceRow(
                    label = "Other Charges",
                    value = "KD %.3f".format(otherCharges)
                )

                Spacer(modifier = Modifier.height(8.dp))
//                    Divider(color = Color.LightGray, thickness = 1.dp)
//                    Spacer(modifier = Modifier.height(8.dp))

                // Total line
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Amount",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "KD %.3f".format(totalAmount),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

//                    Spacer(modifier = Modifier.height(8.dp))
//                    Divider(color = Color.LightGray, thickness = 1.dp)
            }


//                item {
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        "Congratulations! You've saved KD %.3f!".format(discountAmount),
//                        color = Color(0xFF0BC62B),
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Normal,
//                        fontStyle = FontStyle.Italic,        // <-- make it italic
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 24.dp),
//                        textAlign = TextAlign.Start
//                    )
//                }


            item {
                Button(
                    onClick = {
                        navController.navigate(Dest.PaymentSuccessScreen::class.toRoute())
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(
                            0xFF7B3F00
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    CustomMontserratText(
                        "Checkout",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

}


@Composable
fun CartItemCard(
    cartItems:  List<CartItem>, // Use List, not MutableList
    index: Int,
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                NavigationManager.navigateTo(Dest.ItemDetailScreen::class.toRoute())
            }
    ) {
        Row(
            Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(color = IconBackgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp)
            ){
                androidx.compose.foundation.Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row {
                    CustomMontserratText(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_bucket,),
                        contentDescription = "Decrease",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { onDelete(index) }
                    )

                }

                Spacer(Modifier.height(6.dp))




                Row {
                    Column(
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            CustomMontserratText(
                                "KD 5.500",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFF605755)
                            )
                            Text(
                                "KD6.500",
                                style = LocalTextStyle.current.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                            CustomMontserratText(
                                "10%",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color.Red
                            )

                        }
                        Spacer(Modifier.height(6.dp))
                        CustomMontserratText(
                            "Size: S",
                            fontSize = 12.sp,
                            color = Color(0xFF605755)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    QuantitySelector(
                        quantity = item.quantity,
                        onIncrease = onIncrease,
                        onDecrease = onDecrease
                    )
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    var size = 20.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(IconBackgroundColor)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        // Decrease Button
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
//                .background(YellowApp)
                .clickable(enabled = quantity > 1) {
                    if (quantity > 1) onDecrease()
                }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove_bar,),
                contentDescription = "Decrease",
                tint = PrimaryColor,
                modifier = Modifier
                    .size(size)
                    .clickable(enabled = quantity > 1) {
                        if (quantity > 1) onDecrease()
                    }
            )
        }

        Text(
            "$quantity",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Increase Button
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(PrimaryColor)
                .clickable { onIncrease() }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = Color.White,
                modifier = Modifier.size(size)
            )
        }
    }
}

@Composable
fun PriceRow(label: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomMontserratText(label, color = Color.Gray, fontSize = 14.sp)
        CustomMontserratText(value, fontWeight = FontWeight.W600, fontSize = 14.sp)
    }
}

data class CartItem(
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageRes: Int
)

// ---------------------------------------------------------------------
// If you want a @Preview for the QuantitySelector on its own, you can add this:
@Preview(showBackground = true)
@Composable
fun PreviewQuantitySelector() {
    QuantitySelector(quantity = 1, onIncrease = {}, onDecrease = {})
}

@Composable
fun SmallCouponTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    // Remember whether to show the placeholder
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }

    Box(
        modifier = modifier
            .height(44.dp)                                   // total height ~44 dp
            .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
            // NOTE: we removed the .border(...) here
            .padding(horizontal = 12.dp),                    // left/right padding
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isHintVisible = it.isEmpty()
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 18.sp
            ),
            cursorBrush = SolidColor(Color(0xFF8B5E3C)),      // brown cursor
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF2F2F2),)
            // no top/bottom padding here so the text sits centered
        )

        if (isHintVisible) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}



