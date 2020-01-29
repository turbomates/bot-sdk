package com.turbomates.corebot.middleware

import com.turbomates.corebot.botmessage.OutcomeMessage

interface AfterSend {
    operator fun invoke(message: OutcomeMessage)
}