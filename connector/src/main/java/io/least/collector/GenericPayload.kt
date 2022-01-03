package io.least.collector

import kotlinx.serialization.Serializable

@Serializable
data class GenericPayload<T>(
    val osName: String,
    val osVersion: String,
    val data: T
)