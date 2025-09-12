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
import com.sora.sora.features.category.widgets.BubbleShape
import com.sora.sora.features.profile.widgets.CustomSwitch

import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.ProductCardColor
import com.sora.sora.ui.theme.TextFieldColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCartBottomSheet() {
    val coroutineScope = rememberCoroutineScope()

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
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

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
                    Spacer(Modifier.width(10.dp))
                    Column {
                        CustomMontserratText(
                            text = "Object Coloring Book - 30 Patterns",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        CustomMontserratText(
                            text = "Qty: 1",
                            fontWeight = FontWeight.Normal,
                            color = AppTextGray,
                            fontSize = 12.sp
                        )
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

