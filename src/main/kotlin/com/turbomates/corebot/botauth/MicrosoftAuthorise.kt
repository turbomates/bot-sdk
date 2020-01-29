package com.turbomates.corebot.botauth

import com.turbomates.corebot.httpclient.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.Parameters

class MicrosoftAuthorise(private val credentials: BotCredentials): Authorise {

    companion object {
        private const val URL = "https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token"
        private const val SCOPE = "https://api.botframework.com/.default"
        private const val GRANT_TYPE = "client_credentials"
    }

    override suspend fun get(): Token
    {
        val token = HttpClient.post<MicrosoftToken>(
            URL,
            FormDataContent(
                Parameters.build {
                    append("grant_type", GRANT_TYPE)
                    append("scope", SCOPE)
                    append("client_id", credentials.id)
                    append("client_secret", credentials.password)
                }
            )
        )

        return Token("${token.token_type} ${token.access_token}", token.expires_in)
    }
}

data class BotCredentials(val id: String, val password: String)
private data class MicrosoftToken(val token_type: String, val expires_in: Long, val extExpiresIn: Long, val access_token: String)
