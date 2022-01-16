package io.least

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.least.data.HttpClient
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ServiceLocator {

    private fun retrofitInstance(hostUrl: String = "https://api.least.com/"): Retrofit {
          return Retrofit.Builder()
              .baseUrl(hostUrl)
              .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
              .build()
    }

    fun getHttpClient(hostUrl: String): HttpClient {
          return retrofitInstance(hostUrl).create(HttpClient::class.java)
    }
}