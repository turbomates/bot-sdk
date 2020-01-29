package com.turbomates.corebot.botauth

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

object Authorization
{
    suspend fun keepBotAuthorized(authorise: Authorise, auth: BotAuth)
    {
        while (true) {
            auth.renewToken(authorise)
            delay(auth.authorized().expiredInSec * 1000 - 100)
        }
    }
}

interface Authorise { suspend fun get(): Token }
data class Token(val value: String, val expiredInSec: Long)

object BotAuth {

    private var current: Token? = null
    private val blocked = Channel<Token>()

    suspend fun renewToken(authorise: Authorise){
        current = null
        blocked.send(authorise.get())
    }

    suspend fun authorized(): Token {
        if (current == null) {
            current = blocked.receive()
        }
        return current as Token
    }
}