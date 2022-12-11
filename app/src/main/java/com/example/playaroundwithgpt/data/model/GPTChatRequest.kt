package com.example.playaroundwithgpt.data.model

import com.example.playaroundwithgpt.data.model.GPTMessage


data class GPTChatRequest(
    val action: String = "next",
    val messages: List<GPTMessage>,
    val conversation_id: String,
    val parent_message_id: String,
    val model: String = "text-davinci-002-render"
)
