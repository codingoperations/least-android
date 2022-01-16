package io.least.data

import io.least.collector.DeviceDataCollector
import io.least.collector.GenericPayload
import retrofit2.Response
import retrofit2.http.*

interface HttpClient {

    @GET("rateme/experience/config/{app_id}")
    suspend fun fetchMyCases(@Path("user") appId: String): Response<RateExperienceDto>

    @POST("rateme/experience/{app_id}")
    suspend fun publishResult(@Body result: GenericPayload<RateExperienceResult>): Response<Unit>
}
