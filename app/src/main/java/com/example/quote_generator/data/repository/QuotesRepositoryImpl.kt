package com.example.quote_generator.data.repository

import com.example.quote_generator.data.QuotesDataSource
import com.example.quote_generator.data.database.QuotesDao
import com.example.quote_generator.data.mapper.QuoteMapper
import jakarta.inject.Singleton
import jakarta.inject.Inject
import com.example.quote_generator.domain.repository.QuotesRepository
import com.example.quote_generator.domain.model.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Singleton
class QuotesRepositoryImpl @Inject constructor(
    private val dao: QuotesDao
) : QuotesRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            QuotesDataSource(dao).initQuotesIfEmpty()
        }
    }

    override suspend fun getRandomQuote(): Quote {
        val entity = dao.getRandomQuote()
        return entity?.let { QuoteMapper.mapToDomain(it) }
            ?: Quote(0, "Цитаты загружаются...", "Система", false)
    }

    // ✅ suspend List (для UseCase)
    override suspend fun getAllQuotes(): List<Quote> {
        val entities = dao.getAllQuotes()
        return QuoteMapper.mapToDomainList(entities)
    }

    // ✅ Flow (для UI)
    override fun getAllQuotesFlow(): Flow<List<Quote>> {
        return dao.getAllQuotesFlow()
            .map { QuoteMapper.mapToDomainList(it) }
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
