package com.example.cocktailsproject.data

import com.example.cocktailsproject.data.local.LocalDataStore

class CocktailsRepository(private val dataStore: LocalDataStore = LocalDataStore()) {

    fun getLocalData():String{
        return dataStore.getData()
    }

}