package com.example.cocktailsproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cocktailsproject.data.ICocktailsRepository
import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.toCocktail
import kotlinx.coroutines.launch

const val TAG = "TAG"

sealed interface UIState {
    data class Success(val data: Cocktail) : UIState
    object Loading : UIState
    object Error : UIState

}

class CocktailsViewModel(private val repo: ICocktailsRepository) : ViewModel() {

    private var _data: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)
    val data: LiveData<UIState> get() = _data


    fun loadRemoteData() {

        viewModelScope.launch {
            try {
                _data.value = UIState.Success(repo.getRemoteData().toCocktail())

            } catch (e: Exception) {
                _data.value = UIState.Error
            }


        }


    }

    fun reloadRemoteData() {
        loadRemoteData()


    }

    @Suppress("UNCHECKED_CAST")
    class CocktailsViewModelFactory(
        private val repo: ICocktailsRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CocktailsViewModel(repo) as T
        }


    }
}





