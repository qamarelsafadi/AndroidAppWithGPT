package com.example.playaroundwithgpt.data.model


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("content")
    val content: Content? = Content(),
    @SerializedName("create_time")
    val createTime: Any? = Any(),
    @SerializedName("end_turn")
    val endTurn: Any? = Any(),
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("metadata")
    val metadata: Metadata? = Metadata(),
    @SerializedName("recipient")
    val recipient: String? = "",
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("update_time")
    val updateTime: Any? = Any(),
    @SerializedName("user")
    val user: Any? = Any(),
    @SerializedName("weight")
    val weight: Double? = 0.0
)