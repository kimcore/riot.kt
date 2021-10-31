package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class SummonerV4 internal constructor(private val requester: Requester) {
    suspend fun getSummonerByAccount(
        encryptedAccountId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): SummonerDTO {
        return requester.get(
            "/lol/summoner/v4/summoners/by-account/$encryptedAccountId",
            null, platform, apiKey
        )
    }

    suspend fun getSummonerByName(
        summonerName: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): SummonerDTO {
        return requester.get("/lol/summoner/v4/summoners/by-name/$summonerName",
            null, platform, apiKey
        )
    }

    suspend fun getSummonerByPUUID(
        encryptedPUUID: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): SummonerDTO {
        return requester.get(
            "/lol/summoner/v4/summoners/by-puuid/$encryptedPUUID",
            null, platform, apiKey
        )
    }

    suspend fun getSummonerByID(
        encryptedSummonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): SummonerDTO {
        return requester.get(
            "/lol/summoner/v4/summoners/$encryptedSummonerId",
            null, platform, apiKey
        )
    }
}

@Serializable
data class SummonerDTO(
    val accountId: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String,
    val summonerLevel: Int
)