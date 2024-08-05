package com.example.cocktailsproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.cocktailsproject.CocktailsApplication
import com.example.cocktailsproject.databinding.FragmentCocktailBinding


class CocktailFragment: Fragment(){
    private var _binding: FragmentCocktailBinding? = null

    private val args:CocktailFragmentArgs by navArgs<CocktailFragmentArgs>()

    private val viewModel: SingleCocktailViewModel by viewModels {
        (requireActivity().application as CocktailsApplication).daggerComponent.cocktailViewModelFactory()
    }


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailBinding.inflate(inflater, container, false)


        viewModel.cocktail.observe(this.viewLifecycleOwner){
            Log.d(TAG, "CocktailFragment, it: $it")
            binding.apply {

                if (it != null) {

                    it.apply {
                        textviewCocktailName.text = it.toString()
                        textviewCocktailName.text = strDrink
                        textviewCocktailCategory.text = strCategory
                        textviewCocktailType.text = strAlcoholic
                        textviewGlassType.text = strGlass
                        textviewIngredientsCocktail.text = strIngredients

                        imageviewCocktail.load(strDrinkThumb)
                    }





                    layoutCocktail.isVisible = true
                    layoutLoading.isVisible = false

                } else {
                    layoutLoading.isVisible = true
                    layoutCocktail.isVisible = false

                }
            }
        }
        viewModel.getCocktail(args).toString()











        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}