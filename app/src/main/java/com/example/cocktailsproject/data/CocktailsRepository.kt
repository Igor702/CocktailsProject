package com.example.cocktailsproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cocktailsproject.data.local.ILocalDataSource
import com.example.cocktailsproject.data.remote.IRemoteDataSource
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.ui.TAG
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailsRepository @Inject constructor(
    private val remoteData: IRemoteDataSource,
    private val localData: ILocalDataSource
) : ICocktailsRepository {

    override suspend fun getRemoteData(): CocktailRequest {

        return remoteData.getRandomCocktail()
    }

    override fun getRecentCocktails(): Flow<List<Cocktail>?> {
       return localData.getRecentCocktails()
    }

    override suspend fun getLocalCocktailById(id: Int): Cocktail {
        Log.d(TAG, "CocktailsRepository, getCocktailById(id: Int)")

        return localData.getCocktailById(id)
    }

    override suspend fun getRemoteCocktailById(id: Int):CocktailRequest{
        return remoteData.getCocktailById(id)
    }



    override suspend fun addToRecentCocktails(cocktail: Cocktail) {
        Log.d(TAG, "CocktailsRepository, addToRecentCocktails(cocktail: $cocktail)")
        localData.addToRecentCocktails(cocktail)
    }

    override suspend fun searchCocktailByName(query: String): CocktailRequest {
       return remoteData.searchCocktailByName(query)
    }

}