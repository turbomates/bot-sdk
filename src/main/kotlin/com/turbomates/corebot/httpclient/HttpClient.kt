package com.turbomates.corebot.httpclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.post

object HttpClient {
    val client: HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.BODY
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend inline fun <reified T> post(url: String, content: Any, postHeaders: List<Header>? = null): T {

        return client.post<T>(url) {
            postHeaders?.forEach {
                headers.append(it.name, it.value)
            }
            body = content
        }
    }
}

data class Header(val name: String, val value: String)