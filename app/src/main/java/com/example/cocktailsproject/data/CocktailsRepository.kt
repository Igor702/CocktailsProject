package com.example.cocktailsproject.data

import android.util.Log
import com.example.cocktailsproject.data.remote.RemoteDataStore
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.ui.TAG

class CocktailsRepository(
    private val remoteData: RemoteDataStore = RemoteDataStore()
) {



    suspend fun getRemoteData(): CocktailRequest {
        val logInfo = remoteData.getRandomCocktail()
        Log.d(TAG, "repo, getRemoteData, ${logInfo}")

        return logInfo
    }

}