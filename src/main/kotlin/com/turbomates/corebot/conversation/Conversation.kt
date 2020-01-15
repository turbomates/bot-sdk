package com.turbomates.corebot.conversation

import com.turbomates.corebot.botmessage.OutcomeMessage
import com.turbomates.corebot.incomeactivity.ConversationId

class Conversation (val id: ConversationId){

    private val outcomeMessages = mutableListOf<OutcomeMessage>()

    fun push(message: OutcomeMessage) {
        outcomeMessages.add(message)
    }
}