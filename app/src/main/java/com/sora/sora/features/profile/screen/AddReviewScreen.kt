package com.sora.sora.features.profile.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import com.sora.sora.R
import com.sora.sora.core.CustomTopBar
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.components.AppTextField2
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.ui.theme.TextFieldColor

@Composable
fun AddReviewScreen() {
    // States for rating and review text
    var rating by remember { mutableStateOf(3) } // Default rating as 3 stars
    var reviewText by remember { mutableStateOf("") } // Text in the review

    // Rating star icons
    val filledStarIcon = painterResource(id = R.drawable.ic_filled_star_review)
    val unfilledStarIcon = painterResource(id = R.drawable.ic_unfilled_star_review)

    // Teddy bear image
    val teddyImage = painterResource(id = R.drawable.img_temp_teddy) // Replace with actual image

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(top = 50.dp)   ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button Section (optional)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            CustomTopBar(title = "Add reviews")
//            IconButton(onClick = { /* Handle back click */ }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .size(45.dp)
//                        .background(PrimaryColorFaded, CircleShape)
//                        .padding(10.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(48.dp))
//            Text(
//                text = "Add Reviews",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.weight(1f)
//            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Product Image
        Image(
            painter = teddyImage,
            contentDescription = "Teddy Bear",
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Title
        CustomMontserratText(
            text = "Soft Plush Bear Toy",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rating (Stars)
        Row(horizontalArrangement = Arrangement.Center) {
            // Create 5 stars, filled or unfilled based on rating
            repeat(5) { index ->
                Icon(
                    painter = if (index < rating)filledStarIcon  else unfilledStarIcon,
                    contentDescription = "Star $index",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { rating = index + 1 } // Update rating on click
                        .background(color = Color.Transparent),
                            tint = Color.Unspecified // Ensure the icon is not tinted gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Review TextField (write your review)
        AppTextField2(
            value = reviewText,
            onValueChange = { reviewText = it },
            placeholder = "Write your review...",
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth().height(150.dp), // Adjust the height as per your design
            prefixIcon = null // Optional: you can add a prefix icon if needed
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        CustomButton(
            label = "Submit",
            onClick = {  },
            required = true,
            modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
        )
    }
}
