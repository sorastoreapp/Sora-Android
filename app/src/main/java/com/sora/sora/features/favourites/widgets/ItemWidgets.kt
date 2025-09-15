package com.sora.sora.features.favourites.widgets



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextGranularity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.sora.sora.R
import com.sora.sora.core.customButtons.PrimaryButton
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.vFactor
import com.sora.sora.core.widgets.AnimatedAddToCart
import com.sora.sora.features.category.widgets.BubbleShape
import com.sora.sora.features.profile.widgets.CustomSwitch

import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.ImageBackgroundColor
import com.sora.sora.ui.theme.LightBrown
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.ProductCardColor
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.TextFieldColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCartBottomSheet() {
    val coroutineScope = rememberCoroutineScope()
    var quantity by remember { mutableStateOf(0) }
     val  onAdd: () -> Unit = {quantity++}
    val onRemove: () -> Unit = {if (quantity > 0) quantity--}
    val maxQty by remember { mutableStateOf(5) }
    val minQty by remember { mutableStateOf(0) }
    var pressed by remember { mutableStateOf(false) }

    // onAddToCart = { qty++ },
    // onRemoveFromCart = { if (qty > 0) qty-- },

    // BottomSheet States
    val sortSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var showSortSheet by remember { mutableStateOf(false) }

    val sortOptions = listOf("Most Recent", "Lowest Price", "Highest Price", "A to Z", "Z to A", "Best Selling")
    var selectedOption by remember { mutableStateOf(sortOptions[0]) }



    // ---------------- SORT BOTTOM SHEET ----------------
    PrimaryButton(
        text = "Add to Cart",
        backgroundColor = PrimaryColor,
        onClick = {
            coroutineScope.launch {
                if (sortSheetState.isVisible) sortSheetState.hide()
                showSortSheet = true
            }                  },
    )
    if (showSortSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sortSheetState.hide()
                    showSortSheet = false
                }
            },
            sheetState = sortSheetState,
            modifier = Modifier.defaultMinSize(),
            containerColor = Color.White
        )  {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                // Header Row
                Row {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(PrimaryColor, CircleShape)
                            .padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.White, CircleShape)
                                .align(Alignment.Center)
                                .padding(1.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Correct",
                                tint = PrimaryColor
                            )
                        }
                    }
                    Spacer(Modifier.width(10.dp))
                    CustomMontserratText(
                        text = "Are you sure you want to add this item to your cart?",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(hFactor(15)))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, AppGray, RoundedCornerShape(10.dp))
                            .padding(5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_temp_teddy),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(Modifier.width(hFactor(10)))
                    Column {
                        CustomMontserratText(
                            text = "Soft Plush Bear Toy",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(hFactor(5)))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .height(32.dp)
                                .background(Color(0xFFF6F1EF), RoundedCornerShape(30.dp))
                                .padding(horizontal = vFactor(3), vertical = vFactor(3))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_remove),
                                contentDescription = "Decrease",
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(start = 2.dp)
                                    .clickable {
                                        if (quantity > minQty) {
                                            pressed = true
                                            if (quantity > 0) quantity--
                                            pressed = false
                                        }
                                    }
                            )

                            Text(
                                text = quantity.toString(),
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )

                            Box (modifier = Modifier
                                .clip(CircleShape)
                                .background(PrimaryColor)
                                .padding(horizontal = vFactor(2), vertical = vFactor(2))
                            )
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = "Increase",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            if (quantity < maxQty) {
                                                // if (!expanded) expanded = true
                                                pressed = true
                                                quantity++

                                                pressed = false
                                            }
                                        }
                                )
                            }
                        }
//                        AnimatedAddToCart(
//                            quantity = quantity,
//                            onAdd = onAddToCart,
//                            onRemove = onRemoveFromCart,
//                            color = PrimaryColor,
//                            modifier = Modifier
//                               // .align(Alignment.BottomEnd)
//                                .padding(8.dp)
//                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

                Row{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = TextFieldColor),
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(15.dp))
                                .background(TextFieldColor)
                                .weight(1f)
                                .height(45.dp)
                        ) {
                            CustomMontserratText(text = "Cancel", color = PrimaryColor, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(15.dp))
                                .background(PrimaryColor)
                                .weight(1f)
                                .height(45.dp)
                        ) {
                            CustomMontserratText(text = "Add to Cart", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }

                    }
                    }

                }

            }
        }
    }

