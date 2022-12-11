package com.example.playaroundwithgpt.di

import com.example.playaroundwithgpt.data.remote.service.GptService
import com.example.playaroundwithgpt.data.repository.GptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providerGptRepository(
        apiService: GptService
    ): GptRepository {
        return GptRepository(apiService)
    }
}
