package com.sora.sora.features.category.screen

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
fun CategoryResultRow(resultCount: Int = 23, verticalPadding: Dp = 8.dp, horizontalPadding: Dp = 25.dp) {
    val coroutineScope = rememberCoroutineScope()

    // BottomSheet States
    val sortSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val filterSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var showSortSheet by remember { mutableStateOf(false) }
    var showFilterSheet by remember { mutableStateOf(false) }

    val sortOptions = listOf("Most Recent", "Lowest Price", "Highest Price", "A to Z", "Z to A", "Best Selling")
    var selectedOption by remember { mutableStateOf(sortOptions[0]) }

    // Top Row (Results Count, Sort, Filter)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CustomMontserratText(
            text = "$resultCount Results",
            modifier = Modifier.weight(1f),
            fontSize = 12.sp,
            color = AppTextGray,
            fontWeight = FontWeight.Bold,
        )

        // Sort Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    if (filterSheetState.isVisible) filterSheetState.hide()
                    showSortSheet = true
                }
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_sort_bar),
                contentDescription = "Sort by",
                modifier = Modifier.size(20.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
            )
            Spacer(modifier = Modifier.width(4.dp))
            CustomMontserratText(
                text = "Sort by",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = AppTextGray,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Filter Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    if (sortSheetState.isVisible) sortSheetState.hide()
                    showFilterSheet = true
                }
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_filter_icon),
                contentDescription = "Filter by",
                modifier = Modifier.size(15.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
            )
            Spacer(modifier = Modifier.width(4.dp))
            CustomMontserratText(
                text = "Filter by",
                fontWeight = FontWeight.Bold,
                color = AppTextGray,
                fontSize = 12.sp
            )
        }
    }

    // ---------------- SORT BOTTOM SHEET ----------------
    if (showSortSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sortSheetState.hide()
                    showSortSheet = false
                }
            },
            containerColor = Color.White,
            sheetState = sortSheetState,
            modifier = Modifier.height(400.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    CustomMontserratText(
                        text = "Choose a sort option",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                sortSheetState.hide()
                                showSortSheet = false
                            }
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = AppTextGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Divider(color = AppGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                sortOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedOption = option
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        RadioButton(
                            selected = (selectedOption == option),
                            onClick = {
                                selectedOption = option
                                coroutineScope.launch {
                                    sortSheetState.hide()
                                    showSortSheet = false
                                }
                            },
                            modifier = Modifier.size(16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = PrimaryColor,
                                unselectedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        CustomMontserratText(text = option, fontSize = 12.sp)
                    }
                }
            }
        }
    }

    // ---------------- FILTER BOTTOM SHEET ----------------
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    filterSheetState.hide()
                    showFilterSheet = false
                }
            },
            containerColor = Color.White,

            sheetState = filterSheetState,
            modifier = Modifier.heightIn(min = 300.dp)
        ) {
            var minPrice by remember { mutableStateOf(0f) }
            var maxPrice by remember { mutableStateOf(1200f) }
            val priceRange = remember { mutableStateOf(minPrice to maxPrice) }
            var showOffers by remember { mutableStateOf(false) }
            var hideOutOfStock by remember { mutableStateOf(false) }
            val minValueFraction = (priceRange.value.first - minPrice) / (maxPrice - minPrice)
            val maxValueFraction = (priceRange.value.second - minPrice) / (maxPrice - minPrice)

            Column(modifier = Modifier
                .fillMaxWidth()
                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, )
                        .padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    CustomMontserratText(
                        text = "Filter By",
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                filterSheetState.hide()
                                showFilterSheet = false
                            }
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = AppTextGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Divider(color = AppGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(20.dp))
                Column (modifier = Modifier.padding(horizontal = hFactor(20))){

                     Box(modifier = Modifier.fillMaxWidth()) {
                        val screenWidth = with(LocalConfiguration.current) { screenWidthDp.dp }
                        val trackWidth =
                            with(LocalDensity.current) { (screenWidth - 100.dp).toPx() } // slider width minus padding
                        val minThumbX = minValueFraction * trackWidth
                        val maxThumbX = maxValueFraction * trackWidth
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(50.dp)
                                .offset { IntOffset(minThumbX.toInt(), 0) }

                                .background(
                                    Color.Gray.copy(alpha = 0.55f), BubbleShape(
                                        cornerRadius = 24.0f,
                                        pointerWidth = 24.0f,
                                        pointerHeight = 20.0f
                                    )
                                )
                                .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 5.dp)
                        ) {
                            CustomMontserratText(
                                text = priceRange.value.first.toInt().toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(50.dp)
                                .offset { IntOffset(maxThumbX.toInt(), 0) }
                                .background(
                                    Color.Gray.copy(alpha = 0.55f), BubbleShape(
                                        cornerRadius = 24.0f,
                                        pointerWidth = 24.0f,
                                        pointerHeight = 20.0f
                                    )
                                )
                                .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 5.dp)
                        ) {

                            CustomMontserratText(
                                text = priceRange.value.second.toInt().toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                        RangeSlider(
                            value = priceRange.value.first..priceRange.value.second,
                            onValueChange = { priceRange.value = it.start to it.endInclusive },
                            valueRange = 0f..1200f,


                            startThumb = { state ->
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)  // Set the size of the thumb
                                        .clip(CircleShape)  // Make it circular
                                        .background(Color.White)  // Set the background color to white
                                        .border(
                                            6.dp,
                                            PrimaryColor,
                                            CircleShape
                                        )  // Set the border to brown (PrimaryColor)
                                        .shadow(0.dp, CircleShape)  // Remove the shadow
                                )
                            },

                            endThumb = { state ->
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)  // Set the size of the thumb
                                        .clip(CircleShape)  // Make it circular
                                        .background(Color.White)  // Set the background color to white
                                        .border(
                                            6.dp,
                                            PrimaryColor,
                                            CircleShape
                                        )  // Set the border to brown (PrimaryColor)
                                        .shadow(0.dp, CircleShape)  // Remove the shadow
                                )
                            },


                            colors = SliderDefaults.colors(
                                thumbColor = Color.Transparent,  // Thumb color is already handled via startThumb and endThumb
                                activeTrackColor = PrimaryColor,  // Active track color
                                inactiveTrackColor = PrimaryColor.copy(alpha = 0.4f),  // Inactive track color
                                activeTickColor = Color.Transparent,  // Active tick color
                                inactiveTickColor = Color.Transparent  // Inactive tick color
                            ),

                            modifier = Modifier
                                .padding(top = 32.dp, start = 12.dp, end = 24.dp)

                                .shadow(elevation = 0.dp)  // Remove the overall shadow
                        )


                    }

                    Spacer(modifier = Modifier.height(10.dp))




                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        CustomMontserratText(text = "Min : KD 0.0",color = AppTextGray, fontSize = 12.sp)
                        CustomMontserratText(text = "Max : KD 1200.0",color = AppTextGray, fontSize = 12.sp)
                    }

//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Row(
//                        modifier = Modifier.weight(1f),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "From", fontSize = 12.sp)
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(text = priceRange.value.first.toInt().toString(), fontSize = 12.sp)
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    TextField(
//                        value = priceRange.value.second.toInt().toString(),
//                        onValueChange = {
//                            val value = it.toFloatOrNull() ?: priceRange.value.second
//                            priceRange.value = priceRange.value.first to value
//                        },
//                        label = { Text("To") },
//                        modifier = Modifier.weight(1f)
//                    )
//                }

                    Row(
                        modifier = Modifier

                            .padding( top = vFactor(10), bottom = vFactor(14)) ,// Padding around the entire row
                             // Padding inside the box
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // "From" Box with Label and Value
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp) // Padding inside the box
                                .border(1.dp, AppGray, RoundedCornerShape(8.dp)) // Border for the box
                                .padding(horizontal = 16.dp, vertical = 14.dp), // Padding inside the box
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomMontserratText(
                                text = "from",
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            CustomMontserratText(
                                text = "${priceRange.value.first.toInt()}",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            CustomMontserratText(
                                text = "KD",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )
                        }




                        // "To" Box with Label and Value
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp) // Padding inside the box
                                .border(1.dp, AppGray, RoundedCornerShape(8.dp)) // Border for the box
                                .padding(horizontal = 16.dp, vertical = 14.dp), // Padding inside the box
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomMontserratText(
                                text = "to",
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            CustomMontserratText(
                                text = "${priceRange.value.second.toInt()}",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            CustomMontserratText(
                                text = "KD",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        CustomMontserratText(
                            text = "Offers",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        var switchEnabled by remember { mutableStateOf(true) }
                        CustomSwitch(
                            checked  = showOffers,
                            onCheckedChange = { showOffers = it },
                            enabled = switchEnabled
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        CustomMontserratText(
                            text = "Hide Out of Stock",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        var switchEnabled by remember { mutableStateOf(true) }
                        CustomSwitch(
                            checked = hideOutOfStock,
                            onCheckedChange = { hideOutOfStock = it },
                            enabled = switchEnabled
                        )


                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth().
                        padding(horizontal = hFactor(10))



                    ){
                        PrimaryButton(
                            text = "Apply",
                            onClick = {
                                coroutineScope.launch {
                                    filterSheetState.hide()
                                    showFilterSheet = false
                                }
                            },
                            borderColor = PrimaryColor,

                            modifier = Modifier
                                .width(160.dp)
                                .height(60.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        PrimaryButton(
                            text = "Clear",
                            backgroundColor = Color.White,
                            textColor = PrimaryColor,
                            borderColor = PrimaryColor,
                            onClick = {
                                coroutineScope.launch {
                                    filterSheetState.hide()
                                    showFilterSheet = false
                                }
                            },
                            modifier = Modifier
                                .width(160.dp)
                                .height(60.dp)
                        )

                }
                    Spacer(modifier = Modifier.height(8.dp))


                }


            }
        }
    }

}
