package com.github.kimcore.riot

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.enums.Region
import com.github.kimcore.riot.internal.Requester
import com.github.kimcore.riot.routes.*

object RiotAPI {
    internal var apiKey: String? = null
    internal var defaultPlatform = Platform.KR
    internal var defaultRegion = Region.ASIA

    private val requester = Requester()

    fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    fun setDefaultPlatform(platform: Platform) {
        this.defaultPlatform = platform
    }

    fun setDefaultRegion(region: Region) {
        this.defaultRegion = region
    }

//    val account = AccountV1(requester)
    val championMastery = ChampionMasteryV4(requester)
    val champion = ChampionV3(requester)
    val clash = ClashV1(requester)
    val league = LeagueV4(requester)
    val lolStatus = LolStatusV4(requester)
    val match = MatchV5(requester)
    val spectator = SpectatorV4(requester)
    val summoner = SummonerV4(requester)
    val thirdPartyCode = ThirdPartyCodeV4(requester)
}