package com.sora.sora.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
import com.sora.sora.ui.components.AppTextField
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.YellowApp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen() {
    // ----------------------------
    // 1) Dummy cart data + state
    // ----------------------------
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

    // ------------------------------------------------------
    // 2) Entire screen as one LazyColumn (header + all parts)
    // ------------------------------------------------------
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 32.dp) // so bottom button isn't cut off
        ) {
            // ----- Item 0: Screen Title -----
            item {
                Text(
                    text = "Cart",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            // ----- Items 1..N: Cart items with SwipeToDismiss -----
            itemsIndexed(
                items = cartItems,
                key = { index, item -> item.name + index } // unique key
            ) { index, item ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { newState ->
                        if (newState == DismissValue.DismissedToStart) {
                            // Remove item from the list
                            cartItems = cartItems.toMutableList().also { it.removeAt(index) }
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFF3B30))
                                .padding(end = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    },
                    dismissContent = {
                        CartItemCard(
                            item = item,
                            onIncrease = {
                                cartItems = cartItems.toMutableList().also {
                                    it[index] = it[index].copy(quantity = it[index].quantity + 1)
                                }
                            },
                            onDecrease = {
                                if (item.quantity > 1) {
                                    cartItems = cartItems.toMutableList().also {
                                        it[index] = it[index].copy(quantity = it[index].quantity - 1)
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // If the cart is empty, you might want to show a “Your cart is empty” message:
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

            // ----- Item N+1: Coupon Code Section -----
            // ───── Inside your LazyColumn { … } ─────
            item {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Coupon Code",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = PrimaryColor, // brown text
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF2F2F2))
                        .border(1.dp, Color(0xFFF2F2F2), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SmallCouponTextField(
                        value = couponCode,
                        onValueChange = { couponCode = it },
                        placeholder = "Code (Optional)",
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = { /* Apply coupon logic */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(40.dp)
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


            // ----- Item N+2: Price Breakdown (subtotal, discount, etc.) -----
            item {
                Spacer(modifier = Modifier.height(24.dp))

                PriceRow(label = "Price ($itemCount Items)", value = "KD %.3f".format(subtotal))
                PriceRow(label = "Discount ($discountPercent%)", value = "KD %.3f".format(discountAmount))
                PriceRow(label = "Delivery Charges", value = "KD %.3f".format(deliveryCharges))
                PriceRow(label = "Other Charges", value = "KD %.3f".format(otherCharges))

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

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

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
            }

            // ----- Item N+3: “Congratulations!…” saved text -----
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Congratulations! You've saved KD %.3f!".format(discountAmount),
                    color = Color(0xFF0BC62B),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,        // <-- make it italic
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    textAlign = TextAlign.Start
                )
            }

            // ----- Item N+4: Continue button -----
            item {
                Button(
                    onClick = { /* Continue action */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        "Continue",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(38.dp))
            }
        }
    }
}

// ---------------------------------
//  Helper Composables & Data Class
// ---------------------------------

@Composable
fun CartItemCard(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        "KD6.500",
                        style = LocalTextStyle.current.copy(
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Text(
                        "KD 5.500",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = PrimaryColor
                    )
                }

                Spacer(Modifier.height(12.dp))

                QuantitySelector(
                    quantity = item.quantity,
                    onIncrease = onIncrease,
                    onDecrease = onDecrease
                )
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(PrimaryColor)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        // Decrease Button
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(YellowApp)
                .clickable(enabled = quantity > 1) {
                    if (quantity > 1) onDecrease()
                }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove,),
                contentDescription = "Decrease",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            "$quantity",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Increase Button
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(YellowApp)
                .clickable { onIncrease() }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
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
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
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



