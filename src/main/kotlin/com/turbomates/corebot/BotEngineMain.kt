package com.turbomates.corebot

import com.turbomates.corebot.botauth.Authorization
import com.turbomates.corebot.botauth.BotAuth
import com.turbomates.corebot.botauth.MicrosoftAuthorise
import com.turbomates.corebot.botmessage.*
import com.turbomates.corebot.conversation.ConversationAdapter
import com.turbomates.corebot.conversation.storage.InMemory
import com.turbomates.corebot.middleware.ExternalIdLink
import com.turbomates.corebot.middleware.Log
import com.turbomates.corebot.middleware.processAfterSend
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import mu.KotlinLogging

object BotEngineMain: CoroutineScope by CoroutineScope(Dispatchers.Default) {

    fun setup(
        id: String,
        pass: String,
        name: String,
        serverUrl: String
    ) : ConversationAdapter {

        val authorization = Channel<String>()
        val bindings = Channel<ExternalIdLink>()
        val config = BotConfig(BotAuth(id, pass), BotSenderData(id, name, serverUrl))
        val logger = KotlinLogging.logger {}
        val storage = InMemory()
        val messageSender = MessageSender(config.botSenderData, authorization, bindings)


        launch {
            val microsoftAuthorise = MicrosoftAuthorise(config.botAuth)
            Authorization.keepBotAuthorized(microsoftAuthorise, authorization)
        }

        //@todo is it really need?
        launch {
            processAfterSend(bindings, listOf(Log(logger)))
        }

        return ConversationAdapter(storage, messageSender, this)
    }
}

private data class BotConfig(val botAuth: BotAuth, val botSenderData: BotSenderData)