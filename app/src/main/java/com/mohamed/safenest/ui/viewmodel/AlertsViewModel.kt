package com.mohamed.safenest.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.AlertItem
import com.mohamed.domain.usecases.GetFireUseCase
import com.mohamed.domain.usecases.GetGasUseCase
import com.mohamed.domain.usecases.GetWaterUseCase
import com.mohamed.safenest.R
import com.mohamed.safenest.ui.handleError
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
    val errorState = mutableIntStateOf(R.string.empty)
    val isLoading = mutableStateOf(true)

    private val notificationHelper = NotificationHelper(application)
    private var previousWaterAlertsCount = 0
    private var previousFireAlertsCount = 0
    private var previousGasAlertsCount = 0

    fun getWaterAlerts(errorState: MutableIntState) {
        viewModelScope.launch {
            try {
                isLoading.value = false
                val response = getWaterUseCase.invoke()
                if (response.size > previousWaterAlertsCount && previousWaterAlertsCount > 0) {
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
                isLoading.value = false
                errorState.intValue = handleError(e)
            }
        }
    }

    fun getFireAlerts(errorState: MutableIntState) {
        viewModelScope.launch {
            try {
                val response = getFireUseCase.invoke()
                isLoading.value = false
                if (response.size > previousFireAlertsCount && previousFireAlertsCount > 0) {
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
                isLoading.value = false
                errorState.intValue = handleError(e)
            }
        }
    }

    fun getGasAlerts(errorState: MutableIntState) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getGasUseCase.invoke()

                isLoading.value = false
                if (response.size > previousGasAlertsCount && previousGasAlertsCount > 0) {
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
                isLoading.value = false

                errorState.intValue = handleError(e)
            }
        }
    }


}

