package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class LeagueV4 internal constructor(private val requester: Requester) {
    suspend fun getChallengerLeaguesByQueue(
        queue: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): LeagueListDTO {
        return requester.get("/lol/league/v4/challengerleagues/by-queue/$queue", null, platform, apiKey)
    }

    suspend fun getLeagueEntriesBySummonerID(
        encryptedSummonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): List<LeagueEntryDTO> {
        return requester.get("/lol/league/v4/entries/by-summoner/$encryptedSummonerId", null, platform, apiKey)
    }

    suspend fun getLeagueEntries(
        queue: String,
        tier: String,
        division: String,
        page: Int = 1,
        platform: Platform? = null,
        apiKey: String? = null
    ): List<LeagueEntryDTO> {
        return requester.get(
            "/lol/league/v4/entries/$queue/$tier/$division",
            mapOf("page" to page),
            platform,
            apiKey
        )
    }

    suspend fun getGrandmasterLeaguesByQueue(
        queue: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): LeagueListDTO {
        return requester.get("/lol/league/v4/grandmasterleagues/by-queue/$queue", null, platform, apiKey)
    }

    suspend fun getLeagueById(
        leagueId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): LeagueListDTO {
        return requester.get("/lol/league/v4/leagues/$leagueId", null, platform, apiKey)
    }

    suspend fun getMasterLeaguesByQueue(
        queue: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): LeagueListDTO {
        return requester.get("/lol/league/v4/masterleagues/by-queue/$queue", null, platform, apiKey)
    }
}

@Serializable
data class LeagueEntryDTO(
    val leagueId: String,
    val summonerId: String,
    val summonerName: String,
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotStreak: Boolean,
    val veteran: Boolean,
    val freshBlood: Boolean,
    val inactive: Boolean,
    val miniSeries: MiniSeriesDTO? = null
)

@Serializable
data class LeagueListDTO(
    val leagueId: String,
    val entries: List<LeagueItemDTO>,
    val tier: String,
    val name: String,
    val queue: String
)

@Serializable
data class LeagueItemDTO(
    val freshBlood: Boolean,
    val wins: Int,
    val summonerName: String,
    val miniSeries: MiniSeriesDTO,
    val inactive: Boolean,
    val veteran: Boolean,
    val hotStreak: Boolean,
    val rank: String,
    val leaguePoints: Int,
    val losses: Int,
    val summonerId: String
)

@Serializable
data class MiniSeriesDTO(
    val losses: Int,
    val progress: String,
    val target: Int,
    val wins: Int
)