package com.example.quote_generator.domain.repository

import com.example.quote_generator.domain.model.Quote

interface QuotesRepository {
    suspend fun getRandomQuote(): Quote
    suspend fun getAllQuotes(): List<Quote>
    suspend fun toggleFavorite(quoteId: Int)
    suspend fun getFavoriteQuotes(): List<Quote>
}