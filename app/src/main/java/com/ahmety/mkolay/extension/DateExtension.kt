package com.ahmety.mkolay.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(dateFormat: String): String? = SimpleDateFormat(dateFormat, Locale.getDefault()).format(this)