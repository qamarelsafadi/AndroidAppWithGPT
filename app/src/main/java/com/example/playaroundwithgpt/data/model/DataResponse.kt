package com.example.playaroundwithgpt.data.model


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("conversation_id")
    val conversationId: String? = "",
    @SerializedName("error")
    val error: Any? = Any(),
    @SerializedName("message")
    val message: Message? = Message()
)