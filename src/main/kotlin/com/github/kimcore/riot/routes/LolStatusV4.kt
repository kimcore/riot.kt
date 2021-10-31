package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LolStatusV4 internal constructor(private val requester: Requester) {
    suspend fun getPlatformData(platform: Platform? = null, apiKey: String? = null): PlatformDataDto {
        return requester.get("/lol/status/v4/platform-data", null, platform, apiKey)
    }
}

@Serializable
data class PlatformDataDto(
    val id: String,
    val name: String,
    val locales: List<String>,
    val maintenances: List<StatusDto>,
    val incidents: List<StatusDto>
)

@Serializable
data class StatusDto(
    val id: Int,
    @SerialName("maintenance_status")
    val maintenanceStatus: String,
    @SerialName("incident_severity")
    val incidentSeverity: String,
    val titles: List<ContentDto>,
    val updates: List<UpdateDto>,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("archive_at")
    val archiveAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val platforms: List<String>
)

@Serializable
data class ContentDto(
    val locale: String,
    val content: String
)

@Serializable
data class UpdateDto(
    val id: Int,
    val author: String,
    val publish: Boolean,
    @SerialName("publish_locations")
    val publishLocations: List<String>,
    val translations: List<ContentDto>,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)