package com.example.quote_generator.domain.repository

import com.example.quote_generator.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    suspend fun getRandomQuote(): Quote
    suspend fun getAllQuotes(): List<Quote>           // suspend List!
    fun getAllQuotesFlow(): Flow<List<Quote>>         // Flow!
    suspend fun toggleFavorite(quoteId: Int)
    suspend fun getFavoriteQuotes(): List<Quote>
}