package io.least.data

import io.least.collector.GenericPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HttpClient {

    @GET("/rating/v1/config")
    suspend fun fetchRateExperienceConfig(): Response<RateExperienceConfig>

    @POST("/rating/v1/user_rating")
    suspend fun publishResult(@Body result: GenericPayload<RateExperienceResult>): Response<Unit>
}
