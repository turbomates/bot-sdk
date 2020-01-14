package com.turbomates.corebot.dialogue

import com.turbomates.corebot.incomeactivity.ConversationId
import java.util.*

class Dialogue (val conversationId: ConversationId) {
    private val messages: MutableList<DialogueMessage> = mutableListOf()

    constructor(conversationId: ConversationId, message: String) : this(conversationId) {
        messages.add(DialogueMessage(message))
    }

    fun reply(message: String) {
        messages.add(DialogueMessage(message))
    }

    fun continueWith(message: String) {
        messages.add(DialogueMessage(message))
    }
}

data class DialogueMessage(val message: String) {
    val id = DialogueMessageId()
}

class DialogueMessageId {
    val id:UUID = UUID.randomUUID()
}