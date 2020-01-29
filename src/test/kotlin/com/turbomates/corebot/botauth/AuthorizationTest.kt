package com.turbomates.corebot.botauth

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AuthorizationTest {

    @Test fun `Bot authorization delayed while token is renewing`() = runBlocking {

        val authorise = DummyAuthorise()
        val botAuth = BotAuth

        launch {
            botAuth.renewToken(authorise)
        }

        assertEquals("testtoken", botAuth.authorized().value)
    }
}

class DummyAuthorise: Authorise {
    override suspend fun get(): Token {
        delay(30000)
        return Token("testtoken", 3600)
    }
}