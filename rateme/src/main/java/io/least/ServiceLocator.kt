package io.least

import io.least.core.ServiceLocator
import io.least.data.HttpClient

object ServiceLocator {

    fun getHttpClient(hostUrl: String): HttpClient {
          return ServiceLocator.retrofitInstance(hostUrl).create(HttpClient::class.java)
    }
}