package com.github.kimcore.riot.routes

import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.internal.Requester
import kotlinx.serialization.Serializable

class ThirdPartyCodeV4 internal constructor(private val requester: Requester) {
    suspend fun getThirdPartyCode(
        encryptedSummonerId: String,
        platform: Platform? = null,
        apiKey: String? = null
    ): String {
        return requester.get(
            "/lol/platform/v4/third-party-code/by-summoner/$encryptedSummonerId",
            null,
            platform,
            apiKey
        )
    }
}