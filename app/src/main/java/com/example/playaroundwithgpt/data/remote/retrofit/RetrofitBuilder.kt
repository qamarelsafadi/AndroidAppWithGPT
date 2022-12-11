package com.example.playaroundwithgpt.data.remote.retrofit

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBuilder @Inject constructor(@ApplicationContext private val context: Context) {
    private var okHttpClientBuilder: OkHttpClient.Builder? = null
    private var interceptors = mutableListOf<Interceptor>()
    private var authenticator: Authenticator? = null
    private var baseUrl: String = "https://chat.openai.com/backend-api/"
    private val TIME_OUT = 80 * 1000L

    fun addInterceptors(vararg interceptor: Interceptor): RetrofitBuilder {
        interceptors.addAll(interceptor)
        return this
    }

    fun build(): Retrofit {
        val clientBuilder = okHttpClientBuilder ?: OkHttpClient.Builder().apply {
            connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            val auth: Authenticator? = when {
                authenticator != null -> authenticator
                else -> null
            }
            auth?.let { authenticator(it) }
            interceptors.forEach { addInterceptor(it) }
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}