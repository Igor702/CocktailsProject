package com.example.cocktailsproject.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.CardRecentCocktailBinding
import com.example.cocktailsproject.models.Cocktail

class RecentCocktailsAdapter: ListAdapter<Cocktail,RecentCocktailsAdapter.ViewHolder>(DiffCallback) {


    object DiffCallback: DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem.strDrinkThumb == newItem.strDrinkThumb
        }

    }
    class ViewHolder(private var binding: CardRecentCocktailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cocktail: Cocktail){
            binding.imageViewCardRecent.load(cocktail.strDrinkThumb)
            binding.textViewCardCocktailTitle.text = cocktail.strDrink

            binding.cardRecentCocktail.setOnClickListener {
                Log.d(TAG, "ViewHolder bind click")
                it.findNavController().navigate(
                    HomeFragmentDirections
                        .actionHomeFragmentToCocktailFragment(cocktail.idDrink!!, true))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapter = LayoutInflater.from(parent.context)
        return ViewHolder(
            CardRecentCocktailBinding.inflate(adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cocktail = getItem(position))
    }




}