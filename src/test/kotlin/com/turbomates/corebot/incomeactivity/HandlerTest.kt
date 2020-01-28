package com.turbomates.corebot.incomeactivity

import com.nhaarman.mockitokotlin2.*
import com.turbomates.corebot.Bot
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class HandlerTest {
    private val handler = Handler
    private val person = Member("person", "person")
    private val mBot = Member("bot", "bot")
    private val conversationId = ConversationId("test")
    private val bot: Bot = mock()

    @Test fun `On income message the bot method "onMessage" should be called`() {
        val message = IncomeActivity(conversationId, "message", person, mBot, "Hello", null)

        runBlocking {
            handler.handle(message, bot)
            verify (bot, times(1)).onMessage("Hello", conversationId)
            verifyNoMoreInteractions(bot)
        }
    }

    @Test fun `On income message with null text do nothing`() {
        val message = IncomeActivity(conversationId, "message", person, mBot, null, null)

        runBlocking {
            handler.handle(message, bot)
            verifyZeroInteractions(bot)
        }
    }

    @Test fun `When only person joined the chat the bot method "onPersonsAdded" should be called`() {
        val activity = IncomeActivity(conversationId, "conversationUpdate", person, mBot,null, listOf(person))

        runBlocking {
            handler.handle(activity, bot)
            verify (bot, times(1)).onPersonsAdded(listOf(person), conversationId)
            verifyNoMoreInteractions(bot)
        }
    }

    @Test fun `When only bot joined the chat the bot method "onBotAdded" should be called`() {
        val activity = IncomeActivity(conversationId, "conversationUpdate", person, mBot, null, listOf(mBot))

        runBlocking {
            handler.handle(activity, bot)
            verify (bot, times(1)).onBotAdded(conversationId)
            verifyNoMoreInteractions(bot)
        }
    }

    @Test fun `When bot and person joined the chat the bot method "onBotAdded" and "onPersonsAdded" should be called`() {
        val activity = IncomeActivity(conversationId,"conversationUpdate", person, mBot,null, listOf(mBot, person))

        runBlocking {
            handler.handle(activity, bot)
            verify (bot, times(1)).onBotAdded(conversationId)
            verify (bot, times(1)).onPersonsAdded(listOf(person), conversationId)
            verifyNoMoreInteractions(bot)
        }
    }
}
