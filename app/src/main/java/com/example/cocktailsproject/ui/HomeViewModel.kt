package com.example.cocktailsproject.ui

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cocktailsproject.data.ICocktailsRepository
import com.example.cocktailsproject.data.local.ILocalDataSource
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.toCocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(private val repository: ICocktailsRepository) :ViewModel() {

    private var _recentCocktails: LiveData<List<Cocktail>?> = repository.getRecentCocktails().asLiveData()
    val recentCocktails:LiveData<List<Cocktail>?> get() = _recentCocktails


    private var _searchedCocktail: MutableLiveData<Cocktail?> = MutableLiveData()
    val searchedCocktail:LiveData<Cocktail?> get() = _searchedCocktail





   private fun addToRecentCocktails(cocktail: Cocktail){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addToRecentCocktails(cocktail)
                Log.d(TAG, "HomeViewModel, addToRecentCocktails(cocktail:$cocktail)")
            }catch (e:Exception){
                Log.d(TAG, "HomeViewModel, addToRecentCocktails(cocktail:$cocktail), e:$e")
            }
        }
    }
    fun searchCocktailByName(query:String){
        viewModelScope.launch {


            try {
                _searchedCocktail.value =  repository.searchCocktailByName(query).toCocktail()
                addToRecentCocktails(searchedCocktail.value!!)
            }catch (e:Exception){
                Log.d(TAG, "HomeViewModel, searchCocktailByName(query: $query), e:$e")
            }

        }
    }

    fun checkIfInRecentCocktails(id: Int):Boolean{
        if (!_recentCocktails.value.isNullOrEmpty()){
            for(i in _recentCocktails.value!!){
                if (i.idDrink == id){
                    return true
                }
            }
        }
        return false
    }

    fun resetSearchedCocktail(){

        _searchedCocktail.value = null
    }










    class Factory @Inject constructor(
        myViewModelProvider: Provider<HomeViewModel>
    ) : ViewModelProvider.Factory {

        private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
            HomeViewModel::class.java to myViewModelProvider
        )

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (providers[modelClass]!!.get()) as T
        }
    }

}
