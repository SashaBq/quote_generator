package com.example.quote_generator.di

import com.example.quote_generator.data.QuotesDataSource
import com.example.quote_generator.data.repository.QuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton
import dagger.hilt.InstallIn
import com.example.quote_generator.domain.repository.QuotesRepository
import com.example.quote_generator.data.database.QuotesDatabase
import com.example.quote_generator.data.database.QuotesDao
import com.example.quote_generator.domain.usecase.GetRandomQuoteUseCase
import com.example.quote_generator.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuoteRepository(
        quotesDatabase: QuotesDatabase
    ): QuotesRepository {
        return QuotesRepositoryImpl(
            dao = quotesDatabase.quotesDao
        )
    }

    @Provides
    @Singleton
    fun provideGetRandomQuoteUseCase(
        repository: QuotesRepository
    ): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(
        repository: QuotesRepository
    ): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }

}