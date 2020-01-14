package com.turbomates.corebot.dialogue

import com.turbomates.corebot.dialogue.storage.Storage
import com.turbomates.corebot.incomeactivity.ConversationId

class DialogueAdapter (private val storage: Storage){

    fun start(conversationId: ConversationId, message: String)
    {
        val dialogue = Dialogue(conversationId, message)
        storage.save(dialogue)
    }
}