package com.example.quote_generator.di

import android.content.Context
import androidx.room.Room
import com.example.quote_generator.data.database.QuotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(
        @ApplicationContext context: Context
    ): QuotesDatabase {
        return Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            QuotesDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(false).build()
    }
}