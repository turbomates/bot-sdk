package com.turbomates.corebot

import com.turbomates.corebot.botauth.BotCredentials
import com.turbomates.corebot.botauth.MicrosoftAuthorise
import com.turbomates.corebot.botmessage.*
import com.turbomates.corebot.conversation.Conversation
import com.turbomates.corebot.middleware.Log
import mu.KotlinLogging

object ConversationFactory {

    fun setup(
        id: String,
        pass: String,
        name: String,
        serverUrl: String
    ) : Conversation {


        val config = BotConfig(BotCredentials(id, pass), BotSenderData(id, name, serverUrl))
        val logger = KotlinLogging.logger {}
        val microsoftAuthorise = MicrosoftAuthorise(config.botCredentials)
        val messageSender = MessageSender(config.botSenderData, microsoftAuthorise, listOf(Log(logger)))

        return Conversation(messageSender)
    }
}

private data class BotConfig(val botCredentials: BotCredentials, val botSenderData: BotSenderData)