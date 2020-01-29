package com.turbomates.corebot.middleware

import com.turbomates.corebot.botmessage.OutcomeMessage
import mu.KLogger

class Log(private val logger: KLogger): AfterSend {
    override fun invoke(message: OutcomeMessage) {
        logger.info("${message.id} was sent to conversation ${message.conversationId}. Text: ${message.message}")
    }
}