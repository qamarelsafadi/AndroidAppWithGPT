package com.example.playaroundwithgpt.di

import com.example.playaroundwithgpt.data.remote.retrofit.HeaderInterceptor
import com.example.playaroundwithgpt.data.remote.retrofit.RetrofitBuilder
import com.example.playaroundwithgpt.data.remote.service.GptService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: RetrofitBuilder,
        headerInterceptor: HeaderInterceptor
    ): Retrofit =
        retrofitBuilder
            .addInterceptors(headerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideGPTApi(retrofit: Retrofit): GptService = retrofit.create(GptService::class.java)
}
