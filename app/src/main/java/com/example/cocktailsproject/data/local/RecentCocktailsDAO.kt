package com.example.cocktailsproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailsproject.models.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentCocktailsDAO {

    @Query("SELECT * FROM recent_cocktails")
    fun getRecentCocktails(): Flow<List<Cocktail>?>

    @Insert(entity = Cocktail::class,onConflict = OnConflictStrategy.REPLACE)
    fun addToRecentCocktails(cocktail: Cocktail)
    @Query("SELECT * FROM recent_cocktails WHERE  idDrink =:id")
  suspend fun getCocktailById(id: Int?): Cocktail


}