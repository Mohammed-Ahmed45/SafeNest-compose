package com.mohamed.data.api.model

import com.mohamed.data.api.AlertDto
import retrofit2.http.GET

interface ApiServices {
    @GET("waters")
    suspend fun getWaterAlert(): List<AlertDto>

    @GET("flames")
    suspend fun getFireAlert(): List<AlertDto>

    @GET("gas")
    suspend fun getGasAlert(): List<AlertDto>
}