package com.sora.sora.features.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import com.sora.sora.features.favourites.widgets.RatingReviews
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.utils.widgets.ProductSection
import java.time.format.TextStyle

@Composable
fun ItemDetailScreen(

) {
    // Example state
    var featuresExpanded by remember { mutableStateOf(true) }
    val productDescription = remember { mutableStateOf("Soft, cozy, and designed for unicorn lovers, this plush pillow adds comfort and charm to any space...") }
    // Dummy products list
    val discountProductList = listOf(
        Product(1, "Kids Colorful car toy", "KD 1.500", painterResource(R.drawable.img_temp_kids_toy)),
        Product(2, "Brown Men Full T-Short", "KD 1.500", painterResource(R.drawable.img_temp_tshirt)),
        Product(3, "Stainless Steel Water Bottle", "KD 1.500", painterResource(R.drawable.img_temp_bottel))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)  // soft gradient can be done with Brush.verticalGradient if needed
            .padding(16.dp)
    ) {
        // Top Bar Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { /* Back action */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(PrimaryColorFaded, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Row {
                IconButton(
                    onClick = { /* Favorite */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(PrimaryColorFaded, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = { /* Cart */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(PrimaryColorFaded, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Teddy Bear Image
        Image(
            painter = painterResource(R.drawable.img_temp_teddy),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Title + Price + Share Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Soft Plush Bear Toy", fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Price: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "KD6.500",
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "KD 5.500",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7B3F00), // brown accent
                        fontSize = 18.sp
                    )
                }
            }
            IconButton(
                onClick = { /* Cart */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(PrimaryColor, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White  // <-- this makes the icon white
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Description with Read More (simplified)
        Text(
            text = productDescription.value,
            fontSize = 14.sp,
            color = Color.DarkGray,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Read More",
            fontSize = 13.sp,
            color = Color(0xFF7B3F00),
            fontStyle = FontStyle.Italic,   // <-- italic style
            modifier = Modifier.clickable { /* Expand full description */ }
        )

            Spacer(modifier = Modifier.height(12.dp))

             Divider(color = AppGray, thickness = 1.dp)

             Spacer(modifier = Modifier.height(12.dp))

        // Available Colors Row
        Text("Available Colors", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf(R.drawable.img_temp_teddy1, R.drawable.img_temp_teddy2, R.drawable.img_temp_teddy3).forEach { imageRes ->
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "Color Option",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(color = AppGray, thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        // Add To Cart Button
        Button(
            onClick = { /* Add to cart */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B3F00))
        ) {
            Text("Add To Cart", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(color = AppGray, thickness = 1.dp)

        Spacer(modifier = Modifier.height(12.dp))

        // Available Sizes
        Text("Available Sizes", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("12 inches", "18 inches", "24 inches").forEach { size ->
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF2F2F2), RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(size, fontSize = 14.sp, color = Color.DarkGray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Features Expandable Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { featuresExpanded = !featuresExpanded },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Features", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Icon(
                        imageVector = if (featuresExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                if (featuresExpanded) {
                    Spacer(modifier = Modifier.height(12.dp))
                    FeatureItem(text = "Ultra-soft plush fabric with premium cotton filling")
                    FeatureItem(text = "Adorable unicorn design with embroidered details")
                    FeatureItem(text = "Lightweight and easy to carry")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Product Details Table (simplified)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Product Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(12.dp))

                ProductDetailRow("Brand", "DreamCuddle")
                ProductDetailRow("Product Name", "Soft Plush Unicorn Pillow")
                ProductDetailRow("Material", "Ultra-soft plush fabric with premium cotton filling")
                ProductDetailRow("Shape", "Unicorn design with embroidered details")
                ProductDetailRow("Usage", "Sleeping, lounging, décor, travel")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        RatingReviews()
        Spacer(modifier = Modifier.height(24.dp))

        ProductSection(title = "Discount Products", products = discountProductList)
    }
}

@Composable
fun FeatureItem(text: String) {

    Spacer(modifier = Modifier.height(12.dp))

    Divider(color = AppGray, thickness = 1.dp)

    Spacer(modifier = Modifier.height(12.dp))

    Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(vertical = 4.dp)) {
        Image(
            painter = painterResource(R.drawable.img_correct_diamond),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 14.sp, color = Color.DarkGray)
    }
}


@Composable
fun ProductDetailRow(label: String, value: String) {
    Spacer(modifier = Modifier.height(12.dp))

    Divider(color = AppGray, thickness = 1.dp)

    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.End)
    }
}

data class DiscountProduct(val title: String, val price: String, val imageRes: Int)

@Composable
fun DiscountProductCard(product: DiscountProduct) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(220.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = { /* Favorite action */ }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_remove),
                        contentDescription = "Favorite",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(product.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(product.price, color = Color(0xFF7B3F00), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF7B3F00))
                    .clickable { /* Add to cart */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
