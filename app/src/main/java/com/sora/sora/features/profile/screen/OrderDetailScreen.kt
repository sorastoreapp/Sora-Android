
package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.utils.constant.OrderStatusEnum
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.core.widgets.OrderStatusWidget
import com.sora.sora.features.profile.widgets.OrderCancelBottomSheet
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.ImageBackgroundColor
import com.sora.sora.ui.theme.LightBrown
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldColor3


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun OrderDetailScreen(status: String ="Processing", ) {
    // Convert the incoming status string to the corresponding enum
    val orderStatusEnum = OrderStatusEnum.fromText(status)

    // State for showing the bottom sheet
    val showBottomSheet = remember { mutableStateOf(false) }
    // Example order details
    val orderId = "#SOR123456789"
    val purchaseDate = "March 28, 2025"
    val transactionId = "TXN9876543210"
    val totalAmount = "KD 49.99"
    val shippingMethod = "Standard Deliveryh"
    val contactName = "Fahad Alajmi"
    val contactPhone = "+965 9876 5432"
    val contactAddress = "Block 2, Street 10, House 15, Salmiya, Hawalli, Kuwait"

    val items = listOf(
        OrderItem("Soft Plush Bear Toy", "KD 5.500", 2, R.drawable.img_temp_teddy_without_bg),
        OrderItem("Brown Men Full T-shirt", "KD 5.500", 1, R.drawable.img_temp_kids_toy),
        OrderItem("Basket of clean towels", "KD 5.500", 1, R.drawable.img_temp_wooden_building)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
        CustomAppBar(
            title = "Order Details",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopCenter)// Aligning app bar at the top
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
                .background(color = Color.White)
        ) {
            item {

                // Back Button Section


                Spacer(modifier = Modifier.height(16.dp))

                CustomMontserratText(
                    text = "Order Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = PrimaryColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Order Detail Rows
                OrderDetailRow(
                    label = "Order status",
                    value = orderStatusEnum?.text ?: "Unknown",
                    status = true
                )
                OrderDetailRow(label = "Order Id", value = orderId)
                OrderDetailRow(label = "Purchase Date", value = purchaseDate)
                OrderDetailRow(label = "Transaction ID", value = transactionId)
                OrderDetailRow(label = "Total Amount", value = totalAmount)
                OrderDetailRow(label = "Shipping Method", value = shippingMethod)

                Spacer(modifier = Modifier.height(vFactor(28)))

                // Contact Details Section
                CustomMontserratText(
                    "Contact Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = PrimaryColor
                )
                Spacer(modifier = Modifier.height(vFactor(12)))
                CustomMontserratText(
                    "$contactName",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomMontserratText("$contactPhone", fontSize = 14.sp, color = AppTextGray)
                Spacer(modifier = Modifier.height(4.dp))
                CustomMontserratText("$contactAddress", fontSize = 14.sp, color = AppTextGray)

                Spacer(modifier = Modifier.height(vFactor(28)))


                // Items List Section
                CustomMontserratText(
                    "Items",
                    fontSize = 16.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                items.forEach { item ->

                    // Depending on the order status, display the appropriate item card
                    if (orderStatusEnum == OrderStatusEnum.Processing ||
                        orderStatusEnum == OrderStatusEnum.Shipped ||
                        orderStatusEnum == OrderStatusEnum.Canceled
                    ) {
                        OrderDetailCard(item)
                    } else {
                        OrderDetailReviewCard(item)
                    }

                    // Add 12dp height spacing after each item
                    Spacer(modifier = Modifier.height(vFactor(12)))
                }

                Spacer(modifier = Modifier.height(vFactor(36)))

                // Cancel Order Button for specific statuses
                if (orderStatusEnum == OrderStatusEnum.Processing) {
                    CustomButton(
                        label = "Cancel Order?",
                        onClick = {
                            // Show the bottom sheet when the Cancel Order button is clicked
                            showBottomSheet.value = true

                        },
                        required = true,
                        containerColor = Color(0xFFDB5A5A).copy(alpha = 0.05f),
                        textColor = Color(0xFFDB5A5A),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(hFactor(16)))
                }

                // Conditional button text for specific order statuses
                if (orderStatusEnum != null && orderStatusEnum in listOf(
                        OrderStatusEnum.Shipped,
                        OrderStatusEnum.Canceled,
                        OrderStatusEnum.Delivered,
                        OrderStatusEnum.Refund,
                        OrderStatusEnum.Returned,
                        OrderStatusEnum.Confirmed
                    )
                ) {
                    CustomButton(
                        label = "Have an issue? Contact us",
                        onClick = { /* Handle Click */ },
                        containerColor = Color(0xFFF2FBF8),
                        textColor = Color(0xFF07BD74),
                        icon = R.drawable.img_whatsapp // Pass the resource ID of the icon
                    )

                    Spacer(modifier = Modifier.height(hFactor(16)))
                }


                CustomButton(

                    label = "Return request",
                    onClick = {},
                    containerColor = LightBrown,
                    textColor = PrimaryColor,

                    )
                Spacer(modifier = Modifier.width(hFactor(12)))
                //  }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                )
            }
            // Show the bottom sheet when needed

        }
        if (showBottomSheet.value) {
            OrderCancelBottomSheet(
                orderId = orderId,
                onDismiss = { showBottomSheet.value = false },
                onCancelOrder = { orderId ->
                    // Handle order cancellation logic here
                    showBottomSheet.value = false // Close bottom sheet after canceling
                }
            )
        }
    }
}


//@Composable
//fun OrderDetailScreen(status: String) {
//    // Example order details
//    val orderStatus = status
//    val orderId = "#SOR123456789"
//    val purchaseDate = "March 28, 2025"
//    val transactionId = "TXN9876543210"
//    val totalAmount = "KD 49.99"
//    val shippingMethod = "Standard Delivery (5-7 Business Days)"
//    val contactName = "Fahad Alajmi"
//    val contactPhone = "+965 9876 5432"
//    val contactAddress = "Block 2, Street 10, House 15, Salmiya, Hawalli, Kuwait"
//
//    val items = listOf(
//        OrderItem("Soft Plush Bear Toy", "KD 5.500", 2, R.drawable.img_temp_teddy), // Use the new image
//        OrderItem("Brown Men Full T-shirt", "KD 5.500", 1, R.drawable.img_temp_tshirt), // Example, use relevant image
//        OrderItem("Basket of clean towels", "KD 5.500", 1, R.drawable.img_temp_clean_towel) // Example, use relevant image
//    )
//
//    val orderStatusEnum = OrderStatusEnum.fromText(status)
//
//    LazyColumn(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)
//        .statusBarsPadding()) {
//        item {
//            // Back Button Section
//
//            CustomTopBar("Order Details")
////            Row(verticalAlignment = Alignment.CenterVertically) {
////                IconButton(onClick = { /* Handle back click */ }) {
////                    Icon(
////                        imageVector = Icons.Filled.ArrowBack,
////                        contentDescription = "Back",
////                        modifier = Modifier
////                            .background(
////                                Color.Gray.copy(alpha = 0.2f),
////                                shape = RoundedCornerShape(50)
////                            )
////                            .padding(10.dp)
////                    )
////                }
////                Spacer(modifier = Modifier.width(48.dp))
////                CustomMontserratText(
////                    text = "Order Details",
////                    fontSize = 18.sp,
////                    fontWeight = FontWeight.Bold,
////                    color = Color.Black
////                )
////            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            CustomMontserratText(
//                text = "Order Details",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = PrimaryColor
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Order Detail Rows
//            OrderDetailRow(label = "Order status", value = orderStatus, status = true)
//            OrderDetailRow(label = "Order Id", value = orderId)
//            OrderDetailRow(label = "Purchase Date", value = purchaseDate)
//            OrderDetailRow(label = "Transaction ID", value = transactionId)
//            OrderDetailRow(label = "Total Amount", value = totalAmount)
//            OrderDetailRow(label = "Shipping Method", value = shippingMethod)
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Contact Details Section
//            CustomMontserratText("Contact Details", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
//            Spacer(modifier = Modifier.height(8.dp))
//            CustomMontserratText("$contactName", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
//            Spacer(modifier = Modifier.height(4.dp))
//            CustomMontserratText("$contactPhone", fontSize = 14.sp, color = AppTextGray)
//            Spacer(modifier = Modifier.height(4.dp))
//            CustomMontserratText("$contactAddress", fontSize = 14.sp, color = AppTextGray)
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Items List Section
//            CustomMontserratText("Items", fontSize = 16.sp, color = PrimaryColor, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(12.dp))
//            items.forEach { item ->
////                OrderDetailCard(item)
//              if(status == OrderStatusEnum.Processing.text || status == OrderStatusEnum.Shipped.text || status == OrderStatusEnum.Canceled.text)
//                  OrderDetailCard(item)
//                else OrderDetailReviewCard(item)
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//
//
////            CustomButton(
////                label = "Cancel Order?",
////                onClick = {  },
////                required = true,
////                secondaryButton = true,
////                modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
////            )
////
////            Spacer(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(20.dp)
////            )
//
//            if(status == orderStatusEnum.text) {
//                Button(
//                    onClick = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(65.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2FBF8)),
//                    shape = MaterialTheme.shapes.medium
//                ) {
//                    CustomButton(
//                        label = "Cancel Order?",
//                        onClick = {  },
//                        required = true,
//                        secondaryButton = true,
//                        modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
//                    )
//                    Spacer(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(25.dp)
//                    )
//
//                }
//
//            }
//
//            if (orderStatusEnum != null && orderStatusEnum in listOf(
//                    OrderStatusEnum.Shipped,
//                    OrderStatusEnum.Canceled,
//                    OrderStatusEnum.Delivered,
//                    OrderStatusEnum.Refund,
//                    OrderStatusEnum.Returned,
//                    OrderStatusEnum.Completed
//                )){
//
//                  // Button Text
//                  Row {
//
//                      Image(
//                          painter = painterResource(id = R.drawable.img_whatsapp),
//                          contentDescription = "",
//                          Modifier.size(20.dp)
//                      )
//                      Spacer(modifier = Modifier.width(10.dp))
//                      Text(
//                          text = "Have an issue? Contact us ", // Append '*' for required fields
//                          color = Color(0xFF07BD74),
//                          fontSize = 16.sp,
//                          fontWeight = FontWeight.Bold
//                      )
//                  }
//
//                  Spacer(
//                      modifier = Modifier
//                          .fillMaxWidth()
//                          .height(60.dp)
//                  )
//            }
//
//            if (status == "Shipped"|| status == "Delivered" || status == "Completed"){
//            }
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp)
//            )
//        }
//    }
//}

@Composable
fun OrderDetailRow(label: String, value: String, status: Boolean = false) {
    val orderStatusEnum = OrderStatusEnum.fromText(value)

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = vFactor(7)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.width(200.dp)) {
            CustomMontserratText(label, color = Color(0xB21C0F0C), fontSize = 14.sp,fontWeight = FontWeight(400))
        }



        if (status) {

            OrderStatusWidget(status = OrderStatusEnum.fromText(value))
        } else {
            CustomMontserratText(value,
                fontSize = 14.sp, textAlign = TextAlign.End,
            )
        }
    }
}

@Composable
fun OrderDetailCard(order: OrderItem) {
    Card(
        modifier = Modifier
            .shadow(elevation = 24.dp, spotColor = Color(0x0D000000), ambientColor = Color(0x0D000000))
            .border(width = 1.dp, color = Color.Black.copy(alpha = 0.1f), shape = RoundedCornerShape(size = 15.dp))
            .padding(1.dp)
            .fillMaxWidth()
            .height(vFactor(80))
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 15.dp)),


        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = hFactor(8), end = hFactor(16), top = vFactor(8), bottom = vFactor(8))
        ) {
            // Order Details Column
            Column(
                modifier = Modifier
                    .weight(1f)

            ) {
                Row() {
                    // Product Image
                    Image(
                        painter = painterResource(id = order.image),
                        contentDescription = "Product Image",

                        modifier =
                            Modifier
                                .width(74.dp)
                                .height(74.dp)
                                .background(color = ImageBackgroundColor, shape = RoundedCornerShape(size = 8.dp))
                                .padding(start = 4.32432.dp, top = 4.32432.dp, end = 4.32432.dp, bottom = 4.32432.dp)
                    )
                    Spacer(modifier = Modifier.width(hFactor(10)))

                    Column() {
                        // Product Name
                        CustomMontserratText(
                            text = order.name,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(vFactor(4)))

                        // Order Status Button

                        CustomMontserratText(
                            text = "Qty - ${order.quantity}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = AppTextGray
                        )
                        Spacer(modifier = Modifier.height(vFactor(4)))
                        CustomMontserratText(
                            text = "${order.amount}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = AppTextGray
                        )





                    }
                }
            }
        }
    }
}

@Composable
fun OrderDetailReviewCard(order: OrderItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
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
                Row() {
                    // Product Image
                    Image(
                        painter = painterResource(id = order.image),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 16.dp)
                    )

                    Column() {
                        // Product Name
                        Text(
                            text = order.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Amount Paid
                        Text(
                            text = "Amount Paid: ${order.amount}",
                            fontSize = 15.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Order Status Button
                        Box(
                            modifier = Modifier
                                .background(
                                    color = TextFieldColor3,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 20.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "Qty - ${order.quantity}",
                                fontSize = 13.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    // Star Ratings
                    repeat(5) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_unfilled_star_review),
                            contentDescription = "Star Rating",
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(3.dp))
                    }
                    Spacer(Modifier.weight(1f))

                    // Add Review Text
                    Text(
                        text = "Add Review",
                        fontSize = 14.sp,
                        color = PrimaryColor,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                navController.navigate(Dest.AddReviewScreen ::class.toRoute())
                            }
                    )
                }
            }
        }
    }
}


data class OrderItem(
    val name: String,
    val amount: String,
    val quantity: Int,
    val image: Int // Image resource for the product
)




//package com.sora.sora.features.profile.screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Card
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sora.sora.R
//import com.sora.sora.core.customButtons.CustomButton
//import com.sora.sora.core.customText.CustomMontserratText
//import com.sora.sora.ui.theme.AppTextGray
//import com.sora.sora.ui.theme.PrimaryColor
//import com.sora.sora.ui.theme.SecondaryColor
//import com.sora.sora.ui.theme.TextFieldColor
//import com.sora.sora.ui.theme.TextFieldColor2
//import com.sora.sora.ui.theme.TextFieldColor3
//
//@Composable
//fun OrderDetailScreen() {
//    // Example order details
//    val orderStatus = "Processing"
//    val orderId = "#SOR123456789"
//    val purchaseDate = "March 28, 2025"
//    val transactionId = "TXN9876543210"
//    val totalAmount = "KD 49.99"
//    val shippingMethod = "Standard Delivery (5-7 Business Days)"
//    val contactName = "Fahad Alajmi"
//    val contactPhone = "+965 9876 5432"
//    val contactAddress = "Block 2, Street 10, House 15, Salmiya, Hawalli, Kuwait"
//
//    val items = listOf(
//        OrderItem("Soft Plush Bear Toy", "KD 5.500", 2, R.drawable.img_temp_teddy), // Use the new image
//        OrderItem("Brown Men Full T-shirt", "KD 5.500", 1, R.drawable.img_temp_tshirt), // Example, use relevant image
//        OrderItem("Basket of clean towels", "KD 5.500", 1, R.drawable.img_temp_clean_towel) // Example, use relevant image
//    )
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)
//        .statusBarsPadding()) {
//        // Back Button Section
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(onClick = { /* Handle back click */ }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(50))
//                        .padding(10.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(48.dp))
//            CustomMontserratText(
//                text = "Order Details",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        CustomMontserratText(
//            text = "Order Details",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = PrimaryColor
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        OrderDetailRow(
//            label = "Order status",
//            value = orderStatus,
//            status = true
//        )
//        OrderDetailRow(
//            label = "Order Id",
//            value = orderId,
//        )
//        OrderDetailRow(
//            label = "Purchase Date",
//            value = purchaseDate
//        )
//        OrderDetailRow(
//            label = "Transaction ID",
//            value = transactionId
//        )
//        OrderDetailRow(
//            label = "Total Amount",
//            value = totalAmount
//        )
//
//        OrderDetailRow(
//            label = "Shipping Method",
//            value = shippingMethod
//        )
//
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Contact Details Section
//        CustomMontserratText("Contact Details",         fontSize = 16.sp, fontWeight = FontWeight.Bold , color = PrimaryColor)
//        Spacer(modifier = Modifier.height(8.dp))
//        CustomMontserratText("$contactName"      ,fontSize = 14.sp,  fontWeight = FontWeight.Bold, color = Color.Black)
//        Spacer(modifier = Modifier.height(4.dp))
//        CustomMontserratText("$contactPhone",    fontSize = 14.sp, color = AppTextGray)
//        Spacer(modifier = Modifier.height(4.dp))
//        CustomMontserratText("$contactAddress",fontSize = 14.sp, color = AppTextGray)
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Items List Section
//        CustomMontserratText("Items", fontSize = 16.sp, color = PrimaryColor, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(12.dp))
//        items.forEach { item ->
//            OrderDetailCard(item)
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        // Buttons Section
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            TextButton(onClick = { /* Handle "Have an issue?" */ }) {
//                Text("Have an issue? Contact us", color = Color.Blue)
//            }
//            Button(onClick = { /* Handle "Return request" */ }) {
//                Text("Return request", color = Color.White)
//            }
//        }
//
//        // Save Button
//        CustomButton(
//            label = "Cancel Order",
//            onClick = {  },
//            required = true,
//            secondaryButton = true,
//            modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
//        )
//
//    }
//}
//
//@Composable
//fun OrderDetailRow(label: String, value: String, status : Boolean = false) {
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Box (modifier = Modifier.width(200.dp)){
//            CustomMontserratText(label, color = Color.Gray, fontSize = 14.sp)
//        }
//       if(status)  Box(
//
//           modifier = Modifier
//               .background(
////                                    color = when (order.quantity) {
////                                        "Pending" -> Color(0xFFF75555)
////                                        "Shipped" -> Color(0xFF246BFD)
////                                        "Delivered" -> Color(0xFF07BD74)
////                                        else -> Color.Gray
////                                    },
//                   color =  TextFieldColor2,
//                   shape = RoundedCornerShape(20.dp)
//               )
//
//       ) {
//           CustomMontserratText(
//               text = value,
//               fontSize = 14.sp,
//               color = SecondaryColor,
//               textAlign = TextAlign.End,
//               modifier = Modifier.background(color = TextFieldColor3, shape = RoundedCornerShape(20.dp)).padding(horizontal = 8.dp, vertical = 8.dp)
//           )
//       }else  CustomMontserratText(value, fontSize = 14.sp, textAlign = TextAlign.End)
//    }
//}
//
//
//@Composable
//fun OrderDetailCard(order: OrderItem) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(130.dp),
//        shape = RoundedCornerShape(15.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(4.dp)
//        ) {
//
//
//            // Order Details Column
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(16.dp)
//            ) {
//
//                Row(){
//                    //            // Product Image
//                    Image(
//                        painter = painterResource(id = order.image),
//                        contentDescription = "Product Image",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .align(Alignment.CenterVertically)
//                            .padding(end = 16.dp)
//                    )
//
//                    Column(){
//
//
//                        // Product Name
//                        Text(
//                            text = order.name,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 14.sp,
//                            color = Color.Black,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//
//
//                        // Amount Paid
//                        Text(
//                            text = "Amount Paid: ${order.amount}",
//                            fontSize = 15.sp,
//                            color = Color.Black
//                        )
//
//                        Spacer(modifier = Modifier.height(4.dp))
//
//                        // Order Status Button
//                        Box(
//                            modifier = Modifier
//                                .background(
////                                    color = when (order.quantity) {
////                                        "Pending" -> Color(0xFFF75555)
////                                        "Shipped" -> Color(0xFF246BFD)
////                                        "Delivered" -> Color(0xFF07BD74)
////                                        else -> Color.Gray
////                                    },
//                                    color = TextFieldColor3,
//                                    shape = RoundedCornerShape(20.dp)
//                                )
//                                .padding(horizontal = 20.dp, vertical = 1.dp)
//                        ) {
//                            Text(
//                                text = "Qty - ${order.quantity}",
//                                fontSize = 13.sp,
//                                color = Color.Black,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                        }
//
//                    }
//                }
//            }
//
//
//
//
//        }
//    }
//}
//
//data class OrderItem(
//    val name: String,
//    val amount: String,
//    val quantity: Int,
//    val image: Int // Image resource for the product
//)