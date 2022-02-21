package io.least.data

import android.util.Log
import io.least.collector.DeviceDataCollector

class RateExperienceConfigRepo(
    private val appId: String,
    private val httpClient: HttpClient,
    private val dataCollector: DeviceDataCollector<RateExperienceResult>
) {

    suspend fun fetchRateExperienceConfig(): RateExperienceConfig {
        // TODO Handle Http call errors
        val response = httpClient.fetchRateExperienceConfig().body()
        if (response == null) {
            Log.e(this.javaClass.simpleName, "Server returned an empty response")
            // TODO how can we fail safe if the server's response is an empty?
            throw NullPointerException()
        }
        return response
    }

    suspend fun publishRateResults(result: RateExperienceResult) {
        val finalPayload = dataCollector.collect(result)
        httpClient.publishResult(finalPayload)
    }
}