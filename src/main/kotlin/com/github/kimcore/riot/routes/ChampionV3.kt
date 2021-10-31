package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class ChampionV3 internal constructor(private val requester: Requester) {
    suspend fun getChampionRotations(platform: Platform? = null, apiKey: String? = null): ChampionInfo {
        return requester.get("/lol/platform/v3/champion-rotations", null, platform, apiKey)
    }
}

@Serializable
data class ChampionInfo(
    val maxNewPlayerLevel: Int,
    val freeChampionIdsForNewPlayers: List<Int>,
    val freeChampionIds: List<Int>
)