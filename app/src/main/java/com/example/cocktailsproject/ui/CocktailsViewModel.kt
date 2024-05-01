package com.example.cocktailsproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktailsproject.data.CocktailsRepository

const val TAG = "TAG"

class CocktailsViewModel(private val repo: CocktailsRepository):ViewModel() {

    private var _data = MutableLiveData(repo.getLocalData())
    val data: LiveData<String> get() = _data


    fun getData():String{
        try {
            _data.value = repo.getLocalData()
        }catch (e:Exception){
            Log.d(TAG, "e:$e")
        }

        return data.value.toString()
    }


    companion object{

        val Factory: ViewModelProvider.Factory = object:ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                val repo = CocktailsRepository()

                return CocktailsViewModel(repo) as T
            }
            }
    }






}