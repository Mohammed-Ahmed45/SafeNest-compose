package com.mohamed.domain.repositories

import com.mohamed.domain.model.AlertItem

interface AlertRepo {
    suspend fun getFireAlert(): List<AlertItem>
    suspend fun getWaterAlert(): List<AlertItem>
    suspend fun getGasAlert(): List<AlertItem>
}