package com.mohamed.safenest.data.api

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.AlertItem
import com.mohamed.domain.usecases.GetFireUseCase
import com.mohamed.domain.usecases.GetGasUseCase
import com.mohamed.domain.usecases.GetWaterUseCase
import com.mohamed.safenest.ui.screens.fcm.NotificationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertsViewModel @Inject constructor(
    application: Application,
    private val getFireUseCase: GetFireUseCase,
    private val getGasUseCase: GetGasUseCase,
    private val getWaterUseCase: GetWaterUseCase,
) : AndroidViewModel(application) {

    val waterAlertsList = mutableStateListOf<AlertItem>()
    val fireAlertList = mutableStateListOf<AlertItem>()
    val gasAlertList = mutableStateListOf<AlertItem>()

    private val notificationHelper = NotificationHelper(application)
    private var previousWaterAlertsCount = 0
    private var previousFireAlertsCount = 0
    private var previousGasAlertsCount = 0

    fun getWaterAlerts() {
        viewModelScope.launch {
            try {
                val response = getWaterUseCase.invoke()

                // Check for new alerts
                if (response.size > previousWaterAlertsCount && previousWaterAlertsCount > 0) {
                    // New water alert detected
                    val newAlerts = response.takeLast(response.size - previousWaterAlertsCount)
                    newAlerts.forEach { alert ->
                        notificationHelper.showWaterAlert(alert)
                        Log.d(
                            "AlertsViewModel",
                            "New water alert notification sent: ${alert.status}"
                        )
                    }
                }

                previousWaterAlertsCount = response.size
                waterAlertsList.clear()
                waterAlertsList.addAll(response)
            } catch (e: Exception) {
                Log.e("AlertsViewModel", "Error fetching water alerts: ${e.message}", e)
            }
        }
    }

    fun getFireAlerts() {
        viewModelScope.launch {
            try {
                val response = getFireUseCase.invoke()

                // Check for new alerts
                if (response.size > previousFireAlertsCount && previousFireAlertsCount > 0) {
                    // New fire alert detected
                    val newAlerts = response.takeLast(response.size - previousFireAlertsCount)
                    newAlerts.forEach { alert ->
                        notificationHelper.showFireAlert(alert)
                        Log.d(
                            "AlertsViewModel",
                            "New fire alert notification sent: ${alert.status}"
                        )
                    }
                }

                previousFireAlertsCount = response.size
                fireAlertList.clear()
                fireAlertList.addAll(response)
            } catch (e: Exception) {
                Log.e("AlertsViewModel", "Error fetching fire alerts: ${e.message}", e)
            }
        }
    }

    fun getGasAlerts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getGasUseCase.invoke()

                // Check for new alerts
                if (response.size > previousGasAlertsCount && previousGasAlertsCount > 0) {
                    // New gas alert detected
                    val newAlerts = response.takeLast(response.size - previousGasAlertsCount)
                    newAlerts.forEach { alert ->
                        notificationHelper.showGasAlert(alert)
                        Log.d("AlertsViewModel", "New gas alert notification sent: ${alert.status}")
                    }
                }

                previousGasAlertsCount = response.size
                gasAlertList.clear()
                gasAlertList.addAll(response)
            } catch (e: Exception) {
                Log.e("AlertsViewModel", "Error fetching gas alerts: ${e.message}", e)
            }
        }
    }

    // Initialize counts on first load
    fun initializeAlertCounts() {
        viewModelScope.launch {
            try {
                // Get initial counts without triggering notifications
                val waterResponse = getWaterUseCase.invoke()
                val fireResponse = getFireUseCase.invoke()
                val gasResponse = getGasUseCase.invoke()

                previousWaterAlertsCount = waterResponse.size
                previousFireAlertsCount = fireResponse.size
                previousGasAlertsCount = gasResponse.size

                waterAlertsList.clear()
                waterAlertsList.addAll(waterResponse)
                fireAlertList.clear()
                fireAlertList.addAll(fireResponse)
                gasAlertList.clear()
                gasAlertList.addAll(gasResponse)

                Log.d(
                    "AlertsViewModel",
                    "Initial counts - Water: $previousWaterAlertsCount, Fire: $previousFireAlertsCount, Gas: $previousGasAlertsCount"
                )
            } catch (e: Exception) {
                Log.e("AlertsViewModel", "Error initializing alert counts: ${e.message}", e)
            }
        }
    }

    // Method to refresh all alerts and check for new ones
//    fun refreshAllAlerts() {
//        getWaterAlerts()
//        getFireAlerts()
//        getGasAlerts()
//    }
}

