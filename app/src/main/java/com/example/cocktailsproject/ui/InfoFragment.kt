package com.example.cocktailsproject.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.FragmentInfoBinding
import com.example.cocktailsproject.models.Drinks
import kotlin.reflect.KProperty1
import kotlin.reflect.full.valueParameters

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailsViewModel by viewModels {
        CocktailsViewModel.Factory
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)



        binding.apply {


            //todo: make "load" status, because it can take some time for loading data
            viewModel.data.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val drink = it.drinks.get(0)
                    drink.apply {



                        textviewCocktailName.text = strDrink
                        textviewCocktailCategory.text = strCategory
                        textviewCocktailType.text = strAlcoholic
                        textviewGlassType.text = strGlass
                        textviewIngredientsCocktail.text = getIngredients(drink)

                        imageviewInfo.load(strDrinkThumb)
                        toolbarInfo.title = textviewCocktailName.text









                    }


                } else {
                    textviewCocktailName.text = getString(R.string.something_get_wrong)

                }


            })


        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //inflate menu
            toolbarInfo.inflateMenu(R.menu.menu_info)

            //click listener for menu
            toolbarInfo.setOnMenuItemClickListener {

                setVisibilityOfMenuItems(it.itemId)

            }
        }



    }

    private fun setVisibilityOfMenuItems(id: Int): Boolean {
        val menu: Menu = binding.toolbarInfo.menu
        val addToFavourites: MenuItem = menu.findItem(R.id.add_to_favourites)
        val inFavourites: MenuItem = menu.findItem(R.id.in_favourites)

        if (id == R.id.add_to_favourites) {
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            addToFavourites.isVisible = false
            inFavourites.isVisible = true

            return true
        } else if (id == R.id.in_favourites) {

            Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show()
            addToFavourites.isVisible = true
            inFavourites.isVisible = false
            return true
        }

        return false

    }

    private fun getIngredients(drinks: Drinks): String? {
        var rawString: String? = ""
        var resultString:String? =""
        drinks.apply {
            rawString = "$strIngredient1, $strIngredient2, $strIngredient3, $strIngredient4," +
                    " $strIngredient5, $strIngredient6, $strIngredient7, $strIngredient7," +
                    " $strIngredient8, $strIngredient9, $strIngredient10, $strIngredient11, " +
                    "$strIngredient12, $strIngredient13, $strIngredient14, $strIngredient15"

        }
      val list =  rawString?.split(", ")
        if (list != null) {
            for (i in list.indices){
                if (list[i] != "null"){
                    resultString += list[i]

                }
                if (i!= 15 && list[i+1] != "null"){
                    resultString += ", \n"
                }
            }


        }
        return resultString
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}