package com.example.activities

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notifications(activity: Activity){

    private val CHANNEL_ID = "appState"
    private val CHANNEL_NAME = "Application on focus"

    init {
        createNotificationChannel(activity)
    }

    val sleep = NotificationCompat.Builder(activity, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("pause")
        .setContentText("app sleeping")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
    val wakeUp = NotificationCompat.Builder(activity, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("resume")
        .setContentText("app waked up")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
    val notificationManager = NotificationManagerCompat.from(activity)

    private fun createNotificationChannel(activity: Activity) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = "Using app foreground or not"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}