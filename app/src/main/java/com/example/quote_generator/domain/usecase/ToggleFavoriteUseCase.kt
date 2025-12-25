package com.example.quote_generator.domain.usecase

import com.example.quote_generator.domain.repository.QuotesRepository

class ToggleFavoriteUseCase(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(quoteId: Int) {
        repository.toggleFavorite(quoteId)
    }
}