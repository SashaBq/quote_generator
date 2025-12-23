package com.example.quote_generator.data.mapper

import com.example.quote_generator.data.entity.QuoteEntity
import com.example.quote_generator.domain.model.Quote

object QuoteMapper {
    fun mapToDomain(
        entity: QuoteEntity
    ): Quote =
        Quote(
            entity.id,
            entity.text,
            entity.author,
            entity.isFavorite
        )

    fun mapToEntity(
        domain: Quote
    ): QuoteEntity =
        QuoteEntity(
            domain.id,
            domain.text,
            domain.author,
            domain.isFavorite
        )

    fun mapToDomainList(
        entities: List<QuoteEntity>
    ): List<Quote> =
        entities.map {
            mapToDomain(it)
        }
}