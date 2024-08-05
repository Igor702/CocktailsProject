package com.example.cocktailsproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cocktailsproject.CocktailsApplication
import com.example.cocktailsproject.databinding.FragmentHomeBinding

class HomeFragment:Fragment(){
    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: RecentCocktailsAdapter
    private val viewModel: HomeViewModel by viewModels {
        (this.requireActivity().application as CocktailsApplication).daggerComponent.homeViewModelFactory()
    }
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        viewModel.recentCocktails.observe(viewLifecycleOwner){
            Log.d(TAG, "HomeFragment, recentCocktails: $it")

            adapter = RecentCocktailsAdapter()
            adapter.submitList(it)
            binding.recyclerViewLastCocktails.adapter = adapter
        }


        searchCocktailByName()










        return binding.root
    }


    //todo:rename
    private fun searchCocktailByName(){
        binding.searchCocktail.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit p0 $p0")



                if (p0 != null) {
                    viewModel.searchCocktailByName(p0)

                    viewModel.searchedCocktail.observe(viewLifecycleOwner){
                        Log.d(TAG, "HomeFragment, searchedCocktail: $it")
                        if (it != null){
                            val id = it.idDrink!!.toInt()
                            findNavController().navigate(
                                HomeFragmentDirections
                                    .actionHomeFragmentToCocktailFragment(id, viewModel.checkIfInRecentCocktails(id)))
                            viewModel.resetSearchedCocktail()

                        }

                    }


                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextChange p0 $p0")
                return true
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}