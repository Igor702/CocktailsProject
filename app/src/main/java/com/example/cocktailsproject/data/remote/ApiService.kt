package com.example.cocktailsproject.data.remote

import com.example.cocktailsproject.models.ModelRequest
import retrofit2.http.GET

interface ApiService {
    @GET("random.php")
    suspend fun getRandomCocktail(): ModelRequest
}

