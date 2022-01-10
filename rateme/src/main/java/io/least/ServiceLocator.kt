package io.least

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.least.data.HttpClient
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


object ServiceLocator {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.least.com/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    fun getHttpClient(): HttpClient {
          return retrofit.create(HttpClient::class.java)
    }
}