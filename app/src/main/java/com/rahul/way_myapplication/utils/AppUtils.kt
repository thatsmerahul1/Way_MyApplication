package com.rahul.way_myapplication.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

object AppUtils {
    fun formatTimeInMillisToDateString(timeInMillis: Long, dateFormat: String): String {
        val dateFormatInstance = SimpleDateFormat(dateFormat, Locale.getDefault())
        val date = Date(timeInMillis)
        return dateFormatInstance.format(date)
    }

    fun roundToNearestHalf(value: Double):Double {

        return (value * 2).roundToInt() /2.0
    }
}