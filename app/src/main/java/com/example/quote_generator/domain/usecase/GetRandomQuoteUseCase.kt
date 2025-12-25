package com.example.quote_generator.domain.usecase

import com.example.quote_generator.domain.model.Quote
import com.example.quote_generator.domain.repository.QuotesRepository

class GetRandomQuoteUseCase(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(): Quote = repository.getRandomQuote()
}