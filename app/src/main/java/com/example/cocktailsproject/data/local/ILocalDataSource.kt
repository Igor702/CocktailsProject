package com.example.cocktailsproject.data.local

import androidx.lifecycle.LiveData
import com.example.cocktailsproject.models.Cocktail
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {

   suspend fun addToRecentCocktails(cocktail: Cocktail)
   fun getRecentCocktails(): Flow<List<Cocktail>?>
   suspend fun getCocktailById(id: Int): Cocktail
}
