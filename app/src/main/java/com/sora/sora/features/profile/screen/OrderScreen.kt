package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
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
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.core.widgets.OrderStatusWidget
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.ImageBackgroundColor
import com.sora.sora.utils.models.OrderModel


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen() {
    val orderModels = listOf(
        OrderModel(orderId = "Order #87778", items = 10, date = "March 28, 2025", productName = "Soft Plush Bear Toy", amount = "KD 20", status = OrderStatusEnum.Processing, image = R.drawable.img_temp_teddy_without_bg),
        OrderModel(orderId = "Order #87778", items = 10, date = "March 30, 2025", productName = "Brown Men Full T-shirt", amount = "KD 20", status = OrderStatusEnum.Confirmed, image = R.drawable.img_temp_teddy_without_bg),
        OrderModel(orderId = "Order #87778", items = 10, date = "April 2, 2025", productName = "Basket of clean towels", amount = "KD 20", status = OrderStatusEnum.Delivered, image = R.drawable.img_temp_teddy_without_bg),
        OrderModel(orderId = "Order #87778", items = 10, date = "April 2, 2025", productName = "Basket of clean towels", amount = "KD 20", status = OrderStatusEnum.Returned, image = R.drawable.img_temp_teddy_without_bg)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // AppBar should be outside the scrollable content
        CustomAppBar(
            title = "My Orders",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopCenter) // Aligning app bar at the top
        )

        // Column that holds the scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp) // Adjust to make space for the app bar
                .statusBarsPadding()
                .background(Color.White)
                .padding(horizontal = 20.dp, )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(15.dp)) // Spacing before content

            orderModels.forEach { order ->
                OrderCard(orderModel = order)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Composable
fun OrderCard(orderModel: OrderModel) {
    Card(
//        modifier = Modifier
//            .shadow(elevation = 0.dp, spotColor = Color(0x0D000000), ambientColor = Color(0x0D000000),
//           shape = RoundedCornerShape(size = 15.dp), )
//           // .border(width = 1.dp, color = Color.Transparent.copy(alpha = 0.1f), shape = RoundedCornerShape(size = 15.dp))
//            .padding(5.dp)
//            .fillMaxWidth()
//          //  .height(vFactor(120))
//            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 15.dp))
//            .drawBehind(onDraw = {
//                val shadowColor = Color(0x0D000000) // Semi-transparent black for shadow
//                val shadowRadius = 15f
//                drawRoundRect(
//                    color = shadowColor,
//                    size = size.copy(width = size.width - 2.dp.toPx(), height = size.height - 2.dp.toPx()),
//                    cornerRadius = CornerRadius.Zero,
//                    blendMode = BlendMode.SrcOver,
//                    alpha = 0.45f
//                )
//            })
//
//            .clickable {
//                navController.navigate(Dest.OrderDetailScreen::class.toRoute() + "?status=${orderModel.status}")
//            }
//             ,


        shape = RoundedCornerShape(15.dp),
       colors = CardDefaults.cardColors(
           containerColor = Color.White,
           contentColor = Color.White,
           disabledContainerColor = Color.White,
           disabledContentColor = Color.White,




           ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp,
            pressedElevation = 1.dp, focusedElevation = 1.dp,
            hoveredElevation = 1.dp,
            disabledElevation = 1.dp,
            ),


        //border = CardDefaults.outlinedCardBorder(enabled = false),






    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Order Details Column
            Column(
                modifier = Modifier
                    .weight(1f)

                    .align(Alignment.CenterVertically)
                    .padding(10.dp)
            ) {
                // Order ID and Date
//                Row ( modifier = Modifier
//                    .weight(1f) ){
//                    // Order ID with background and rounded corners
//                    Box(
//                        modifier = Modifier
//                            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
//                            .padding(horizontal = 12.dp, vertical = 6.dp)
//                    ) {
//                        Text(
//                            text = order.orderId,
//                            fontSize = 12.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.SemiBold
//
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.width(8.dp))
//
//                    // Order Date
//                    Box(
//                        modifier = Modifier
//                            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
//                            .padding(horizontal = 12.dp, vertical = 6.dp)
//                    ) {
//                        Text(
//                            text = order.date,
//                            fontSize = 12.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))


                Row(

                ){
                    //            // Product Image
                    Image(
                        painter = painterResource(id = orderModel.image),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(80.dp)
                            .height(80.dp)
                            .background(color = ImageBackgroundColor, shape = RoundedCornerShape(size = 8.dp))
                            .padding(start = 4.32432.dp, top = 4.32432.dp, end = 4.32432.dp, bottom = 4.32432.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                  Column(){


                // Product Name
                      CustomMontserratText(
                          text = orderModel.orderId,
                          fontWeight = FontWeight(500),
                          fontSize = 16.sp,
                          color = Color.Black,
                          maxLines = 1,
                          overflow = TextOverflow.Ellipsis
                      )
                      Spacer(modifier = Modifier.height(6.dp))


                CustomMontserratText(
                    text = "Items: ${orderModel.items}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = AppTextGray,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(5.dp))
                      CustomMontserratText(
                    text = "${orderModel.amount}",
                          fontSize = 10.sp,
                          fontWeight = FontWeight(400),
                          color = AppTextGray,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(6.dp))
                      OrderStatusWidget(status = orderModel.status)

                // Order Status Button


            }
            }
            }




        }
    }
}


