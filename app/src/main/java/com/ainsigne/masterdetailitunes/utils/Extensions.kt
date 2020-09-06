package com.ainsigne.masterdetailitunes.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * string formatter for date
 */
@SuppressLint("ConstantLocale")
val stringFormatter = SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.getDefault())


/**
 * Returns the date string in formatted
 */
fun Date.toStringFormatFull() : String
{
    return stringFormatter.format(this)
}

