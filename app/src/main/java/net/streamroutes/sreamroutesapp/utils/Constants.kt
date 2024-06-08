package net.streamroutes.sreamroutesapp.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Name of Notification Channel for verbose notifications of background work
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"
val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

val brush = Brush.verticalGradient(listOf(Color(0xFFE8AA42), Color(0xFFEACE43)))

object Constants {
    object ParkingApi {
        val BASE_URL = "https://rumapp.free.beeceptor.com/api/"
    }
}