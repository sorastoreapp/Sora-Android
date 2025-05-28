package com.sora.sora.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.ui.components.AppTextField
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.YellowApp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen() {
    var cartItems by remember {
        mutableStateOf(
            mutableListOf(
                CartItem("Soft Plush Bear Toy", 1, 5.5, R.drawable.ic_cat_toy),
                CartItem("Stainless Water Bottle", 1, 5.5, R.drawable.ic_cat_toy),
                CartItem("Classic Round Sunglasses", 1, 5.5, R.drawable.ic_cat_toy)
            )
        )
    }

    var couponCode by remember { mutableStateOf("") }
    val discountPercent = 10
    val deliveryCharges = 0.5
    val otherCharges = 0.5

    val subtotal = cartItems.sumOf { it.price * it.quantity }
    val discountAmount = subtotal * discountPercent / 100
    val totalAmount = subtotal - discountAmount + deliveryCharges + otherCharges

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(horizontal = 16.dp)
            ) {

                androidx.compose.material3.Text(
                    text = "Cart",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )


                Spacer(Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    itemsIndexed(cartItems, key = { _, item -> item.name }) { index, item ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    cartItems = cartItems.toMutableList().also { list -> list.removeAt(index) }
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
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    "Coupon Code",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    color = Color(0xFF8B5E3C),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(YellowApp, RoundedCornerShape(25.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppTextField(
                        value = couponCode,
                        onValueChange = { couponCode = it },
                        placeholder = "Code (Optional)",
                        height = 20.dp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                            .height(55.dp),
//                        singleLine = true
                    )

                    Button(
                        onClick = { /* Apply coupon action */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .height(38.dp)
                            .width(96.dp)
                    ) {
                        Text("Apply", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(24.dp))

                PriceBreakdown(
                    subtotal = subtotal,
                    discountPercent = discountPercent,
                    discountAmount = discountAmount,
                    deliveryCharges = deliveryCharges,
                    otherCharges = otherCharges,
                    totalAmount = totalAmount,
                    itemCount = cartItems.sumOf { it.quantity }
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "Congratulations! You've saved KD %.3f!".format(discountAmount),
                    color = Color(0xFF0BC62B),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = { /* Continue action */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Continue", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    )
}

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
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewQuantitySelector() {
//    QuantitySelector(
//        quantity = 1,
//        onIncrease = {},
//        onDecrease = {}
//    )
//}
//--i want preview function for qualtitiy selector


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
                .clickable(enabled = quantity > 1) { if (quantity > 1) onDecrease() }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove),  // Use painter here
                contentDescription = "Decrease",
                tint = if (quantity > 1) Color.Black else Color.Gray,
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
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}





@Composable
fun PriceBreakdown(
    subtotal: Double,
    discountPercent: Int,
    discountAmount: Double,
    deliveryCharges: Double,
    otherCharges: Double,
    totalAmount: Double,
    itemCount: Int
) {
    PriceRow("Price ($itemCount Items)", "KD %.3f".format(subtotal))
    PriceRow("Discount ($discountPercent%)", "KD %.3f".format(discountAmount))
    PriceRow("Delivery Charges", "KD %.3f".format(deliveryCharges))
    PriceRow("Other Charges", "KD %.3f".format(otherCharges))

    Spacer(Modifier.height(8.dp))

    Divider(color = Color.LightGray, thickness = 1.dp)

    Spacer(Modifier.height(8.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Total Amount", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("KD %.3f".format(totalAmount), fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }

    Spacer(Modifier.height(8.dp))

    Divider(color = Color.LightGray, thickness = 1.dp)
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
