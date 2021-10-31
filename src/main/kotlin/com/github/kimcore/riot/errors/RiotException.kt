package com.github.kimcore.riot.errors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("CanBeParameter", "MemberVisibilityCanBePrivate")
class RiotException(val statusMessage: String, val statusCode: Int) : Throwable("$statusCode $statusMessage")

@Serializable
data class StatusWrapper(
    val status: Status
)

@Serializable
data class Status(
    val message: String,
    @SerialName("status_code")
    val statusCode: Int
)