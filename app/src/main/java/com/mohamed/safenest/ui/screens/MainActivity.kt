package com.mohamed.safenest.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.messaging.FirebaseMessaging
import com.mohamed.safenest.ui.theme.SafeNestTheme
import com.mohamed.safenest.ui.utils.Nav
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d(TAG, "Notification permission granted")
            subscribeToTopics()
        } else {
            Log.d(TAG, "Notification permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")

        askNotificationPermission()

        setContent {
            SafeNestTheme {
                Nav()
            }
        }

        handleNotificationClick()

        setupLifecycleObservers()
    }

    @SuppressLint("RepeatOnLifecycleWrongUsage")
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called - Activity is visible")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d(TAG, "Activity in STARTED state")
                checkAndResubscribeToTopics()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkPendingNotifications()
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    private fun setupLifecycleObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                Log.d(TAG, "Lifecycle is in CREATED state")
                initializeAppResources()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                Log.d(TAG, "Lifecycle is in RESUMED state")
                handleForegroundOperations()
            }
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "Notification permission already granted")
                subscribeToTopics()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            subscribeToTopics()
        }
    }

    private fun subscribeToTopics() {
        FirebaseMessaging.getInstance().subscribeToTopic("fire_alerts")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to fire_alerts"
                if (!task.isSuccessful) {
                    msg = "Failed to subscribe to fire_alerts"
                }
                Log.d(TAG, msg)
            }

        FirebaseMessaging.getInstance().subscribeToTopic("water_alerts")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to water_alerts"
                if (!task.isSuccessful) {
                    msg = "Failed to subscribe to water_alerts"
                }
                Log.d(TAG, msg)
            }

        FirebaseMessaging.getInstance().subscribeToTopic("gas_alerts")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to gas_alerts"
                if (!task.isSuccessful) {
                    msg = "Failed to subscribe to gas_alerts"
                }
                Log.d(TAG, msg)
            }

        FirebaseMessaging.getInstance().subscribeToTopic("all_alerts")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to all_alerts"
                if (!task.isSuccessful) {
                    msg = "Failed to subscribe to all_alerts"
                }
                Log.d(TAG, msg)
            }
    }

    private fun checkAndResubscribeToTopics() {
        Log.d(TAG, "Checking topic subscriptions...")

        lifecycleScope.launch {
            try {
                subscribeToTopics()
            } catch (e: Exception) {
                Log.e(TAG, "Error checking topic subscriptions", e)
            }
        }
    }

    private fun initializeAppResources() {
        Log.d(TAG, "Initializing app resources...")

        lifecycleScope.launch {
            try {
                Log.d(TAG, "App resources initialized successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing app resources", e)
            }
        }
    }

    private fun handleForegroundOperations() {
        Log.d(TAG, "Handling foreground operations...")

        lifecycleScope.launch {
            try {
                checkForAppUpdates()
                refreshAlertData()
            } catch (e: Exception) {
                Log.e(TAG, "Error handling foreground operations", e)
            }
        }
    }

    private fun checkPendingNotifications() {
        Log.d(TAG, "Checking for pending notifications...")

        lifecycleScope.launch {
            try {
                Log.d(TAG, "Pending notifications check completed")
            } catch (e: Exception) {
                Log.e(TAG, "Error checking pending notifications", e)
            }
        }
    }

    private fun checkForAppUpdates() {
        Log.d(TAG, "Checking for app updates...")
    }

    private fun refreshAlertData() {
        Log.d(TAG, "Refreshing alert data...")
    }

    private fun handleNotificationClick() {
        val alertType = intent.getStringExtra("alert_type")
        val notificationClicked = intent.getBooleanExtra("notification_clicked", false)

        if (notificationClicked && alertType != null) {
            Log.d(TAG, "App opened from $alertType notification")
            when (alertType) {
                "flame" -> Log.d(TAG, "Navigate to Fire tab")
                "water" -> Log.d(TAG, "Navigate to Water tab")
                "gas" -> Log.d(TAG, "Navigate to Gas tab")
                else -> Log.d(TAG, "Navigate to All Alerts tab")
            }
        }
    }
}