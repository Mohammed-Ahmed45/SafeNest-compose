package com.mohamed.data.repositories

import com.mohamed.data.api.model.ApiServices
import com.mohamed.domain.model.AlertItem
import com.mohamed.domain.repositories.AlertRepo
import javax.inject.Inject

class AlertRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : AlertRepo {
    override suspend fun getFireAlert(): List<AlertItem> {
        return apiServices.getFireAlert().map { alertDto ->
            alertDto.toAlertList()
        }
    }

    override suspend fun getWaterAlert(): List<AlertItem> {
        return apiServices.getWaterAlert().map { alertDto ->
            alertDto.toAlertList()
        }
    }

    override suspend fun getGasAlert(): List<AlertItem> {
        return apiServices.getGasAlert().map { alertDto ->
            alertDto.toAlertList()
        }
    }
}