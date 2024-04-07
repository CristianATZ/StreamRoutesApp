package net.streamroutes.sreamroutesapp.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import net.streamroutes.sreamroutesapp.utils.CHANNEL_ID
import net.streamroutes.sreamroutesapp.utils.NOTIFICATION_ID
import net.streamroutes.sreamroutesapp.utils.NOTIFICATION_TITLE
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import net.streamroutes.sreamroutesapp.utils.VERBOSE_NOTIFICATION_CHANNEL_NAME

private const val TAG = "WorkerUtils"

@SuppressLint("MissingPermission")
fun makeStatusNotification(message: String, context: Context) {

    // Make a channel if necessary
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        // Add the channel
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

fun sleep(){
    try {
        Thread.sleep(3000, 0)
    } catch (e: InterruptedException) {
        Log.e("WOKER_UTILS", e.message.toString())
    }
}