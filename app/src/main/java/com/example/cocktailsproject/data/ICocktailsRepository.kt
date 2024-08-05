package com.example.cocktailsproject.data

import androidx.lifecycle.LiveData
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.CocktailRequest
import kotlinx.coroutines.flow.Flow

interface ICocktailsRepository {
    suspend fun getRemoteData(): CocktailRequest
     fun getRecentCocktails(): Flow<List<Cocktail>?>

     suspend fun getLocalCocktailById(id:Int): Cocktail
    suspend fun getRemoteCocktailById(id: Int):CocktailRequest
   suspend fun addToRecentCocktails(cocktail: Cocktail)
   suspend fun searchCocktailByName(query: String): CocktailRequest
}