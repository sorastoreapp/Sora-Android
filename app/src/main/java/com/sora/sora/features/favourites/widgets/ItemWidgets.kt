package com.sora.sora.features.favourites.widgets



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
            modifier = Modifier.height(400.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Header Row
                Row {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(PrimaryColor, CircleShape)
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color.White, CircleShape)
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Correct",
                                tint = Color.White
                            )
                        }
                    }
                    CustomMontserratText(
                        text = "Are you sure you want to add this item to your cart?",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(PrimaryColor, CircleShape)
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color.White, CircleShape)
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Correct",
                                tint = Color.White
                            )
                        }
                    }
                    Column {
                        CustomMontserratText(
                            text = "Are you sure you want to add this item to your cart?",
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
                Spacer(modifier = Modifier.height(10.dp))

                Row{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
//                                onAddToCart?.invoke()
                            },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Add to Cart")
                        }

                    }
                    }
                }

            }
        }
    }

