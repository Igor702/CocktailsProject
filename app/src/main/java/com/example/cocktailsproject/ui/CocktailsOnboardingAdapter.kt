package com.example.cocktailsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class CocktailsOnboardingAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = OnboardingObjectFragment(position)
//        fragment.arguments = Bundle().apply {
//            putInt(ARG_OBJECT, position+1)
//        }

        return fragment
    }

}