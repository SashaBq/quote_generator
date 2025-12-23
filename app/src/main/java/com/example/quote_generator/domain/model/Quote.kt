package com.example.quote_generator.domain.model

data class Quote(
    val id: Int,
    val text: String,
    val author: String,
    val isFavorite: Boolean = false
) {
    companion object {
        fun defaultQuotes() = listOf(
            Quote(1, "Начни делать то, что важно тебе. Жизнь слишком коротка.", "Далай Лама"),
            Quote(2, "То, что мы думаем, мы становимся.", "Будда"),
            Quote(3, "Путь рождает совершенство.", "Лао-цзы"),
        )
    }
}
