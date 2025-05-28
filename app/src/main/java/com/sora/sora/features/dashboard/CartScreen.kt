package com.sora.sora.features.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures

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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.ui.components.AppTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen() {
    // Sample data for products - replace with your data model & images
    var cartItems by remember {
        mutableStateOf(
            mutableListOf(
                CartItem("Soft Plush Bear Toy", 1, 5.5, R.drawable.ic_cat_toy), // use your drawable resource
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

    val scope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .systemBarsPadding()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(cartItems, key = { _, item -> item.name }) { index, item ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    cartItems.removeAt(index)
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
                                        .background(Color(0xFFFF3B30))
                                        .padding(end = 20.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            },
                            dismissContent = {
                                CartItemCard(
                                    item = item,
                                    onIncrease = {
                                        cartItems[index] =
                                            item.copy(quantity = item.quantity + 1)
                                    },
                                    onDecrease = {
                                        if (item.quantity > 1) {
                                            cartItems[index] =
                                                item.copy(quantity = item.quantity - 1)
                                        }
                                    }
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Coupon Code",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Using your AppTextField style
                AppTextField(
                    value = couponCode,
                    onValueChange = { couponCode = it },
                    placeholder = "Code (Optional)",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Apply button aligned right
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { /* handle apply coupon */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text("Apply", color = Color.White, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Price Breakdown
                PriceRow(label = "Price (${cartItems.sumOf { it.quantity }} Items)", value = "KD %.3f".format(subtotal))
                PriceRow(label = "Discount ($discountPercent%)", value = "KD %.3f".format(discountAmount))
                PriceRow(label = "Delivery Charges", value = "KD %.3f".format(deliveryCharges))
                PriceRow(label = "Other Charges", value = "KD %.3f".format(otherCharges))

                Spacer(modifier = Modifier.height(8.dp))

                // Grey line
                Divider(color = Color.LightGray, thickness = 1.dp)

                Spacer(modifier = Modifier.height(8.dp))

                // Total Amount row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Amount", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("KD %.3f".format(totalAmount), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Grey line
                Divider(color = Color.LightGray, thickness = 1.dp)

                Spacer(modifier = Modifier.height(8.dp))

                // Congratulations Text in green
                Text(
                    "Congratulations! You've saved KD %.3f!".format(discountAmount),
                    color = Color(0xFF0BC62B),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Continue Button
                Button(
                    onClick = { /* Handle continue */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Continue", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    )
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

@Composable
fun CartItemCard(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            androidx.compose.foundation.Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    "KD %.3f".format(item.price),
                    color = Color(0xFF9E9E9E),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Quantity Row with custom buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${item.quantity}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .background(Color(0xFFFFCA00), RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Decrease button
                    IconButton(
                        onClick = onDecrease,
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color(0xFFFFCA00), shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Decrease",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Increase button
                    IconButton(
                        onClick = onIncrease,
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color(0xFFFFCA00), shape = CircleShape)
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
        }
    }
}

data class CartItem(
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageRes: Int
)
