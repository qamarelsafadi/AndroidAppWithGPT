package com.example.playaroundwithgpt.data.model


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("content_type")
    val contentType: String? = "",
    @SerializedName("parts")
    val parts: List<Any?>? = listOf()
)