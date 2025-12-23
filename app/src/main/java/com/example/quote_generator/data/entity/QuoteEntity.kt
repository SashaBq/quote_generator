package com.example.quote_generator.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String,
    val isFavorite: Boolean = false
)
