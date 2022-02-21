package io.least.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


object ServiceLocator {
    private val logging = HttpLoggingInterceptor().apply{ this.setLevel(HttpLoggingInterceptor.Level.BODY) }
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()


    @OptIn(ExperimentalSerializationApi::class)
    fun retrofitInstance(hostUrl: String = "http://codingops-publisher.herokuapp.com"): Retrofit {
        return Retrofit.Builder()
            .baseUrl(hostUrl)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }
}