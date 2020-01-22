package com.turbomates.corebot.conversation

import com.turbomates.corebot.botmessage.MessageSender
import com.turbomates.corebot.botmessage.OutcomeMessage
import com.turbomates.corebot.conversation.storage.Storage
import com.turbomates.corebot.incomeactivity.ConversationId
import kotlinx.coroutines.*

class ConversationAdapter (
    private val storage: Storage,
    private val sender: MessageSender,
    private val botScope: CoroutineScope
) {

    fun write(message: OutcomeMessage) {
        val conversation = gatherConversation(message.conversationId)
        conversation.push(message)

        botScope.launch {
            sender.send(message)
        }

        storage.save(conversation)
    }

    private fun gatherConversation(conversationId: ConversationId): Conversation
    {
        return storage.get(conversationId)
    }
}