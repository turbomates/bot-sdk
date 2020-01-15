package com.turbomates.corebot.botmessage

import com.turbomates.corebot.incomeactivity.ConversationId
import java.util.*

class OutcomeMessage(val message: String, val conversationId: ConversationId) {
    val id = MessageId()
}

class MessageId {
    val id = UUID.randomUUID()
}