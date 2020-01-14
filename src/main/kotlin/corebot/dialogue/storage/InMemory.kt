package com.turbomates.corebot.dialogue.storage

import com.turbomates.corebot.dialogue.Dialogue
import com.turbomates.corebot.incomeactivity.ConversationId

class InMemory: Storage {
    private val dialogues = mutableMapOf<ConversationId, MutableList<Dialogue>>()

    override fun get(id: ConversationId): List<Dialogue> {
        return dialogues.getOrDefault(id, mutableListOf<Dialogue>())
    }

    override fun save(dialogue: Dialogue) {
        dialogues[dialogue.conversationId]?.add(dialogue)
    }
}