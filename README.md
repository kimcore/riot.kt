# riot.kt
[![image](https://img.shields.io/jitpack/v/github/kimcore/riot.kt?style=flat-square)](https://github.com/kimcore/riot.kt/releases)
[![image](https://img.shields.io/github/license/kimcore/riot.kt?style=flat-square)](https://github.com/kimcore/riot.kt/blob/master/LICENSE)

Simple Riot API wrapper for Kotlin, built on coroutines

Currently supports:
* CHAMPION-MASTERY-V4
* CHAMPION-V3
* CLASH-V1
* LEAGUE-V4
* LOL-STATUS-V4
* MATCH-V5
* SPECTATOR-V4
* SUMMONER-V4
* THIRD-PARTY-CODE-V4

## Installation
Kotlin DSL
```gradle
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.kimcore:riot.kt:1.0")
}
```
Groovy
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.kimcore:riot.kt:1.0'
}
```
## Setup
```kotlin
import com.github.kimcore.riot.RiotAPI
import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.enums.Region

RiotAPI.setApiKey("RGAPI-xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")

// optional, defaults to KR, ASIA
RiotAPI.setDefaultPlatform(Platform.KR)
RiotAPI.setDefaultRegion(Region.ASIA)
```
## Usage
```kotlin
// get summoner by name
val summoner = RiotAPI.summoner.getSummonerByName("Hide on bush")

// get match list
val matches = RiotAPI.match.getMatchIdsByPUUID(summoner.puuid)

// get summoner's solo rank tier
val leagueEntries = RiotAPI.league.getLeagueEntriesBySummonerID(summoner.id)
val soloTier = leagueEntries.first { it.queueType == "RANKED_SOLO_5x5" }.tier
```
If error occurs, `RiotException` will be thrown.


```kotlin
try {
    RiotAPI.summoner.getSummonerByName("this summoner does not exist!")
} catch(e: RiotException) {
    println(e.statusCode) // 404
    println(e.statusMessage) // Data not found
}
```
