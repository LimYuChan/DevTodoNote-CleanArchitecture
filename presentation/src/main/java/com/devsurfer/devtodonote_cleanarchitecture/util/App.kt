package com.devsurfer.devtodonote_cleanarchitecture.util

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.devsurfer.devtodonote_cleanarchitecture.util.notification.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.CHANNEL_ID,
            "D-Day",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Displays the remaining period of work."

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}