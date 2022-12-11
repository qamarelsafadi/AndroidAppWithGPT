package com.example.playaroundwithgpt.data.model

data class GPTMessage(
  val id: String,
  val role: String = "user",
  val content: GPTContent = GPTContent()
)
