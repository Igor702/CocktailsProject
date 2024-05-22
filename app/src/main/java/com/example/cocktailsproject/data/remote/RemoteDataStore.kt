package com.example.cocktailsproject.data.remote

import android.util.Log
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.ui.TAG


class RemoteDataStore {

    suspend fun getRandomCocktail(): CocktailRequest {

        val result = ApiClient.apiService.getRandomCocktail()

        Log.d(TAG, "remoteDS, result: $result")

        return result
    }
}