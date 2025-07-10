package com.mohamed.safenest.ui.screens.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mohamed.safenest.R
import com.mohamed.safenest.ui.screens.MainActivity

class FirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCMService"
        private const val CHANNEL_ID = "Alerts"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            showNotification(it.title, it.body, remoteMessage.from)
        }

        // Check if message contains a data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            handleDataMessage(remoteMessage.data)
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        val alertType = data["alert_type"]
        val message = data["message"]

        when (alertType) {
            "flames" -> {
                showNotification(
                    title = "🔥 Fire Alert!",
                    body = message ?: "Fire detected in your area",
                    topic = "fire_alerts",
                    alertType = "flames"
                )
            }

            "water" -> {
                showNotification(
                    title = "💧 Water Alert!",
                    body = message ?: "Water leak detected",
                    topic = "water_alerts",
                    alertType = "waters"
                )
            }

            "gas" -> {
                showNotification(
                    title = "⚠️ Gas Alert!",
                    body = message ?: "Gas leak detected",
                    topic = "gas_alerts",
                    alertType = "gas"
                )
            }

            else -> {
                showNotification(
                    title = "SafeNest Alert",
                    body = message ?: "New alert received",
                    topic = "all_alerts"
                )
            }
        }
    }

    private fun showNotification(
        title: String?,
        body: String?,
        topic: String?,
        alertType: String? = null,
    ) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("notification_clicked", true)
            putExtra("alert_type", alertType ?: getAlertTypeFromTopic(topic))
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title ?: "SafeNest")
            .setContentText(body ?: "New alert received")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        when (alertType) {
            "flames" -> notificationBuilder.setColor(0xFFFF0000.toInt())
            "waters" -> notificationBuilder.setColor(0xFF0000FF.toInt())
            "gas" -> notificationBuilder.setColor(0xFFFFFF00.toInt())
            else -> notificationBuilder.setColor(0xFF00FF00.toInt())
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    private fun getAlertTypeFromTopic(topic: String?): String {
        return when {
            topic?.contains("flames") == true -> "flames"
            topic?.contains("waters") == true -> "waters"
            topic?.contains("gas") == true -> "gas"
            else -> "general"
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "SafeNest Alerts"
            val descriptionText = "Notifications for fire, water, and gas alerts"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableLights(true)
                enableVibration(true)
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }
}