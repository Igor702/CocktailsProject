package com.example.cocktailsproject.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.ui.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: RecentCocktailsDAO): ILocalDataSource {

    fun getData(): String {
        return "String from dataStore"
    }

    override suspend fun addToRecentCocktails(cocktail: Cocktail) {

        dao.addToRecentCocktails(cocktail)

        Log.d(TAG, "addToRecentCocktails(cocktail: $cocktail)")
    }

    override fun getRecentCocktails(): Flow<List<Cocktail>?> {
        val temp = dao.getRecentCocktails()
        Log.d(TAG, "getRecentCocktails() temp ${temp.asLiveData().value}")
        return temp
    }

    override suspend fun getCocktailById(id: Int): Cocktail {
//
//        Log.d(TAG, "getCocktailById(id: $id) temp ${temp.asLiveData().value}")
        return  dao.getCocktailById(id) }
}