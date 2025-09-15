package com.sora.sora.core.temp

import com.sora.sora.R
import com.sora.sora.features.dashboard.CategoryItemData

class TempCustomData {
    val categories = listOf(
        CategoryItemData(
            title = "Toys",
            iconRes = R.drawable.ic_cat_toy,
            colorCode1 = "#FADA7A",
            colorCode2 = "#F2C549"
        ),
        CategoryItemData(
            title = "Clothing",
            iconRes = R.drawable.ic_cat_polo_tshirt,
            colorCode1 = "#B7B1F2",
            colorCode2 = "#928BDD"
        ),
        CategoryItemData(
            title = "Baby Essentials",
            iconRes = R.drawable.ic_cat_essential,
            colorCode1 = "#FDB7EA",
            colorCode2 = "#DB95C8"
        ),
        CategoryItemData(
            title = "Cups & Mugs",
            iconRes = R.drawable.ic_cat_cup,
            colorCode1 = "#D0E8C5",
            colorCode2 = "#97C981"
        ),
        CategoryItemData(
            title = "Shoes & Socks",
            iconRes = R.drawable.ic_cat_shoe,
            colorCode1 = "#FFE3E3",
            colorCode2 = "#FFA0A0"
        ),
        CategoryItemData(
            title = "Robes & Towels",
            iconRes = R.drawable.ic_cat_towel,
            colorCode1 = "#C6E7FF",
            colorCode2 = "#89BBE1"
        ),
        CategoryItemData(
            title = "School Supplies",
            iconRes = R.drawable.ic_cat_school,
            colorCode1 = "#EDDFE0",
            colorCode2 = "#B18386"
        ),
        CategoryItemData(
            title = "Accessories",
            iconRes = R.drawable.ic_cat_accessaries,
            colorCode1 = "#E5D9F2",
            colorCode2 = "#B99CDB"
        ),
        CategoryItemData(
            title = "Gift Packs",
            iconRes = R.drawable.ic_cat_gift_new,
            colorCode1 = "#E0E5B6",
            colorCode2 = "#A6AD6A"
        ),
        CategoryItemData(
            title = "Special Offers",
            iconRes = R.drawable.ic_cat_special,
            colorCode1 = "#F7C8E0",
            colorCode2 = "#D992B6"
        )
    )
}


data class SeeAllModel(
    val title: String,
    val list: List<String>
)

