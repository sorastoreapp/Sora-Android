/**Working code without animation and proper sized*/

//package com.sora.sora.features.dashboard
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.navigationBarsPadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Icon
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.sora.sora.R
//import com.sora.sora.core.temp.TempCustomData
//import com.sora.sora.features.profile.screen.ProfileScreen
//import com.sora.sora.ui.theme.PrimaryColor
//
//@Composable
//fun DashboardScreen(navController : NavController) {
//    var selectedIndex by remember { mutableStateOf(0) }
//
//    // You can replace this with NavController-based navigation if preferred
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(
//                selectedItem = selectedIndex,
//                onItemSelected = { selectedIndex = it }
//            )
//        },
//        modifier = Modifier.fillMaxSize()
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            when (selectedIndex) {
//                0 -> HomeScreen()
//                1 -> CategoryScreen(TempCustomData().categories)
//                2 -> CartScreen()
//                3 -> FavoritesScreen()
//                4 -> ProfileScreen()
//                else -> HomeScreen()
//            }
//        }
//    }
//
//}
//
//@Composable
//fun BottomNavigationBar(
//    selectedItem: Int,
//    onItemSelected: (Int) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val items = listOf(
//        NavItem("Home", R.drawable.ic_home, R.drawable.ic_white_home),
//        NavItem("Category", R.drawable.ic_category, R.drawable.ic_white_cat),
//        NavItem("Cart", R.drawable.ic_cart, R.drawable.ic_white_cart),
//        NavItem("Favorites", R.drawable.ic_favoritess, R.drawable.ic_white_fav),
//        NavItem("Profile", R.drawable.ic_profile, R.drawable.ic_white_profile)
//    )
//
//    Surface(
//        color = Color.White,
////            shadowElevation = 8.dp,
//        modifier = modifier
//            .fillMaxWidth()
//            .navigationBarsPadding()
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 12.dp, vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            items.forEachIndexed { index, item ->
//                val isSelected = selectedItem == index
//                val weight = if (isSelected) 2f else 1f
//
//                Box(
//                    modifier = Modifier
//                        .weight(weight)
//                        .clip(RoundedCornerShape(16.dp))
//                        .background(if (isSelected) PrimaryColor else Color.Transparent)
//                        .clickable(
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = null, // disables ripple
//                            onClick = { onItemSelected(index) }
//                        )
//
//                        .padding(horizontal = if (isSelected) 16.dp else 0.dp, vertical = 8.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        if (isSelected) {
//                            Icon(
//                                painter = painterResource(id = item.icon_selected),
//                                contentDescription = item.title,
//                                tint = Color.White,
//                                modifier = Modifier.size(22.dp)
//                            )
//                        } else {
//                            Icon(
//                                painter = painterResource(id = item.icon),
//                                contentDescription = item.title,
//                                modifier = Modifier.size(22.dp),
//                                        tint = Color.Unspecified
//                            )
//                        }
//                        if (isSelected) {
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text(
//                                text = item.title,
//                                color = Color.White,
//                                fontWeight = FontWeight.SemiBold,
//                                fontSize = 13.sp,
//                                maxLines = 1
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//


package com.sora.sora.features.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sora.sora.R
import com.sora.sora.core.temp.TempCustomData
import com.sora.sora.features.profile.screen.ProfileScreen
import com.sora.sora.ui.theme.PrimaryColor

@Composable
fun DashboardScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> CategoryScreen(TempCustomData().categories)
                2 -> CartScreen()
                3 -> FavoritesScreen()
                4 -> ProfileScreen()
                else -> HomeScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("Home", R.drawable.ic_unselected_home, R.drawable.ic_selected_home),
        NavItem("Category", R.drawable.ic_unselected_categories, R.drawable.ic_selected_categories),
        NavItem("Cart", R.drawable.ic_unselected_cart, R.drawable.ic_selected_cart),
        NavItem("Favorites", R.drawable.ic_unselected_favorite, R.drawable.ic_selected_favorite),
        NavItem("Profile", R.drawable.ic_unselected_profile, R.drawable.ic_selected_profile)
    )
    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .then(Modifier.shadow(10.dp, shape = RoundedCornerShape(16.dp), clip = false)) // Apply shadow only on top

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem == index

                // Use animateDpAsState for smooth width change for selected tab
                val paddingHorizontal by animateDpAsState(
                    targetValue = if (isSelected) 16.dp else 0.dp
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) PrimaryColor else Color.Transparent)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { /* No animation on press */ },
                                onTap = { onItemSelected(index) }
                            )
                        }
                        .padding(vertical = 8.dp, horizontal = paddingHorizontal)
                        .wrapContentWidth(align = Alignment.CenterHorizontally),  // Allow text to dynamically fit width
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Unselected icon with smooth fade and scale
                        AnimatedVisibility(visible = !isSelected) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp),
                                tint = Color.Gray
                            )
                        }

                        // Selected icon with smooth fade and scale
                        AnimatedVisibility(visible = isSelected) {
                            Icon(
                                painter = painterResource(id = item.icon_selected),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }

                        // Text label animation with smooth horizontal expansion
                        AnimatedVisibility(
                            visible = isSelected,
                            enter = fadeIn() + slideInHorizontally(initialOffsetX = { 40 }) + expandIn(),
                            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { -40 }) + shrinkOut()
                        ) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .height(24.dp) // Fix height for consistent item size
                                    .wrapContentWidth()  // Allow text to expand horizontally
                                    .wrapContentHeight(align = Alignment.CenterVertically)  // Center vertically
                                    .padding(end = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

