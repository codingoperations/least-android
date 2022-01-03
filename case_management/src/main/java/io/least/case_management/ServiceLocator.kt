package io.least.case_management

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.least.case_management.data.CaseListRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import retrofit2.Retrofit


object ServiceLocator {
    private lateinit var xMPPTCPConnection: XMPPTCPConnection

    fun getXMPPTCPConnection(): XMPPTCPConnection {
        if (!this::xMPPTCPConnection.isInitialized) {
            xMPPTCPConnection = XMPPTCPConnection("coder_off@jabber.uk", "12345678")
        }
        return xMPPTCPConnection
    }


    @OptIn(ExperimentalSerializationApi::class)
    fun getCaseListRepository(): CaseListRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.least.com/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

        return retrofit.create(CaseListRepository::class.java)
    }
}