package com.example.quote_generator.di

import com.example.quote_generator.data.repository.QuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton
import dagger.hilt.InstallIn
import com.example.quote_generator.domain.repository.QuotesRepository
import com.example.quote_generator.data.database.QuotesDatabase
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuoteRepository(quotesDatabase: QuotesDatabase): QuotesRepository {
        return QuotesRepositoryImpl(dao = quotesDatabase.quotesDao)
    }
}