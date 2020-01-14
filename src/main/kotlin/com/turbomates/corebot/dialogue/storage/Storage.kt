package com.turbomates.corebot.dialogue.storage

import com.turbomates.corebot.dialogue.Dialogue
import com.turbomates.corebot.incomeactivity.ConversationId

interface Storage {
    fun get(id: ConversationId): List<Dialogue>
    fun save(dialogue: Dialogue)
}