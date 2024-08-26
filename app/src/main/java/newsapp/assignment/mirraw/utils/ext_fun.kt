package newsapp.assignment.mirraw.utils

import okhttp3.internal.UTC
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun Date.toSpecialString(): String{
    val startCalendar = Calendar.getInstance()
    startCalendar.time = this
    startCalendar[Calendar.HOUR_OF_DAY] = 0
    startCalendar[Calendar.MINUTE] = 0
    startCalendar[Calendar.SECOND] = 0
    startCalendar[Calendar.MILLISECOND] = 0

    val endCalendar = Calendar.getInstance()
    endCalendar[Calendar.HOUR_OF_DAY] = 0
    endCalendar[Calendar.MINUTE] = 0
    endCalendar[Calendar.SECOND] = 0
    endCalendar[Calendar.MILLISECOND] = 0

    val diffInMillis = abs(startCalendar.timeInMillis - endCalendar.timeInMillis)
    val daysBetween = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
    val formatter = SimpleDateFormat("hh:mm a • MMM dd", Locale.ENGLISH)
    return if(daysBetween == 0L) "Today" else if(daysBetween == 1L) "Yesterday" else formatter.format(this)
}

fun String.urlEncode(): String{
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}