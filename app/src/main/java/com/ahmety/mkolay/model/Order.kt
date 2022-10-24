package com.ahmety.mkolay.model

data class Order(
    val id: String,
    val picUrl: String,
    val itemName: String,
    val itemPrice: Double,
    val itemAmount: String,
    val itemQuantatiy: String
)
