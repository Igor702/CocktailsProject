package com.example.cocktailsproject.data

import android.util.Log
import com.example.cocktailsproject.data.local.LocalDataStore
import com.example.cocktailsproject.data.remote.RemoteDataStore
import com.example.cocktailsproject.models.ModelRequest
import com.example.cocktailsproject.ui.TAG

class CocktailsRepository(
    private val localData: LocalDataStore = LocalDataStore(),
    private val remoteData: RemoteDataStore = RemoteDataStore()
) {

    fun getLocalData(): String {
        return localData.getData()
    }

    suspend fun getRemoteData(): ModelRequest {
        val logInfo = remoteData.getRandomCocktail()
        Log.d(TAG, "repo, getRemoteData, ${logInfo}")

        return logInfo
    }

}