package com.example.quote_generator.data.repository

import com.example.quote_generator.data.database.QuotesDao
import com.example.quote_generator.data.mapper.QuoteMapper
import jakarta.inject.Singleton
import jakarta.inject.Inject
import com.example.quote_generator.domain.repository.QuotesRepository
import com.example.quote_generator.domain.model.Quote

@Singleton
class QuotesRepositoryImpl @Inject constructor(
    private val dao: QuotesDao
) : QuotesRepository {

    override suspend fun getRandomQuote(): Quote {
        val entity = dao.getRandomQuote()
        return QuoteMapper.mapToDomain(entity)
    }

    override suspend fun getAllQuotes(): List<Quote> {
        val entities = dao.getAllQuotes()
        return QuoteMapper.mapToDomainList(entities)
    }

    override suspend fun toggleFavorite(quoteId: Int) {
        val currentFavorite = dao.getQuoteById(quoteId)?.isFavorite ?: false
        dao.toggleFavorite(quoteId, !currentFavorite)
    }

    override suspend fun getFavoriteQuotes(): List<Quote> {
        val entities = dao.getFavoriteQuotes()
        return QuoteMapper.mapToDomainList(entities)
    }
}
