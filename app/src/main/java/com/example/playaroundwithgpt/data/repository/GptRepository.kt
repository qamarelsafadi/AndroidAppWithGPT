package com.example.playaroundwithgpt.data.repository

import com.example.playaroundwithgpt.data.Resource
import com.example.playaroundwithgpt.data.model.DataResponse
import com.example.playaroundwithgpt.data.model.GPTChatRequest
import com.example.playaroundwithgpt.data.remote.service.GptService
import com.example.playaroundwithgpt.utils.fromJson
import com.example.playaroundwithgpt.utils.handleExceptions
import com.example.playaroundwithgpt.utils.handleSuccess
import com.example.playaroundwithgpt.utils.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


class GptRepository @Inject constructor(
    private val api: GptService
) {
    suspend fun sendMessages(
        gptChatRequest: GPTChatRequest
    ): Flow<Resource<String>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<String>> = try {
            val json = gptChatRequest.toJson()
            val responseBody = ("""$json""".trimIndent()).toRequestBody(
                contentType = "application/json".toMediaType()
            )
            val response = api.sendMessage(responseBody)
            if (response.isSuccessful) {
                val body = response.body()?.string()
                val chatMessage =
                    body?.split("\n")?.maxBy { it.length }?.replace("data: ", "")
                val gptChatResponse = chatMessage?.fromJson() as DataResponse
                handleSuccess(
                    gptChatResponse.message?.content?.parts?.get(0).toString(),
                    response.message()
                )
            } else {
                handleExceptions(response.body().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }
}