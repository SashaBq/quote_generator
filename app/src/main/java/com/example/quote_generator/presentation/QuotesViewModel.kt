package com.example.quote_generator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote_generator.domain.model.Quote
import com.example.quote_generator.domain.repository.QuotesRepository
import com.example.quote_generator.domain.usecase.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.quote_generator.domain.usecase.ToggleFavoriteUseCase

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val repository: QuotesRepository
) : ViewModel() {

    private val _currentQuote = MutableStateFlow(Quote(0, "", ""))
    val currentQuote: StateFlow<Quote> = _currentQuote.asStateFlow()

    val allQuotes: StateFlow<List<Quote>> = repository
        .getAllQuotesFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadRandomQuote()
    }

    fun onNextQuoteClick() {
        loadRandomQuote()
    }

    fun onToggleFavorite(quoteId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(quoteId)
        }
    }

    private fun loadRandomQuote() {
        viewModelScope.launch {
            _currentQuote.value = getRandomQuoteUseCase()
        }
    }
}