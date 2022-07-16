package io.least.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HttpClient {

    @GET("/v1/rating/sdk/config")
    suspend fun fetchRateExperienceConfig(): Response<RateExperienceConfig>

    @POST("/v1/rating/sdk/user_rating")
    suspend fun publishResult(@Body result: RateExperienceResult): Response<Unit>
}
