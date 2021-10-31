package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class SpectatorV4 internal constructor(private val requester: Requester) {
    suspend fun getCurrentGameInfo(encryptedSummonerId: String, platform: Platform? = null, apiKey: String? = null): CurrentGameInfo {
        return requester.get(
            "/lol/spectator/v4/active-games/by-summoner/$encryptedSummonerId",
            null, platform, apiKey
        )
    }
}

@Serializable
data class CurrentGameInfo(
    val gameId: Long,
    val gameType: String,
    val gameStartTime: Long,
    val mapId: Long,
    val gameLength: Long,
    val platformId: String,
    val gameMode: String,
    val bannedChampions: List<BannedChampion>,
    val gameQueueConfigId: Long,
    val observers: Observer,
    val participants: List<CurrentGameParticipant>
)

@Serializable
data class BannedChampion(
    val pickTurn: Int,
    val championId: Long,
    val teamId: Long
)

@Serializable
data class Observer(
    val encryptionKey: String
)

@Serializable
data class CurrentGameParticipant(
    val championId: Long,
    val perks: Perks,
    val profileIconId: Long,
    val bot: Boolean,
    val teamId: Long,
    val summonerName: String,
    val summonerId: String,
    val spell1Id: Long,
    val spell2Id: Long,
    val gameCustomizationObjects: List<GameCustomizationObject>
)

@Serializable
data class Perks(
    val perkIds: List<Long>,
    val perkStyle: Long,
    val perkSubStyle: Long
)

@Serializable
data class GameCustomizationObject(
    val category: String,
    val content: String
)