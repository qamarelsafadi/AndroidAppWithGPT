package com.example.playaroundwithgpt.data.model

data class GPTContent(
    val content_type: String = "text",
    val parts: List<String> = listOf()
)
