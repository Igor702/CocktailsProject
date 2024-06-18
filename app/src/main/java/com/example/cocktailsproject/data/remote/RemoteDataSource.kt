package com.example.cocktailsproject.data.remote

import android.util.Log
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.ui.TAG
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource {

    override suspend fun getRandomCocktail(): CocktailRequest {

        val result = apiService.getRandomCocktail()

        Log.d(TAG, "remoteDS, result: $result")

        return result
    }
}