package com.sora.sora.features.profile.screen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.LightBrown
import com.sora.sora.ui.theme.PrimaryColor

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var showLanguageSheet by remember { mutableStateOf(false) }


  Scaffold(
      backgroundColor=Color.White,

      modifier = Modifier
          .background(Color.White)
          .fillMaxSize(),

      topBar = {



          CenterAlignedTopAppBar(
              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                  containerColor = Color.White
              ),
              modifier = Modifier.
              padding(0.dp),
              title = {
                  CustomMontserratText(
                      text = "Settings",
                      fontSize = 18.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.Black
                  )
              },

          )

      },



  ) {

          paddingValues ->
//      CustomAppBar(
//          title = "Settings",
//          isBackButton = false,
//         // modifier = Modifier.padding(bottom = vFactor(40)),
//
//          onBackClick = {
//              // navController.navigate(Dest.Home.toRoute())
//          }
//      )


      Column(
          modifier = Modifier
              .fillMaxSize()
              .verticalScroll(rememberScrollState())

              .padding(horizontal = 20.dp,)
      ) {
          Spacer(modifier = Modifier.height(vFactor(5)))

          Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth()
          ) {
              Image(
                  painter = painterResource(id = R.drawable.ic_profile_icon), // Your profile photo drawable
                  contentDescription = "Profile Photo",
                  contentScale = ContentScale.Crop,


                  modifier = Modifier
                      .size(48.dp)
                      .clip(CircleShape)
                      .background(Color(0xFFF3EDEC)) // Fondo claro marrón

                      .padding(6.dp)


              )

              Spacer(modifier = Modifier.width(hFactor(8)))


              CustomMontserratText(
                  text = "Visitor",
                  fontWeight = FontWeight(500),
                  fontSize = 16.sp,
                  color = Color.Black
              )
              Spacer(modifier = Modifier
                  .height(6.dp)
                  .weight(1f))



              CustomMontserratText(
                  text = "Create account",

                  fontSize = 12.sp,
                  color = PrimaryColor,
                  fontWeight = FontWeight.Bold,
                  lineHeight = 20.sp,
                  textAlign = TextAlign.Right,
                  modifier = Modifier.pointerInput(Unit) {
                      detectTapGestures(
                          onPress = { offset ->
                              navController.navigate(Dest.EditProfileScreen::class.toRoute())
                          }
                      )
                  })

          }

          Spacer(modifier = Modifier.height(vFactor(30)))

          // Section Title
          CustomMontserratText(
              text = "Account",
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = PrimaryColor
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Account items
          ProfileMenuItem(
              iconRes = R.drawable.ic_orders,
              title = "My orders",
              onClick = {
                  navController.navigate(Dest.OrdersScreen::class.toRoute()) {
                      popUpTo(Dest.OrdersScreen::class.toRoute()) { inclusive = true }
                  }
              }
          )
          CommonDivider()

          ProfileMenuItem(
              showArrow = true,
              iconRes = R.drawable.ic_address,
              title = "My Addresses",
              onClick = {
                  navController.navigate(Dest.MyAddressScreen::class.toRoute()) {
                      popUpTo(Dest.OrdersScreen::class.toRoute()) { inclusive = true }
                  }
              }
          )
          CommonDivider()
          var notificationsEnabled by remember { mutableStateOf(true) }
          var switchEnabled by remember { mutableStateOf(true) } // control disabled state

          ProfileMenuItem(
              iconRes = R.drawable.ic_notifications,
              title = "Notifications",
              trailingContent = {

                  MinimalSwitch(
                      checked = notificationsEnabled,
                      onCheckedChange = { notificationsEnabled = it },
                      enabled = switchEnabled
                  )
              },
              onClick = {
                  // Example: toggle whether the switch is enabled/disabled
                  // switchEnabled = !switchEnabled
                  navController.navigate(Dest.NotificationScreen::class.toRoute())
              }
          )



          CommonDivider()

          ProfileMenuItem(
              iconRes = R.drawable.ic_language,
              title = "Languages",

              trailingText = "English",
              onClick = {
                  showLanguageSheet = true
                  // Language selection bottom sheet
              }
          )
          if (showLanguageSheet) {
              LanguageSelectionBottomSheet(
                  onDismiss = { showLanguageSheet = false },
                  onLanguageSelected = { language ->
                      selectedLanguage = language
                      showLanguageSheet = false
                  })
          }



          Spacer(modifier = Modifier.height(vFactor(30)))

          CustomMontserratText(
              text = "Others",
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = PrimaryColor
          )

          Spacer(modifier = Modifier.height(5.dp))

          ProfileMenuItem(
              iconRes = R.drawable.ic_tnc,
              title = "Terms & Conditions",
              onClick = {
                  navController.navigate(Dest.TermConditionScreen::class.toRoute())
              }
          )
          CommonDivider()

          ProfileMenuItem(
              iconRes = R.drawable.ic_pp,
              title = "Privacy policies",
              onClick = {
                  navController.navigate(Dest.PrivacyPolicyScreen::class.toRoute())
              }
          )
          CommonDivider()

          ProfileMenuItem(
              iconRes = R.drawable.ic_faq,
              title = "FAQ’s",
              onClick = {
                  navController.navigate(Dest.FaqScreen::class.toRoute())
              }
          )
          CommonDivider()

          ProfileMenuItem(
              iconRes = R.drawable.ic_about_us,
              title = "About Us",
              onClick = {
                  navController.navigate(Dest.AboutUsScreen::class.toRoute())
              }
          )
          Spacer(modifier = Modifier.height(22.dp))
          CustomButton(
              label = "Log in",
              onClick = {},
              containerColor = LightBrown,
              textColor = PrimaryColor,

              )


          Spacer(modifier = Modifier.height(16.dp))
          CustomButton(
              label = "Have an issue? Contact us",
              onClick = { /* Handle Click */ },
              containerColor = Color(0xFFF2FBF8),
              textColor = Color(0xFF07BD74),
              icon = R.drawable.img_whatsapp // Pass the resource ID of the icon
          )
          Spacer(modifier = Modifier.height(200.dp))


      }
    }

}

@Composable
fun ProfileMenuItem(
    iconRes: Int? = null,
    title: String,
    trailingText: String? = null,
    showArrow: Boolean = true,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
    modifier: Modifier? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier?:Modifier
            .fillMaxWidth()
            .height(48.dp)
            .pointerInput(Unit) {
                // Custom touch handling for Skip Button without ripple effect
                detectTapGestures {
                    onClick()
                }
            }
            .padding(horizontal = hFactor(10))
    ) {

        if(iconRes!=null)
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier. width(12.dp))
        CustomMontserratText(
            text = title,
            fontSize = 15.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(500),
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        if (trailingText != null) {
            CustomMontserratText(
                text = trailingText,
                color = Color(0xFF8A4C3D),
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                fontSize = 12.sp
            )

        }
        if (trailingContent != null) {
            trailingContent()
        }
        Spacer(modifier = Modifier.width(12.dp))
        if (showArrow) {

            Icon(

                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow",

                tint = PrimaryColor
                ,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DottedDivider(
    color: Color,
    dashWidth: Dp = 4.dp,
    dashGap: Dp = 4.dp,
    height: Dp = 1.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val dashWidthPx = dashWidth.toPx()
        val dashGapPx = dashGap.toPx()
        var startX = 0f
        val y = size.height / 2

        while (startX < size.width) {
            drawLine(
                color = color,
                start = Offset(startX, y),
                end = Offset(startX + dashWidthPx, y),
                strokeWidth = height.toPx(),
                cap = StrokeCap.Round
            )
            startX += dashWidthPx + dashGapPx
        }
    }
}
@Composable
fun CommonDivider(
    color: Color = Color.Gray.copy(alpha = 0.1f),
    thickness: Dp = 1.dp,
    horizontalPadding: Dp = 0.dp,
    verticalPadding: Dp = 4.dp
) {
    Divider(
        color = color,
        thickness = thickness,
        modifier = Modifier
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    )
}


@Immutable
data class MinimalSwitchColors(
    val checkedThumb: Color = Color(0xFFFFFFFF),
    val checkedTrack: Color = Color(0xFF8A4C3D),
    val uncheckedThumb: Color = Color.White,
    val uncheckedTrack: Color = AppGray,

    // Disabled state
    val disabledThumb: Color = Color.White.copy(alpha = 0.7f),
    val disabledTrack: Color = AppGray
)

@Composable
fun MinimalSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: MinimalSwitchColors = MinimalSwitchColors()
) {
    // Choose colors based on state
    val targetTrack = when {
        !enabled -> colors.disabledTrack
        checked -> colors.checkedTrack
        else -> colors.uncheckedTrack
    }
    val targetThumb = when {
        !enabled -> colors.disabledThumb
        checked -> colors.checkedThumb
        else -> colors.uncheckedThumb
    }

    val trackColor by animateColorAsState(targetValue = targetTrack, label = "trackColor")
    val thumbColor by animateColorAsState(targetValue = targetThumb, label = "thumbColor")
    val thumbOffset by animateDpAsState(targetValue = if (checked) 33.dp else 4.dp, label = "thumbOffset")

    val shape = CircleShape

    Box(
        modifier = modifier
            .width(55.dp)
            .height(27.dp)
            .clip(shape)
            .background(trackColor)
            .toggleable( // ✅ instead of clickable + semantics
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                onValueChange = { onCheckedChange(it) }
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        // Thumb
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(18.5.dp)
                .clip(shape)
                .background(thumbColor)
//                .border(
//                    width = 1.dp,
//                    color = if (enabled) Color.Black.copy(alpha = 0.05f) else Color.Black.copy(alpha = 0.04f),
//                    shape = shape
//                )
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionBottomSheet(
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    val languages = listOf("English", "العربية")
    val languageIcons = listOf(R.drawable.ic_usa_flat, R.drawable.ic_kuwait_flag)
    val selectedLanguage = remember { mutableStateOf("English") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp) // optional rounded corners
    ) {
        Column(
            modifier = Modifier.padding(horizontal = hFactor(24), vertical = vFactor(8))
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustomMontserratText(
                    text = "Select Language",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = PrimaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Cross",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clickable { onDismiss() }
                )
            }

            languages.forEachIndexed { index, language ->
                ProfileMenuItem(
                    showArrow = false,
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(48.dp)
                          .pointerInput(Unit) {
                              // Custom touch handling for Skip Button without ripple effect
                              detectTapGestures {
                                  Log.d("ProfileScreen", "My orders clicked")
                              }
                          }
                          .padding(start = 8.dp, end = 0.dp),


                    title = language,
                    onClick = {
                       // Log.d("ProfileScreen", "My orders clicked")

                    },
                    trailingContent = {
                        if (selectedLanguage.value == language) Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Color.Green.copy(alpha = 0.05f)


                                )

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = Color(0XFF07BD74)
                            )
                        } else Box(modifier = Modifier.size(0.dp))
                    }
                )
                if (selectedLanguage.value == language)
                CommonDivider()
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Save Button
            CustomButton(
                label = "Save",
                onClick = { onDismiss },

                modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
            )

            Spacer(modifier = Modifier.height(5.dp))

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null
) {
     CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Settings",

                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

