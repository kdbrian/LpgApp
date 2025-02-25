package com.example.lpg.android.util

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Double.formatTo2DecimalPlaces(): String {
    return String.format(Locale.getDefault(), "%.2f", this)
}

@SuppressLint("NewApi")
fun LocalDateTime.formatToDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}

