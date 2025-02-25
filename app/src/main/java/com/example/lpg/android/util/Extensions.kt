package com.example.lpg.android.util

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Double.formatTo2DecimalPlaces(): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance().format(this)
    return String
        .format(Locale.getDefault(), currencyFormatter.replace("$", ""), this)
}

@SuppressLint("NewApi")
fun LocalDateTime.formatToDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}

