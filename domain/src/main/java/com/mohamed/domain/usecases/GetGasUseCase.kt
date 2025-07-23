package com.mohamed.domain.usecases

import com.mohamed.domain.model.AlertItem
import com.mohamed.domain.repositories.AlertRepo
import javax.inject.Inject

class GetGasUseCase @Inject constructor(
    private val alertRepo: AlertRepo,
) {
    suspend fun invoke(): List<AlertItem> {
        return alertRepo.getGasAlert()
    }
}