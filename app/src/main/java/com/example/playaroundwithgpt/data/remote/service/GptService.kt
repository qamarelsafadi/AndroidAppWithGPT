package com.example.playaroundwithgpt.data.remote.service

import com.example.playaroundwithgpt.data.model.DataResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptService {
    @Headers( "Content-Type: application/json" )
    @POST("conversation")
    suspend fun sendMessage(
        @Body body: RequestBody
    ): Response<ResponseBody>

}