package com.turbomates.corebot.incomeactivity

import kotlinx.serialization.Serializable

/**
 * https://github.com/microsoft/botframework-sdk/tree/master/specs/botframework-activity
 */
@Serializable
data class IncomeActivity (
    val conversation: ConversationId,
    val type: String,
    val from: Member,
    val recipient: Member,
    val text: String?,
    val membersAdded: List<Member>?
)
@Serializable
data class Member (val id: String, val name: String)
@Serializable
data class ConversationId (val id: String)