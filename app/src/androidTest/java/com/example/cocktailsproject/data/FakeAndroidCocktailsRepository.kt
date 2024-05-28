package com.example.cocktailsproject.data

import android.util.Log
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.ui.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FakeAndroidCocktailsRepository:ICocktailsRepository {

    private var cocktailServiceRequest = CocktailRequest(mutableListOf())
    private var uiStateLoading = false


    fun setCocktail(cocktailRequest: CocktailRequest){
        cocktailServiceRequest = cocktailRequest
    }

    fun setDelayForLoadingTesting(delay: Boolean){
        uiStateLoading = delay
    }
    override suspend fun getRemoteData(): CocktailRequest {

        if (uiStateLoading){
            withContext(Dispatchers.IO) {
                Thread.sleep(5000)
            }
        }
        return cocktailServiceRequest
    }
}