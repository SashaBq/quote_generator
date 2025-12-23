package com.example.quote_generator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quote_generator.data.entity.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuotesDatabase : RoomDatabase() {
    abstract val quotesDao: QuotesDao

    companion object {
        const val DATABASE_NAME = "quote_table"
    }
}