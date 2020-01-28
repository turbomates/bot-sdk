package com.turbomates.corebot.conversation

import com.turbomates.corebot.botmessage.MessageSender
import com.turbomates.corebot.botmessage.OutcomeMessage
import kotlinx.coroutines.*

class ConversationAdapter (
    private val sender: MessageSender,
    private val botScope: CoroutineScope
) {

    fun write(message: OutcomeMessage) {
        botScope.launch {
            sender.send(message)
        }
    }

}