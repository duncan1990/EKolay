package com.ahmety.mkolay.ui.canteen

import androidx.lifecycle.ViewModel

class CanteenViewModel : ViewModel() {

    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

}