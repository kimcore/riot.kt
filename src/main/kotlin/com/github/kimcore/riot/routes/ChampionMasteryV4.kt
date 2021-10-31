package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class ChampionMasteryV4 internal constructor(private val requester: Requester) {
    suspend fun getChampionMasteryEntries(
        encryptedSummonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): List<ChampionMasteryDto> {
        return requester.get(
            "/lol/champion-mastery/v4/champion-masteries/by-summoner/$encryptedSummonerId",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getChampionMastery(
        encryptedSummonerId: String,
        championId: Long,
        platform: Platform? = null,
        apiKey: String? = null
    ): ChampionMasteryDto {
        return requester.get(
            "/lol/champion-mastery/v4/champion-masteries/by-summoner/$encryptedSummonerId/by-champion/$championId",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getTotalScore(
        encryptedSummonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): Int {
        return requester.get(
            "/lol/champion-mastery/v4/scores/by-summoner/$encryptedSummonerId",
            null,
            platform,
            apiKey
        )
    }
}

@Serializable
data class ChampionMasteryDto(
    val championPointsUntilNextLevel: Long,
    val chestGranted: Boolean,
    val championId: Long,
    val lastPlayTime: Long,
    val championLevel: Int,
    val summonerId: String,
    val championPoints: Int,
    val championPointsSinceLastLevel: Long,
    val tokensEarned: Int
)