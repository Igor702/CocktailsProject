package com.example.cocktailsproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailsproject.models.Cocktail

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class RecentCocktailsDatabase:RoomDatabase() {

    abstract fun recentCocktailsDao():RecentCocktailsDAO
}