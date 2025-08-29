package com.sora.sora.features.category.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextGranularity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import com.sora.sora.features.profile.screen.CommonDivider
import com.sora.sora.features.profile.screen.MinimalSwitch
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColor100
import com.sora.sora.ui.theme.SecondaryColor


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
                        .padding(vertical = vFactor(2), horizontal = hFactor(12)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier.size(0.dp)
                    )

                    CustomMontserratText(
                        text = "Filter by",
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

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
                Spacer(modifier = Modifier.height(20.dp))
                Column (modifier = Modifier.padding(horizontal = hFactor(20))){

                     Box(modifier = Modifier.fillMaxWidth()) {
                        val trackWidth =
                            with(LocalDensity.current) { (352.dp - 32.dp).toPx() } // slider width minus padding
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
                            Spacer(modifier = Modifier.width(8.dp)) // Space between label and value
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
                            Spacer(modifier = Modifier.width(8.dp)) // Space between label and value
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
                        MinimalSwitch(
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
                        MinimalSwitch(
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
                                .width(170.dp)
                                .height(60.dp)
                        )
                        Spacer(modifier = Modifier.weight(2f))

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
                                .width(170.dp)
                                .height(60.dp)
                        )
                }


                }


            }
        }
    }

}






/**working but bottom bar issue */

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryResultRow(
//    resultCount: Int = 23,
//) {
//    val coroutineScope = rememberCoroutineScope()
//
//    // Sort BottomSheet State
//    val sortSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showSortSheet by remember { mutableStateOf(false) }
//
//    // Filter BottomSheet State
//    val filterSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showFilterSheet by remember { mutableStateOf(false) }
//
//    val sortOptions = listOf("Most Recent", "Lowest Price", "Highest Price", "A to Z", "Z to A", "Best Selling")
//    var selectedOption by remember { mutableStateOf(sortOptions[0])
//    }
//
//    // --- TOP BAR (Sort & Filter Buttons) ---
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 25.dp, vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        CustomMontserratText(
//            text = "$resultCount Results",
//            modifier = Modifier.weight(1f),
//            fontSize = 12.sp,
//            color = AppTextGray,
//            fontWeight = FontWeight.Bold,
//        )
//
//        // Sort By Button
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable { showSortSheet = true }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.img_sort_bar),
//                contentDescription = "Sort by",
//                modifier = Modifier.size(20.dp),
//                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            CustomMontserratText(
//                text = "Sort by",
//                fontWeight = FontWeight.Bold,
//                fontSize = 12.sp,
//                color = AppTextGray,
//            )
//        }
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        // Filter By Button
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable { showFilterSheet = true }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.img_filter_icon),
//                contentDescription = "Filter by",
//                modifier = Modifier.size(15.dp),
//                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            CustomMontserratText(
//                text = "Filter by",
//                fontWeight = FontWeight.Bold,
//                color = AppTextGray,
//                fontSize = 12.sp
//            )
//        }
//    }
//
//    // --- SORT BOTTOM SHEET ---
//
//    // Show Bottom Sheet only when needed
//    if (showSortSheet) {
//        ModalBottomSheet(
//            onDismissRequest = { showSortSheet = false },
//            sheetState = sortSheetState,
//            modifier = Modifier.height(400.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding( vertical = 16.dp)
//            ) {
//                // Header Row with title and close icon
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 2.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Spacer(modifier = Modifier.weight(1.0f) )
//                    CustomMontserratText(
//                        text = "Choose a sort option",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp
//                    )
//                    Spacer(modifier = Modifier.weight(1.0f) )
//                    IconButton(
//                        onClick = { coroutineScope.launch { sortSheetState.hide() } },
//                        modifier = Modifier.size(24.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "Close",
//                            tint = AppTextGray,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }
//
//                // Divider below the header
//                Divider(color = AppGray, thickness = 1.dp)
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                sortOptions.forEach { option ->
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                selectedOption = option
//                                showSortSheet = false
//                            }
//                            .padding(horizontal = 16.dp)
//                            .padding(vertical = 10.dp) // Adjust the padding as needed
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(20.dp) // Control the size of the radio button
//                                .wrapContentSize(align = Alignment.Center)
//                        ) {
//                            RadioButton(
//                                selected = (selectedOption == option),
//                                onClick = {
//                                    selectedOption = option
//                                    showSortSheet = false
//                                },
//                                modifier = Modifier.size(16.dp), // Set the size of the radio button here
//                                colors = RadioButtonDefaults.colors(
//                                    selectedColor = PrimaryColor, // Color when selected
//                                    unselectedColor = Color.Gray // Color when unselected
//                                )
//                            )
//                        }
//                        Spacer(modifier = Modifier.width(14.dp))
//                        CustomMontserratText(
//                            text = option,
//                            fontSize = 12.sp,
//                            modifier = Modifier.padding(0.dp) // Remove any padding from the text
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//
//    // --- FILTER BOTTOM SHEET ---
//    // Bottom sheet content
//    if (showFilterSheet) {
//        ModalBottomSheet(
//            onDismissRequest = { showFilterSheet = false },
//            sheetState = filterSheetState,
//            modifier = Modifier.heightIn(min = 300.dp)
//        ) {
//
//           var minPrice: Float = 0f
//            var maxPrice: Float = 1000f
//
//            val priceRange = remember { mutableStateOf(minPrice to maxPrice) }
//            var showOffers by remember { mutableStateOf(false) }
//            var hideOutOfStock by remember { mutableStateOf(false) }
//
//            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
//
//                // Header with Close Button
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 2.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Spacer(modifier = Modifier.weight(1f))
//                    CustomMontserratText(
//                        text = "Filter by",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
//                    IconButton(
//                        onClick = { coroutineScope.launch { filterSheetState.hide() } },
//                        modifier = Modifier.size(24.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "Close",
//                            tint = Color.Gray,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }
//
//                // Divider
//                Divider(color = Color.Gray, thickness = 1.dp)
//
//                // Price Range Slider
//                Spacer(modifier = Modifier.height(10.dp))
//                CustomMontserratText(
//                    text = "Price Range",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                RangeSlider(
//                    value = priceRange.value.first..priceRange.value.second,
//                    onValueChange = { priceRange.value = it.start to it.endInclusive },
//                    valueRange = 0f..1000f,
//                    steps = 100,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//
//                // Price Range Labels
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(text = "Min KD ${priceRange.value.first}", fontSize = 12.sp)
//                    Text(text = "Max KD ${priceRange.value.second}", fontSize = 12.sp)
//                }
//
//                // Price Range Numeric Input Fields
//                Spacer(modifier = Modifier.height(10.dp))
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    TextField(
//                        value = priceRange.value.first.toString(),
//                        onValueChange = {
//                            val value = it.toFloatOrNull() ?: priceRange.value.first
//                            priceRange.value = value to priceRange.value.second
//                        },
//                        label = { Text("From") },
//                        modifier = Modifier.weight(1f)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    TextField(
//                        value = priceRange.value.second.toString(),
//                        onValueChange = {
//                            val value = it.toFloatOrNull() ?: priceRange.value.second
//                            priceRange.value = priceRange.value.first to value
//                        },
//                        label = { Text("To") },
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                // Offers Switch
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 10.dp)
//                ) {
//                    Text(text = "Offers", fontSize = 12.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Switch(
//                        checked = showOffers,
//                        onCheckedChange = { showOffers = it }
//                    )
//                }
//
//                // Hide Out of Stock Switch
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 10.dp)
//                ) {
//                    Text(text = "Hide Out of Stock", fontSize = 12.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Switch(
//                        checked = hideOutOfStock,
//                        onCheckedChange = { hideOutOfStock = it }
//                    )
//                }
//
//                // Apply Button
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(
//                    onClick = {
////                        priceRange.value.first, priceRange.value.second, showOffers, hideOutOfStock
//                        showFilterSheet = false
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFB6C6D)) // Apply button color
//                ) {
//                    Text(text = "Apply", fontSize = 14.sp)
//                }
//
//                // Clear Button
//                OutlinedButton(
//                    onClick = {
//                        priceRange.value = 0f to 1000f
//                        showOffers = false
//                        hideOutOfStock = false
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    Text(text = "Clear", fontSize = 14.sp)
//                }
//            }
//        }
//    }
//}

/**wtestingggg */

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryResultRow(resultCount: Int = 23, modifier: Modifier = Modifier) {
//    val coroutineScope = rememberCoroutineScope()
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showBottomSheet by remember { mutableStateOf(false) }
//
//    val sortOptions = listOf(
//        "Most Recent", "Lowest Price", "Highest Price", "A to Z", "Z to A", "Best Selling"
//    )
//    var selectedOption by remember { mutableStateOf(sortOptions[0]) }
//
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 25.dp, vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        CustomMontserratText(
//            text = "$resultCount Results",
//            modifier = Modifier.weight(1f),
//            fontSize = 12.sp,
//            color = AppTextGray,
//            fontWeight = FontWeight.Bold,
//        )
//
//        // Sort by Row
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable {
//                showBottomSheet = true
//            }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.img_sort_bar),
//                contentDescription = "Sort by",
//                modifier = Modifier.size(20.dp),
//                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            CustomMontserratText(
//                text = "Sort by",
//                fontWeight = FontWeight.Bold,
//                fontSize = 12.sp,
//                color = AppTextGray,
//            )
//        }
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        // Filter by Row (Not yet implemented)
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable {
//            showBottomSheet = true
//        }) {
//            Image(
//                painter = painterResource(id = R.drawable.img_filter_icon),
//                contentDescription = "Filter by",
//                modifier = Modifier.size(15.dp),
//                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(AppTextGray)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            CustomMontserratText(
//                text = "Filter by",
//                fontWeight = FontWeight.Bold,
//                color = AppTextGray,
//                fontSize = 12.sp
//            )
//        }
//    }
//
//    // Show Bottom Sheet only when needed
//    if (showBottomSheet) {
//        ModalBottomSheet(
//            onDismissRequest = { showBottomSheet = false },
//            sheetState = sheetState,
//            modifier = Modifier.height(400.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding( vertical = 16.dp)
//            ) {
//                // Header Row with title and close icon
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 2.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Spacer(modifier = Modifier.weight(1.0f) )
//                    CustomMontserratText(
//                        text = "Choose a sort option",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp
//                    )
//                    Spacer(modifier = Modifier.weight(1.0f) )
//                    IconButton(
//                        onClick = { coroutineScope.launch { sheetState.hide() } },
//                        modifier = Modifier.size(24.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "Close",
//                            tint = AppTextGray,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }
//
//                // Divider below the header
//                Divider(color = AppGray, thickness = 1.dp)
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                sortOptions.forEach { option ->
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                selectedOption = option
//                                showBottomSheet = false
//                            }
//                            .padding(horizontal = 16.dp)
//                            .padding(vertical = 10.dp) // Adjust the padding as needed
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(20.dp) // Control the size of the radio button
//                                .wrapContentSize(align = Alignment.Center)
//                        ) {
//                            RadioButton(
//                                selected = (selectedOption == option),
//                                onClick = {
//                                    selectedOption = option
//                                    showBottomSheet = false
//                                },
//                                modifier = Modifier.size(16.dp), // Set the size of the radio button here
//                                colors = RadioButtonDefaults.colors(
//                                    selectedColor = PrimaryColor, // Color when selected
//                                    unselectedColor = Color.Gray // Color when unselected
//                                )
//                            )
//                        }
//                        Spacer(modifier = Modifier.width(14.dp))
//                        CustomMontserratText(
//                            text = option,
//                            fontSize = 12.sp,
//                            modifier = Modifier.padding(0.dp) // Remove any padding from the text
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}

/** ----------------  */
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryFilterBottomSheet(
//    modifier: Modifier = Modifier,
//    minPrice: Float = 0f,
//    maxPrice: Float = 1000f,
//    onApplyFilter: (Float, Float, Boolean, Boolean) -> Unit
//) {
//    val coroutineScope = rememberCoroutineScope()
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showBottomSheetFilter by remember { mutableStateOf(false) }
//
//    val priceRange = remember { mutableStateOf(minPrice to maxPrice) }
//    var showOffers by remember { mutableStateOf(false) }
//    var hideOutOfStock by remember { mutableStateOf(false) }
//
//    // Open bottom sheet on some action
//    LaunchedEffect(Unit) {
//        showBottomSheetFilter = true
//    }
//
//    // Bottom sheet content
//    if (showBottomSheetFilter) {
//        ModalBottomSheet(
//            onDismissRequest = { showBottomSheetFilter = false },
//            sheetState = sheetState,
//            modifier = Modifier.heightIn(min = 300.dp)
//        ) {
//            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
//
//                // Header with Close Button
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 2.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Spacer(modifier = Modifier.weight(1f))
//                    CustomMontserratText(
//                        text = "Filter by",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
//                    IconButton(
//                        onClick = { coroutineScope.launch { sheetState.hide() } },
//                        modifier = Modifier.size(24.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "Close",
//                            tint = Color.Gray,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }
//
//                // Divider
//                Divider(color = Color.Gray, thickness = 1.dp)
//
//                // Price Range Slider
//                Spacer(modifier = Modifier.height(10.dp))
//                CustomMontserratText(
//                    text = "Price Range",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                RangeSlider(
//                    value = priceRange.value.first..priceRange.value.second,
//                    onValueChange = { priceRange.value = it.start to it.endInclusive },
//                    valueRange = 0f..1000f,
//                    steps = 100,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//
//                // Price Range Labels
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(text = "Min KD ${priceRange.value.first}", fontSize = 12.sp)
//                    Text(text = "Max KD ${priceRange.value.second}", fontSize = 12.sp)
//                }
//
//                // Price Range Numeric Input Fields
//                Spacer(modifier = Modifier.height(10.dp))
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    TextField(
//                        value = priceRange.value.first.toString(),
//                        onValueChange = {
//                            val value = it.toFloatOrNull() ?: priceRange.value.first
//                            priceRange.value = value to priceRange.value.second
//                        },
//                        label = { Text("From") },
//                        modifier = Modifier.weight(1f)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    TextField(
//                        value = priceRange.value.second.toString(),
//                        onValueChange = {
//                            val value = it.toFloatOrNull() ?: priceRange.value.second
//                            priceRange.value = priceRange.value.first to value
//                        },
//                        label = { Text("To") },
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                // Offers Switch
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 10.dp)
//                ) {
//                    Text(text = "Offers", fontSize = 12.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Switch(
//                        checked = showOffers,
//                        onCheckedChange = { showOffers = it }
//                    )
//                }
//
//                // Hide Out of Stock Switch
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 10.dp)
//                ) {
//                    Text(text = "Hide Out of Stock", fontSize = 12.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Switch(
//                        checked = hideOutOfStock,
//                        onCheckedChange = { hideOutOfStock = it }
//                    )
//                }
//
//                // Apply Button
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(
//                    onClick = {
//                        onApplyFilter(priceRange.value.first, priceRange.value.second, showOffers, hideOutOfStock)
//                        showBottomSheetFilter = false
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFB6C6D)) // Apply button color
//                ) {
//                    Text(text = "Apply", fontSize = 14.sp)
//                }
//
//                // Clear Button
//                OutlinedButton(
//                    onClick = {
//                        priceRange.value = 0f to 1000f
//                        showOffers = false
//                        hideOutOfStock = false
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    Text(text = "Clear", fontSize = 14.sp)
//                }
//            }
//        }
//    }
//}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CategoryFilterBottomSheet(
//        minPrice = 375f,
//        maxPrice = 784f,
//        onApplyFilter = { min, max, offers, hideOutOfStock ->
//            Log.d("Applied Filter", "Min: $min, Max: $max, Offers: $offers, Hide Out of Stock: $hideOutOfStock")
//        }
//    )
//}
