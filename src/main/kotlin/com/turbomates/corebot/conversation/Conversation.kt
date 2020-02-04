package com.turbomates.corebot.conversation

import com.turbomates.corebot.botmessage.MessageSender
import com.turbomates.corebot.botmessage.OutcomeMessage
import kotlinx.coroutines.*

class Conversation (
    private val sender: MessageSender
) : CoroutineScope by CoroutineScope(Dispatchers.Default) {


    fun write(message: OutcomeMessage) {
        this.launch {
            sender.send(message)
        }
    }

}