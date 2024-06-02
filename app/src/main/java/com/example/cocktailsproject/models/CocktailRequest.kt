package com.example.cocktailsproject.models


data class CocktailRequest(
    val drinks: List<DrinkRawData>
)

fun CocktailRequest.toCocktail(): Cocktail {

    this.drinks[0].apply {
        return Cocktail(
            idDrink = idDrink,
            strDrink = strDrink,
            strDrinkAlternate = strDrinkAlternate,
            strTags = strTags,
            strVideo = strVideo,
            strCategory = strCategory,
            strIBA = strIBA,
            strAlcoholic = strAlcoholic,
            strGlass = strGlass,
            strInstructions = strInstructions,
            strInstructionsES = strInstructionsES,
            strInstructionsDE = strInstructionsDE,
            strInstructionsFR = strInstructionsFR,
            strInstructionsIT = strInstructionsIT,
            strInstructionsZHHANS = strInstructionsZHHANS,
            strInstructionsZHHANT = strInstructionsZHHANT,
            strDrinkThumb = strDrinkThumb,
            strIngredients = getIngredients(this),
            strMeasure1 = strMeasure1,
            strMeasure2 = strMeasure2,
            strMeasure3 = strMeasure3,
            strMeasure4 = strMeasure4,
            strMeasure5 = strMeasure5,
            strMeasure6 = strMeasure6,
            strMeasure7 = strMeasure7,
            strMeasure8 = strMeasure8,
            strMeasure9 = strMeasure9,
            strMeasure10 = strMeasure10,
            strMeasure11 = strMeasure11,
            strMeasure12 = strMeasure12,
            strMeasure13 = strMeasure13,
            strMeasure14 = strMeasure14,
            strMeasure15 = strMeasure15,
            strImageSource = strImageSource,
            strImageAttribution = strImageAttribution,
            strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
            dateModified = dateModified
        )

    }
}

private fun getIngredients(drinkRawData: DrinkRawData): String? {
    var rawString: String?
    var resultString: String? = ""
    drinkRawData.apply {
        rawString = "$strIngredient1, $strIngredient2, $strIngredient3, $strIngredient4," +
                " $strIngredient5, $strIngredient6, $strIngredient7, $strIngredient7," +
                " $strIngredient8, $strIngredient9, $strIngredient10, $strIngredient11, " +
                "$strIngredient12, $strIngredient13, $strIngredient14, $strIngredient15"

    }
    val list = rawString?.split(", ")
    if (list != null) {
        for (i in list.indices) {
            if (list[i] != "null") {
                resultString += list[i]

            }
            if (i != 15 && list[i + 1] != "null") {
                resultString += ", \n"
            }
        }
    }
    return resultString
}



