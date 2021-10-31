package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Region
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class AccountV1 internal constructor(private val requester: Requester) {
    suspend fun getAccountByPUUID(puuid: String, region: Region? = null, apiKey: String? = null): AccountDto {
        return requester.get("/riot/account/v1/accounts/by-puuid/$puuid", null, region, apiKey)
    }

    suspend fun getAccountByRiotID(
        gameName: String,
        tagLine: String,
        region: Region? = null,
        apiKey: String? = null
    ): AccountDto {
        return requester.get("/riot/account/v1/accounts/by-riot-id/$gameName/$tagLine", null, region, apiKey)
    }

    suspend fun getActiveShard(
        game: String,
        puuid: String,
        region: Region? = null,
        apiKey: String? = null
    ): ActiveShardDto {
        return requester.get("/riot/account/v1/active-shards/by-game/$game/by-puuid/$puuid", null, region, apiKey)
    }
}

@Serializable
data class AccountDto(
    val puuid: String,
    val gameName: String? = null,
    val tagLine: String? = null
)

@Serializable
data class ActiveShardDto(
    val puuid: String,
    val game: String,
    val activeShard: String
)