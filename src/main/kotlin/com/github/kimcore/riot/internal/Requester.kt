package com.github.kimcore.riot.internal

import com.github.kimcore.riot.RiotAPI
import com.github.kimcore.riot.enums.Platform
import com.github.kimcore.riot.enums.Region
import com.github.kimcore.riot.errors.RiotException
import com.github.kimcore.riot.errors.StatusWrapper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

internal class Requester {
    private val client = HttpClient(CIO) {
        install(JsonFeature)
    }

    suspend inline fun <reified T> get(
        path: String,
        parameters: Map<String, Any?>?,
        platform: Platform?,
        apiKey: String?
    ): T = get((platform ?: RiotAPI.defaultPlatform).uri, path, parameters, apiKey)

    suspend inline fun <reified T> get(
        path: String,
        parameters: Map<String, Any?>?,
        region: Region?,
        apiKey: String?
    ): T = get((region ?: RiotAPI.defaultRegion).uri, path, parameters, apiKey)

    @Throws(RiotException::class)
    private suspend inline fun <reified T> get(
        uri: String,
        path: String,
        parameters: Map<String, Any?>?,
        apiKey: String?
    ): T {
        return runCatching {
            return@runCatching client.get<T> {
                url.takeFrom("https://$uri$path")
                parameters?.forEach {
                    if (it.value != null) {
                        url.parameters[it.key] = it.value.toString()
                    }
                }
                header("X-Riot-Token", (apiKey ?: RiotAPI.apiKey ?: error("api key is not specified")))
            }
        }.getOrElse {
            throw when (it) {
                is ClientRequestException -> {
                    val wrapper = Json.decodeFromString(
                        StatusWrapper.serializer(),
                        it.response.readText()
                    )
                    RiotException(wrapper.status.message, wrapper.status.statusCode)
                }
                else -> it
            }
        }
    }
}