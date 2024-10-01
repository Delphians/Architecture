package com.che.architecture.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val date: String,
    val close: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val volume: Long,
    val adjClose: Double,
    val adjHigh: Double,
    val adjLow: Double,
    val adjOpen: Double,
    val adjVolume: Long,
    val divCash: Double,
    val splitFactor: Double
)
