package com.example.cocktailsproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktailsproject.data.CocktailsRepository
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.toCocktail
import kotlinx.coroutines.launch

const val TAG = "TAG"

sealed interface UIState {
    data class Success(val data: Cocktail):UIState
    object Loading:UIState
    object Error: UIState

}
class CocktailsViewModel(private val repo: CocktailsRepository) : ViewModel() {

    private var _data: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)
    val data: LiveData<UIState> get() = _data


    init {
        loadRemoteData()
    }



    private fun loadRemoteData() {

        viewModelScope.launch {
            try {
                _data.value = UIState.Success(repo.getRemoteData().toCocktail())

            } catch (e: Exception) {
                Log.d(TAG, "vm, loadRemoteData, exception: $e")
                _data.value = UIState.Error
            }


        }


    }

    fun reloadRemoteData() {
        loadRemoteData()
        val logInfo = _data.value.toString()
        Log.d(TAG, "vm, getRemoteData, logInfo: $logInfo")


    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
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