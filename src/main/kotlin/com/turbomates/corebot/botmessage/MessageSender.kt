package com.turbomates.corebot.botmessage

import com.turbomates.corebot.botauth.Authorise
import com.turbomates.corebot.incomeactivity.Member
import com.turbomates.corebot.httpclient.Header
import com.turbomates.corebot.httpclient.HttpClient
import com.turbomates.corebot.middleware.AfterSend
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.Serializable

class MessageSender(
    private val senderData: BotSenderData,
    private val auth: Authorise,
    private val middleware: List<AfterSend>
){

    suspend fun send(message: OutcomeMessage) = coroutineScope {

        HttpClient.post<ExternalId>(
            "${senderData.serverUrl}/v3/conversations/${message.conversationId.id}/activities",
            Body("message", message.message, Member(senderData.id, senderData.name)),
            listOf(
                Header(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                Header(HttpHeaders.Authorization, auth.get().value)
            )
        )

        middleware.map { it(message) }
    }
}

data class BotSenderData(val id: String, val name: String, val serverUrl: String)
@Serializable
private data class Body(val type: String, val text: String, val from: Member)
@Serializable
private data class ExternalId(val id: String)

