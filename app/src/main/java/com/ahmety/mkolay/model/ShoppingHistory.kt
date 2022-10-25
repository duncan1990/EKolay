package com.ahmety.mkolay.model

import java.util.Date

data class ShoppingHistory(
    val id: String,
    val marketName: String,
    val date: Date,
    val price: Double
)
