package io.least.data

import android.util.Log

class RateExperienceConfigRepo(private val appId: String, private val httpClient: HttpClient) {

    suspend fun fetchRateExperienceConfig(): RateExperienceConfig {
        val response = httpClient.fetchMyCases(appId).body()
        if (response == null) {
            Log.e(this.javaClass.simpleName, "Server returned an empty response")
            // TODO how can we fail safe if the server's response is an empty?
            throw NullPointerException()
        }
        return RateExperienceConfig(
            tags = response.tags,
            appId = appId,
            numberOfStars = response.numberOfStars,
            valueReaction = response.valueReaction,
            title = response.title,
            postSubmitTitle = response.postSubmitTitle,
            postSubmitText = response.postSubmitText,
            fetchConfigFromServer = true,
        )
    }
}