package com.example.cocktailsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.cocktailsproject.AppBarCollapsed
import com.example.cocktailsproject.R
import com.example.cocktailsproject.ServiceLocator
import com.example.cocktailsproject.databinding.FragmentInfoBinding
import kotlin.math.abs

private const val MENU_INVISIBLE = -1
private const val MENU_VISIBLE = 1

class InfoFragment : Fragment() {


    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailsViewModel by viewModels {
        CocktailsViewModel.CocktailsViewModelFactory(ServiceLocator.provideCocktailsRepository())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        viewModel.loadRemoteData()

        //updating UI-elements - refreshing direct in fragment
        viewModel.data.observe(viewLifecycleOwner) {
            //change visibility of ui and menu depend on result
            changeVisibilityOfUi(binding, it)

        }

        //reloading data of UI - handle in viewModel
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
        val inFavourites: MenuItem = menu.findItem(R.id.remove_from_favourites)




        if (id == R.id.add_to_favourites) {
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            addToFavourites.isVisible = false
            inFavourites.isVisible = true

            return true
        } else if (id == R.id.remove_from_favourites) {

            Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show()
            addToFavourites.isVisible = true
            inFavourites.isVisible = false
            return true
        } else if (id == MENU_INVISIBLE) {
            addToFavourites.isVisible = false
            inFavourites.isVisible = false
        } else if (id == MENU_VISIBLE) {
            addToFavourites.isVisible = true
            inFavourites.isVisible = false

        }

        return false

    }

    private fun changeVisibilityOfUi(binding: FragmentInfoBinding, uiState: UIState) {
        binding.apply {
            when (uiState) {
                is UIState.Success -> {
                    val drink = uiState.data
                    drink.apply {

                        layoutInfo.isVisible = true
                        layoutLoading.isVisible = false
                        layoutError.isVisible = false

                        textviewCocktailName.text = strDrink
                        textviewCocktailCategory.text = strCategory
                        textviewCocktailType.text = strAlcoholic
                        textviewGlassType.text = strGlass
                        textviewIngredientsCocktail.text = strIngredients

                        imageviewInfo.load(strDrinkThumb)
                        imageviewInfo.tag = null
                        imageviewInfo.isVisible = true
                        gifViewInfo.isVisible = false

                        setVisibilityOfMenuItems(MENU_VISIBLE)
                        refreshLayout.isRefreshing = false
                        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                            if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                                refreshLayout.isEnabled = false
                                AppBarCollapsed.setRefreshing(refreshLayout.isEnabled)

                            } else {
                                refreshLayout.isEnabled = verticalOffset == 0
                                AppBarCollapsed.setRefreshing(refreshLayout.isEnabled)
                            }


                        }
                        appBarLayout.totalScrollRange
                    }
                }

                is UIState.Loading -> {
                    layoutLoading.isVisible = true
                    layoutInfo.isVisible = false
                    setVisibilityOfMenuItems(MENU_INVISIBLE)
                    refreshLayout.isRefreshing = false


                }

                is UIState.Error -> {
                    layoutLoading.isVisible = false
                    layoutError.isVisible = true
                    layoutInfo.isVisible = false
                    imageviewInfo.load(R.drawable.drink_loading_error)
                    imageviewInfo.tag = R.drawable.drink_loading_error
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


