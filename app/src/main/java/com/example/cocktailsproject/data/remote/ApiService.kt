package com.example.cocktailsproject.data.remote

import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.CocktailRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    suspend fun getRandomCocktail(): CocktailRequest

    @GET("search.php?")
   suspend fun searchCocktailByName(@Query("s") query: String): CocktailRequest

   @GET("lookup.php?")
   suspend fun getCocktailById(@Query("i") id: Int): CocktailRequest


}

