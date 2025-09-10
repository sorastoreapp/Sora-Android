package com.sora.sora.features.profile.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.snackbar.CenterExpandSnackBar
import com.sora.sora.core.snackbar.SnackBarData
import com.sora.sora.core.snackbar.SnackBarType
import com.sora.sora.core.vFactor
import com.sora.sora.features.profile.widgets.CommonDivider
import com.sora.sora.features.profile.widgets.CustomSwitch
import com.sora.sora.features.profile.widgets.LanguageSelectionBottomSheet
import com.sora.sora.features.profile.widgets.ProfileMenuItem
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
    var showSnackbar by remember { mutableStateOf(false) } // Control snackbar visibility
    var switchEnabled by remember { mutableStateOf(true) }



  Scaffold(
      backgroundColor = Color.White,

      modifier = Modifier
          .background(Color.White)
          .fillMaxSize()
          .systemBarsPadding()
      ,

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
                  CustomSwitch(
                      checked = notificationsEnabled,
                      onCheckedChange = { checked ->
                          // Update the state and trigger showing the snackbar
                          notificationsEnabled = checked
                          showSnackbar = checked // Show Snackbar if notifications are enabled
                      },
                      enabled = switchEnabled
                  )
              },
              onClick = {
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
    if (showSnackbar) {
        CenterExpandSnackBar(
            visible = showSnackbar,
            topPadding = 50.dp,
            horizontalMargin = 10.dp,



            data = SnackBarData(
                title = "Success!",
                message = "Notification enabled successfully",
                type = SnackBarType.Success,
                iconRes = R.drawable.ic_sora_logo
            ),
            onDismiss = { showSnackbar = false } // Hide the snackbar when dismissed
        )
    }

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








