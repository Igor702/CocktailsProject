package com.example.cocktailsproject.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cocktailsproject.data.local.RecentCocktailsDAO
import com.example.cocktailsproject.data.local.RecentCocktailsDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideRecentCocktailsDatabase(context: Context):RecentCocktailsDatabase{
        val database: RecentCocktailsDatabase = Room
            .databaseBuilder(
                context,
                RecentCocktailsDatabase::class.java,
                "recent_cocktails")
            .build()

        return database
    }

    @Provides
    fun provideRecentCocktailsDao(database: RecentCocktailsDatabase):RecentCocktailsDAO{
        return database.recentCocktailsDao()
    }
}