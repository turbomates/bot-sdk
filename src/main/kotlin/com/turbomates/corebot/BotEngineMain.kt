package com.turbomates.corebot

import com.turbomates.corebot.botauth.Authorization
import com.turbomates.corebot.botauth.BotAuth
import com.turbomates.corebot.botauth.BotCredentials
import com.turbomates.corebot.botauth.MicrosoftAuthorise
import com.turbomates.corebot.botmessage.*
import com.turbomates.corebot.conversation.ConversationAdapter
import com.turbomates.corebot.middleware.Log
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

        val authorization = BotAuth
        val config = BotConfig(BotCredentials(id, pass), BotSenderData(id, name, serverUrl))
        val logger = KotlinLogging.logger {}
        val messageSender = MessageSender(config.botSenderData, authorization, listOf(Log(logger)))


        launch {
            val microsoftAuthorise = MicrosoftAuthorise(config.botCredentials)
            Authorization.keepBotAuthorized(microsoftAuthorise, authorization)
        }

        return ConversationAdapter(messageSender, this)
    }
}

private data class BotConfig(val botCredentials: BotCredentials, val botSenderData: BotSenderData)