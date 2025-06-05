package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded


@Composable
fun OrdersScreen() {
    val orders = listOf(
        Order(orderId = "SORA-9876543210", date = "March 28, 2025", productName = "Soft Plush Bear Toy", amount = "KD 5.500", status = "Pending", image = R.drawable.img_temp_teddy),
        Order(orderId = "SORA-9876543210", date = "March 30, 2025", productName = "Brown Men Full T-shirt", amount = "KD 5.500", status = "Shipped", image = R.drawable.img_temp_tshirt),
        Order(orderId = "SORA-9876543210", date = "April 2, 2025", productName = "Basket of clean towels", amount = "KD 5.500", status = "Delivered", image = R.drawable.img_temp_clean_towel)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        // Back Button Section
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Handle back click */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.width(48.dp))
            Text(
                text = "My Orders",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        orders.forEach { order ->
            OrderCard(order = order)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {


            // Order Details Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // Order ID and Date
                Row ( modifier = Modifier
                    .weight(1f) ){
                    // Order ID with background and rounded corners
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = order.orderId,
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold

                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Order Date
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = order.date,
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(){
                    //            // Product Image
                    Image(
                        painter = painterResource(id = order.image),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 16.dp)
                    )

                  Column(){


                // Product Name
                Text(
                    text = order.productName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                // Amount Paid
                Text(
                    text = "Amount Paid: ${order.amount}",
                    fontSize = 13.sp,
                    color = Color.Black
                )

                      Spacer(modifier = Modifier.height(4.dp))

                // Order Status Button
                Box(
                    modifier = Modifier
                        .background(
                            color = when (order.status) {
                                "Pending" -> Color(0xFFF75555)
                                "Shipped" -> Color(0xFF246BFD)
                                "Delivered" -> Color(0xFF07BD74)
                                else -> Color.Gray
                            },
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = order.status,
                        fontSize = 13.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
            }
            }




        }
    }
}

data class Order(
    val orderId: String,
    val date: String,
    val productName: String,
    val amount: String,
    val status: String,
    val image: Int // Image resource for the product
)
