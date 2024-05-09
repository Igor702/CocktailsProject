package com.example.cocktailsproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktailsproject.data.CocktailsRepository
import com.example.cocktailsproject.models.Drinks
import com.example.cocktailsproject.models.ModelRequest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

const val TAG = "TAG"

sealed interface UIState {
    data class Success(val data: ModelRequest):UIState
    object Loading:UIState
    object Error: UIState

}
class CocktailsViewModel(private val repo: CocktailsRepository) : ViewModel() {

    private var _data: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)
    val data: LiveData<UIState> get() = _data


    init {
        loadRemoteData()
    }

    fun getLocalData(): String {
        try {
//            _data = repo.getLocalData()
        } catch (e: Exception) {
            Log.d(TAG, "e:$e")
        }

        return data.value.toString()
    }

    private fun loadRemoteData() {

        viewModelScope.launch {
            try {
                _data.value = UIState.Success(repo.getRemoteData())

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


    fun jsonGame(): String {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<ModelRequest> = moshi.adapter(ModelRequest::class.java)

        val request = ModelRequest(
            listOf(
                Drinks(
                    "659494",
                    "name of drink",
                    "mstr",
                    "tag not null",
                    "video not null",
                    "category not null",
                    "IBA not null",
                    "alc not null",
                    "glass not null",
                    "instr not null",
                    "es not null",
                    "de not null",
                    "fr not null",
                    "it not null",
                    "zh not null",
                    "zhh not null",
                    "thumb not null",
                    "i1 not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null",
                    "not null"
                )
            )
        )
        val json: String = adapter.toJson(request)
        Log.d(TAG, "JsonGame, json: $json")

        return json
    }


}