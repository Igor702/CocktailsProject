package com.example.cocktailsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.FragmentInfoBinding

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
            toolbarInfo.title = textviewCocktailName.text


            //todo: make "load" status, because it can take some time for loading data
            viewModel.data.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    textviewCocktailName.text = it.toString()

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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}