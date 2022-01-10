package io.least.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HttpClient {

    @GET("rateme/experience/config/{app_id}")
    suspend fun fetchMyCases(@Path("user") appId: String): Response<RateExperienceDto>
}
