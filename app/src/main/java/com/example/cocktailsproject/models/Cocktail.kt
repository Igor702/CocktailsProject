package com.example.cocktailsproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recent_cocktails")
data class Cocktail(
   @PrimaryKey var idDrink: Int? = null,
   @ColumnInfo(name = "str_drink") var strDrink: String? = null,
   @ColumnInfo(name = "str_drink_alternate") var strDrinkAlternate: String? = null,
   @ColumnInfo(name = "str_tags") var strTags: String? = null,
   @ColumnInfo(name = "str_video") var strVideo: String? = null,
   @ColumnInfo(name = "str_category") var strCategory: String? = null,
   @ColumnInfo(name = "str_iba") var strIBA: String? = null,
   @ColumnInfo(name = "str_alcoholic") var strAlcoholic: String? = null,
   @ColumnInfo(name = "str_glass") var strGlass: String? = null,
   @ColumnInfo(name = "str_instructions") var strInstructions: String? = null,
   @ColumnInfo(name = "str_instructions_es") var strInstructionsES: String? = null,
   @ColumnInfo(name = "str_instructions_de") var strInstructionsDE: String? = null,
   @ColumnInfo(name = "str_instructions_fr") var strInstructionsFR: String? = null,
   @ColumnInfo(name = "str_instructions_it") var strInstructionsIT: String? = null,
   @ColumnInfo(name = "str_instructions_zhhans") var strInstructionsZHHANS: String? = null,
   @ColumnInfo(name = "str_instructions_zhhant") var strInstructionsZHHANT: String? = null,
   @ColumnInfo(name = "str_drink_thumb") var strDrinkThumb: String? = null,
   @ColumnInfo(name = "str_ingredients") var strIngredients: String? = null,
   @ColumnInfo(name = "str_measure1") var strMeasure1: String? = null,
   @ColumnInfo(name = "str_measure2") var strMeasure2: String? = null,
   @ColumnInfo(name = "str_measure3") var strMeasure3: String? = null,
   @ColumnInfo(name = "str_measure4") var strMeasure4: String? = null,
   @ColumnInfo(name = "str_measure5") var strMeasure5: String? = null,
   @ColumnInfo(name = "str_measure6") var strMeasure6: String? = null,
   @ColumnInfo(name = "str_measure7") var strMeasure7: String? = null,
   @ColumnInfo(name = "str_measure8") var strMeasure8: String? = null,
   @ColumnInfo(name = "str_measure9") var strMeasure9: String? = null,
   @ColumnInfo(name = "str_measure10") var strMeasure10: String? = null,
   @ColumnInfo(name = "str_measure11") var strMeasure11: String? = null,
   @ColumnInfo(name = "str_measure12") var strMeasure12: String? = null,
   @ColumnInfo(name = "str_measure13") var strMeasure13: String? = null,
   @ColumnInfo(name = "str_measure14") var strMeasure14: String? = null,
   @ColumnInfo(name = "str_measure15") var strMeasure15: String? = null,
   @ColumnInfo(name = "str_image_source") var strImageSource: String? = null,
   @ColumnInfo(name = "str_image_attribution") var strImageAttribution: String? = null,
   @ColumnInfo(name = "str_creative_commons_confirmed") var strCreativeCommonsConfirmed: String? = null,
   @ColumnInfo(name = "date_modified") var dateModified: String? = null
)