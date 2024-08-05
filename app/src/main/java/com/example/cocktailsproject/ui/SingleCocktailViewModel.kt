package com.example.cocktailsproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cocktailsproject.data.ICocktailsRepository
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.toCocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class SingleCocktailViewModel @Inject constructor(private val repository: ICocktailsRepository) : ViewModel() {




    private var _cocktail = MutableLiveData<Cocktail>()
    val cocktail: LiveData<Cocktail> get() = _cocktail

    fun getLocalCocktailById(id:Int){
        try {
//            _cocktail.value = repository.getLocalCocktailById(id)
            Log.d(TAG, "getCocktailById(id:$id) value _cocktail: ${_cocktail.value}")

        }catch (e:Exception){
            Log.d(TAG, "getCocktailById(id:$id) e:$e")
        }

    }

    fun getCocktail(args: CocktailFragmentArgs){
        val id = args.cocktailId
        Log.d(TAG,"getCocktail, args.id: ${args.cocktailId}, args.isLocalStored: ${args.isLocalStorred}")
        if (args.isLocalStorred){
            viewModelScope.launch {
                try {
                    _cocktail.value = repository.getLocalCocktailById(id)
                    Log.d(TAG, "getLocalCocktailById(id:$id) value _cocktail: ${_cocktail.value}")

                }catch (e:Exception){
                    Log.d(TAG, "getLocalCocktailById(id:$id) e:$e")
                }
            }

        }else{
            viewModelScope.launch {
                try {
                    _cocktail.value = repository.getRemoteCocktailById(id).toCocktail()
                    Log.d(TAG, "getRemoteCocktailById(id:$id) value _cocktail: ${_cocktail.value}")

                }catch (e:Exception){
                    Log.d(TAG, "SingleCocktailVM, getRemoteCocktailById(id:$id), exception: $e")
                }
            }
        }

    }

    fun getRemoteCocktailById(id: Int){
        viewModelScope.launch {
            try {
                _cocktail.value = repository.getRemoteCocktailById(id).toCocktail()

            }catch (e:Exception){
                Log.d(TAG, "SingleCocktailVM, getRemote, exception: $e")
            }
        }
    }



    class Factory @Inject constructor(
        myViewModelProvider: Provider<SingleCocktailViewModel>
    ) : ViewModelProvider.Factory {

        private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
            SingleCocktailViewModel::class.java to myViewModelProvider
        )

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (providers[modelClass]!!.get()) as T
        }
    }

}