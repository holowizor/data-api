package com.devbuild.api.data.infections

import com.fasterxml.jackson.databind.DeserializationFeature
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import java.text.DateFormat
import java.util.*

data class Infection(
    val infected: Int,
    val deceased: Int,
    val recovered: Int,
    val dailyInfected: Int,
    val dailyTested: Int,
    val dailyPositiveTests: Int,
    val dailyDeceased: Int,
    val dailyRecovered: Int,
    val dailyQuarantine: Int,
    val country: String,
    val lastUpdatedAtApify: Date,
    val infectedByRegion: List<InfectedRegion>
)

data class InfectedRegion(
    val region: String,
    val infectedCount: Int,
    val deceasedCount: Int,
    val recoveredCount: Int,
    val testedCount: Int,
    val quarantineCount: Int,
    val testedPositive: Int,
    val testedNegative: Int
)

object InfectionExternalApiClient {
    private val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                dateFormat = DateFormat.getDateInstance()
            }
        }
    }

    private val scheme = "https"
    private val port = 443
    private val host = "api.apify.com"
    private val path = "/v2/key-value-stores/3Po6TV7wTht4vIEid/records/LATEST"

    suspend fun getInfection(): Infection = client.get(scheme = scheme, port = port, host = host, path = path)
}
