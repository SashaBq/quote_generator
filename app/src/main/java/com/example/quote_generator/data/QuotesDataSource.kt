package com.example.quote_generator.data

import com.example.quote_generator.data.database.QuotesDao
import com.example.quote_generator.data.mapper.QuoteMapper
import com.example.quote_generator.domain.model.Quote
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class QuotesDataSource @Inject constructor(
    private val dao: QuotesDao
) {
    suspend fun initQuotesIfEmpty() {
        val count = dao.getAllQuotes().size
        if (count == 0) {
            val entities = Quote.defaultQuotes()
                .map { QuoteMapper.mapToEntity(it) }
            dao.insertQuotes(entities)
        }
    }
}