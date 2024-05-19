package com.example.cocktailsproject.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.FragmentInfoBinding
import com.example.cocktailsproject.models.Drinks
import kotlin.math.abs
import kotlin.reflect.KProperty1
import kotlin.reflect.full.valueParameters

private const val MENU_INVISIBLE = -1
private const val MENU_VISIBLE = 1

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

            viewModel.data.observe(viewLifecycleOwner, Observer {
                //change visibility of ui and menu depend on result
                changeVisibilityOfUi(binding, it)

            })
        binding.refreshLayout.setOnRefreshListener {
            viewModel.reloadRemoteData()
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
        } else if (id == MENU_INVISIBLE){
            addToFavourites.isVisible = false
            inFavourites.isVisible = false
        }else if (id == MENU_VISIBLE){
            addToFavourites.isVisible = true
        }

        return false

    }

    private fun changeVisibilityOfUi(binding: FragmentInfoBinding, uiState: UIState){
        binding.apply {
            when(uiState) {
                is UIState.Success -> {
                    val drink = uiState.data.drinks[0]
                    drink.apply {

                        layoutInfo.isVisible = true
                        layoutLoading.isVisible = false
                        layoutError.isVisible = false

                        textviewCocktailName.text = strDrink
                        textviewCocktailCategory.text = strCategory
                        textviewCocktailType.text = strAlcoholic
                        textviewGlassType.text = strGlass
                        textviewIngredientsCocktail.text = getIngredients(drink)

                        imageviewInfo.load(strDrinkThumb)
                        imageviewInfo.isVisible = true
                        gifViewInfo.isVisible = false

                        setVisibilityOfMenuItems(MENU_VISIBLE)
                        refreshLayout.isRefreshing = false
                        appBarLayout.addOnOffsetChangedListener{
                            appBarLayout, verticalOffset ->
                            if (abs(verticalOffset) >= appBarLayout.totalScrollRange){
                                refreshLayout.isEnabled = false
                            }else refreshLayout.isEnabled = verticalOffset == 0

                        }
                    }
                }

                is UIState.Loading -> {
                    layoutLoading.isVisible = true
                    layoutInfo.isVisible = false
                    setVisibilityOfMenuItems(MENU_INVISIBLE)
                    refreshLayout.isRefreshing = false


                }
                is UIState.Error ->{
                    layoutLoading.isVisible = false
                    layoutError.isVisible  = true
                    layoutInfo.isVisible = false
                    imageviewInfo.load(R.drawable.drink_loading_error)
                    imageviewInfo.isVisible = true
                    gifViewInfo.isVisible = false
                    setVisibilityOfMenuItems(MENU_INVISIBLE)
                    refreshLayout.isRefreshing = false

                    btnErrorTryAgain.setOnClickListener {
                        refreshLayout.isRefreshing = true
                        viewModel.reloadRemoteData()


                    }
                }


            }
        }
    }


    //transform drinks to string and filtered it
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

