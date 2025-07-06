package com.mohamed.data.api

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.AlertItem

data class AlertDto(

	@field:SerializedName("sensor_id")
	val sensorId: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("guidance")
	val guidance: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("status")
	val status: String? = null,
) {
    fun toAlertList(): AlertItem {
        return AlertItem(
            sensorId = sensorId,
            updatedAt = updatedAt,
            guidance = guidance,
            icon = icon,
            createdAt = createdAt,
            id = id,
            type = type,
            status = status
        )
    }
}