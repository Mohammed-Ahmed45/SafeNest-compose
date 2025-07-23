package com.mohamed.safenest.ui.screens.fcm

// NotificationHelper.kt
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.toColorInt
import com.mohamed.domain.model.AlertItem
import com.mohamed.safenest.ui.screens.MainActivity

class NotificationHelper(private val context: Context) {

    companion object {
        private const val WATER_CHANNEL_ID = "water_alerts"
        private const val FIRE_CHANNEL_ID = "fire_alerts"
        private const val GAS_CHANNEL_ID = "gas_alerts"

        private const val WATER_CHANNEL_NAME = "Water Alerts"
        private const val FIRE_CHANNEL_NAME = "Fire Alerts"
        private const val GAS_CHANNEL_NAME = "Gas Alerts"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Water alerts channel
            val waterChannel = NotificationChannel(
                WATER_CHANNEL_ID,
                WATER_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for water-related alerts"
                enableLights(true)
                lightColor = android.graphics.Color.BLUE
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            }

            val fireChannel = NotificationChannel(
                FIRE_CHANNEL_ID,
                FIRE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for fire-related alerts"
                enableLights(true)
                lightColor = android.graphics.Color.RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            }

            val gasChannel = NotificationChannel(
                GAS_CHANNEL_ID,
                GAS_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for gas-related alerts"
                enableLights(true)
                lightColor = android.graphics.Color.YELLOW
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            }

            notificationManager.createNotificationChannel(waterChannel)
            notificationManager.createNotificationChannel(fireChannel)
            notificationManager.createNotificationChannel(gasChannel)
        }
    }

    fun showWaterAlert(alert: AlertItem) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("alert_type", "waters")
            putExtra("notification_clicked", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, WATER_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Replace with water icon
            .setContentTitle("🚰 Water Alert!")
            .setContentText("Status: ${alert.status}")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Water Alert Detected!\n\nStatus: ${alert.status}\nTime: ${alert.createdAt ?: "Now"}")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setColor(android.graphics.Color.BLUE)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .build()

        notificationManager.notify(generateNotificationId(), notification)
    }

    fun showFireAlert(alert: AlertItem) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("alert_type", "flames")
            putExtra("notification_clicked", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, FIRE_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("🔥 Fire Alert!")
            .setContentText("Status: ${alert.status}")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Fire Alert Detected!\n\nStatus: ${alert.status}\nTime: ${alert.createdAt ?: "Now"}\n\n⚠️ Please check immediately!")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setColor(android.graphics.Color.RED)
            .setVibrate(longArrayOf(0, 300, 100, 300, 100, 300))
            .build()

        notificationManager.notify(generateNotificationId(), notification)
    }

    fun showGasAlert(alert: AlertItem) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("alert_type", "gas")
            putExtra("notification_clicked", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, GAS_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("⚠️ Gas Alert!")
            .setContentText("Status: ${alert.status}")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Gas Alert Detected!\n\nStatus: ${alert.status}\nTime: ${alert.createdAt ?: "Now"}\n\n🚨 Immediate attention required!")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setColor("#FFA500".toColorInt())
            .setVibrate(longArrayOf(0, 200, 100, 200, 100, 200, 100, 200))
            .build()

        notificationManager.notify(generateNotificationId(), notification)
    }

    private fun generateNotificationId(): Int {
        return System.currentTimeMillis().toInt()
    }
}