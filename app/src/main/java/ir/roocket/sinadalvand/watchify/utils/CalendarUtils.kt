package ir.roocket.sinadalvand.watchify.utils

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtils {

    fun Long.toDate(): Date? {
        return Calendar.getInstance().apply { timeInMillis = this@toDate }.time
    }

    fun Date.toFormat(dateFormat: String = "dd/MM/yyyy"): String {
        return SimpleDateFormat(dateFormat, Locale.US).format(this)
    }
}