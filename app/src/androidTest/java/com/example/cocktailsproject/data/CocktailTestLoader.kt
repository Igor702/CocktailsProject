package com.example.cocktailsproject.data

import com.example.cocktailsproject.models.CocktailRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CocktailTestLoader {

    private var cocktailServiceRequest = CocktailRequest(mutableListOf())
    private var uiStateLoading = false


    fun setCocktail(cocktailRequest: CocktailRequest) {
        cocktailServiceRequest = cocktailRequest
    }

    fun setDelayForLoadingTesting(delay: Boolean) {
        uiStateLoading = delay
    }

    suspend fun getRemoteData(): CocktailRequest {

        if (uiStateLoading) {
            withContext(Dispatchers.IO) {
                Thread.sleep(5000)
            }
        }
        return cocktailServiceRequest
    }

    fun resetData() {
        uiStateLoading = false
        cocktailServiceRequest = CocktailRequest(mutableListOf())
    }

}