package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.enums.Region
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class MatchV5 internal constructor(private val requester: Requester) {
    suspend fun getMatchIdsByPUUID(
        puuid: String,
        startTime: Long? = null,
        endTime: Long? = null,
        queue: String? = null,
        type: String? = null,
        start: Int = 0,
        count: Int = 20,
        region: Region? = null,
        apiKey: String? = null
    ): List<String> {
        return requester.get(
            "/lol/match/v5/matches/by-puuid/$puuid/ids", mapOf(
                "startTime" to startTime,
                "endTime" to endTime,
                "queue" to queue,
                "type" to type,
                "start" to start,
                "count" to count
            ), region, apiKey
        )
    }

    suspend fun getMatch(
        matchId: String,
        region: Region? = null,
        apiKey: String? = null
    ): MatchDto {
        return requester.get("/lol/match/v5/matches/$matchId", null, region, apiKey)
    }

    suspend fun getMatchTimeline(
        matchId: String,
        region: Region? = null,
        apiKey: String? = null
    ): MatchTimelineDto {
        return requester.get("/lol/match/v5/matches/$matchId/timeline", null, region, apiKey)
    }
}

@Serializable
data class MatchDto(
    val metadata: MetadataDto,
    val info: InfoDto
)

@Serializable
data class MetadataDto(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)

@Serializable
data class InfoDto(
    val gameCreation: Long,
    val gameDuration: Long,
    val gameEndTimestamp: Long,
    val gameId: Long,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Long,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val participants: List<ParticipantDto>,
    val platformId: String,
    val queueId: Int,
    val teams: List<MatchTeamDto>,
    val tournamentCode: String
)

@Serializable
data class ParticipantDto(
    val assists: Int,
    val baronKills: Int,
    val bountyLevel: Int,
    val champExperience: Int,
    val champLevel: Int,
    val championId: Int,
    val championName: String,
    val championTransform: Int,
    val consumablesPurchased: Int,
    val damageDealtToBuildings: Int,
    val damageDealtToObjectives: Int,
    val damageDealtToTurrets: Int,
    val damageSelfMitigated: Int,
    val deaths: Int,
    val detectorWardsPlaced: Int,
    val doubleKills: Int,
    val dragonKills: Int,
    val firstBloodAssist: Boolean,
    val firstBloodKill: Boolean,
    val firstTowerAssist: Boolean,
    val firstTowerKill: Boolean,
    val gameEndedInEarlySurrender: Boolean,
    val gameEndedInSurrender: Boolean,
    val goldEarned: Int,
    val goldSpent: Int,
    val individualPosition: String,
    val inhibitorKills: Int,
    val inhibitorTakedowns: Int,
    val inhibitorsLost: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val itemsPurchased: Int,
    val killingSprees: Int,
    val kills: Int,
    val lane: String,
    val largestCriticalStrike: Int,
    val largestKillingSpree: Int,
    val largestMultiKill: Int,
    val longestTimeSpentLiving: Int,
    val magicDamageDealt: Int,
    val magicDamageDealtToChampions: Int,
    val magicDamageTaken: Int,
    val neutralMinionsKilled: Int,
    val nexusKills: Int,
    val nexusTakedowns: Int,
    val nexusLost: Int,
    val objectivesStolen: Int,
    val objectivesStolenAssists: Int,
    val participantId: Int,
    val pentaKills: Int,
    val perks: PerksDto,
    val physicalDamageDealt: Int,
    val physicalDamageDealtToChampions: Int,
    val physicalDamageTaken: Int,
    val profileIcon: Int,
    val puuid: String,
    val quadraKills: Int,
    val riotIdName: String,
    val riotIdTagline: String,
    val role: String,
    val sightWardsBoughtInGame: Int,
    val spell1Casts: Int,
    val spell2Casts: Int,
    val spell3Casts: Int,
    val spell4Casts: Int,
    val summoner1Casts: Int,
    val summoner1Id: Int,
    val summoner2Casts: Int,
    val summoner2Id: Int,
    val summonerId: String,
    val summonerLevel: Int,
    val summonerName: String,
    val teamEarlySurrendered: Boolean,
    val teamId: Int,
    val teamPosition: String,
    val timeCCingOthers: Int,
    val timePlayed: Int,
    val totalDamageDealt: Int,
    val totalDamageDealtToChampions: Int,
    val totalDamageShieldedOnTeammates: Int,
    val totalDamageTaken: Int,
    val totalHeal: Int,
    val totalHealsOnTeammates: Int,
    val totalMinionsKilled: Int,
    val totalTimeCCDealt: Int,
    val totalTimeSpentDead: Int,
    val totalUnitsHealed: Int,
    val tripleKills: Int,
    val trueDamageDealt: Int,
    val trueDamageDealtToChampions: Int,
    val trueDamageTaken: Int,
    val turretKills: Int,
    val turretTakedowns: Int,
    val turretsLost: Int,
    val unrealKills: Int,
    val visionScore: Int,
    val visionWardsBoughtInGame: Int,
    val wardsKilled: Int,
    val wardsPlaced: Int,
    val win: Boolean
)

@Serializable
data class PerksDto(
    val statPerks: PerkStatsDto,
    val styles: List<PerkStyleDto>
)

@Serializable
data class PerkStatsDto(
    val defense: Int,
    val flex: Int,
    val offense: Int
)

@Serializable
data class PerkStyleDto(
    val description: String,
    val selections: List<PerkStyleSelectionDto>,
    val style: Int
)

@Serializable
data class PerkStyleSelectionDto(
    val perk: Int,
    val var1: Int,
    val var2: Int,
    val var3: Int
)

@Serializable
data class MatchTeamDto(
    val bans: List<BanDto>,
    val objectives: ObjectivesDto,
    val teamId: Int,
    val win: Boolean
)

@Serializable
data class BanDto(
    val championId: Int,
    val pickTurn: Int
)

@Serializable
data class ObjectivesDto(
    val baron: ObjectiveDto,
    val champion: ObjectiveDto,
    val dragon: ObjectiveDto,
    val inhibitor: ObjectiveDto,
    val riftHerald: ObjectiveDto,
    val tower: ObjectiveDto
)

@Serializable
data class ObjectiveDto(
    val first: Boolean,
    val kills: Int
)

@Serializable
data class MatchTimelineDto(
    val metadata: MetadataDto,
    val info: TimelineInfoDto,
)

@Serializable
data class TimelineInfoDto(
    val frameInterval: Long,
    val frames: List<MatchFrameDto>
)

@Serializable
data class MatchFrameDto(
    val participantFrames: Map<String, MatchParticipantFrameDto>,
    val events: List<MatchEventDto>,
    val timestamp: Long
)

@Serializable
data class MatchParticipantFrameDto(
    val championStats: MatchChampionStatsDto,
    val currentGold: Int,
    val damageStats: MatchDamageStatsDto,
    val goldPerSecond: Int,
    val jungleMinionsKilled: Int,
    val level: Int,
    val minionsKilled: Int,
    val participantId: Int,
    val position: MatchPositionDto,
    val timeEnemySpentControlled: Int,
    val totalGold: Int,
    val xp: Int
)

@Serializable
data class MatchChampionStatsDto(
    val abilityHaste: Int,
    val abilityPower: Int,
    val armor: Int,
    val armorPen: Int,
    val armorPenPercent: Int,
    val attackDamage: Int,
    val attackSpeed: Int,
    val bonusArmorPenPercent: Int,
    val bonusMagicPenPercent: Int,
    val ccReduction: Int,
    val cooldownReduction: Int,
    val health: Int,
    val healthMax: Int,
    val healthRegen: Int,
    val lifesteal: Int,
    val magicPen: Int,
    val magicPenPercent: Int,
    val magicResist: Int,
    val movementSpeed: Int,
    val omnivamp: Int,
    val physicalVamp: Int,
    val power: Int,
    val powerMax: Int,
    val powerRegen: Int,
    val spellVamp: Int
)

@Serializable
data class MatchDamageStatsDto(
    val magicDamageDone: Int,
    val magicDamageDoneToChampions: Int,
    val magicDamageTaken: Int,
    val physicalDamageDone: Int,
    val physicalDamageDoneToChampions: Int,
    val physicalDamageTaken: Int,
    val totalDamageDone: Int,
    val totalDamageDoneToChampions: Int,
    val totalDamageTaken: Int,
    val trueDamageDone: Int,
    val trueDamageDoneToChampions: Int,
    val trueDamageTaken: Int
)

@Serializable
data class MatchPositionDto(
    val x: Int,
    val y: Int
)

@Serializable
data class MatchEventDto(
    val type: String,
    val timestamp: Long,
    val laneType: String? = null,
    val skillSlot: Int? = null,
    val ascendedType: String? = null,
    val creatorId: Int? = null,
    val afterId: Int? = null,
    val eventType: String? = null,
    val levelUpType: String? = null,
    val wardType: String? = null,
    val participantId: Int? = null,
    val towerType: String? = null,
    val itemId: Int? = null,
    val beforeId: Int? = null,
    val pointCaptured: String? = null,
    val monsterType: String? = null,
    val monsterSubType: String? = null,
    val teamId: Int? = null,
    val assistingParticipantIds: List<Int>? = null,
    val buildingType: String? = null,
    val victimId: Int? = null,
    val killerId: Int? = null,
    val killStreakLength: Int? = null,
    val position: MatchPositionDto? = null,
    val victimDamageDealt: List<MatchEventVictimDamageDto>? = null,
    val victimDamageReceived: List<MatchEventVictimDamageDto>? = null
)

@Serializable
data class MatchEventVictimDamageDto(
    val basic: Boolean,
    val magicDamage: Int,
    val name: String,
    val participantId: Int,
    val physicalDamage: Int,
    val spellName: String,
    val spellSlot: Int,
    val trueDamage: Int,
    val type: String
)