package com.mohamed.domain.usecases

import com.mohamed.domain.model.AlertItem
import com.mohamed.domain.repositories.AlertRepo
import javax.inject.Inject

class GetFireUseCase @Inject constructor(
    private val alertRepository: AlertRepo,
) {
    suspend fun invoke(): List<AlertItem> {
        return alertRepository.getFireAlert()
    }

}