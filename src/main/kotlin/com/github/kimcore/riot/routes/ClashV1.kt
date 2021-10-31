package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class ClashV1 internal constructor(private val requester: Requester) {
    suspend fun getClashPlayers(
        summonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): List<PlayerDto> {
        return requester.get(
            "/lol/clash/v1/players/by-summoner/$summonerId",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getClashTeam(
        teamId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): ClashTeamDto {
        return requester.get(
            "/lol/clash/v1/teams/$teamId",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getTournaments(
        platform: Platform? = null,
        apiKey: String? = null
    ): List<TournamentDto> {
        return requester.get(
            "/lol/clash/v1/tournaments",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getTournamentByTeamID(
        teamId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): TournamentDto {
        return requester.get(
            "/lol/clash/v1/tournaments/by-team/$teamId",
            null,
            platform,
            apiKey
        )
    }

    suspend fun getTournamentByID(
        tournamentId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): TournamentDto {
        return requester.get(
            "/lol/clash/v1/tournaments/$tournamentId",
            null,
            platform,
            apiKey
        )
    }
}

@Serializable
data class PlayerDto(
    val summonerId: String,
    val teamId: String,
    val position: String,
    val role: String
)

@Serializable
data class ClashTeamDto(
    val id: String,
    val tournamentId: Int,
    val name: String,
    val iconId: Int,
    val tier: Int,
    val captain: String,
    val abbreviation: String,
    val players: List<PlayerDto>
)

@Serializable
data class TournamentDto(
    val id: Int,
    val themeId: Int,
    val nameKey: String,
    val nameKeySecondary: String,
    val schedule: List<TournamentPhaseDto>
)

@Serializable
data class TournamentPhaseDto(
    val id: Int,
    val registrationTime: Long,
    val startTime: Long,
    val cancelled: Boolean
)