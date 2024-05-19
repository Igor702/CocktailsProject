package com.example.cocktailsproject.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class CocktailsOnboardingFragment:Fragment(){

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding
    private lateinit var sharedPreferences:SharedPreferences

    private lateinit var pager:ViewPager2
    private lateinit var adapter: CocktailsOnboardingAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        sharedPreferences = this.requireActivity().getSharedPreferences(getString(R.string.onboarding_key_name), Context.MODE_PRIVATE)


        val onboardingWasShowed:Boolean = sharedPreferences.getBoolean(getString(R.string.onboarding_key_value), false)
        Log.d(TAG, "onboarding check: $onboardingWasShowed")
        if (onboardingWasShowed){
            findNavController().navigate(R.id.action_cocktailsOnboardingFragment_to_infoFragment)
        }

        binding?.btnOnboardingGo?.setOnClickListener {
            findNavController().navigate(R.id.action_cocktailsOnboardingFragment_to_infoFragment)
            sharedPreferences.edit().putBoolean(getString(R.string.onboarding_key_value), true).apply()

        }






        adapter = CocktailsOnboardingAdapter(this)
            pager = binding!!.pagerOnboardings
         pager.adapter = adapter
            TabLayoutMediator(binding!!.onboardingTabLayout, pager){ tab, position -> }.attach()


        return binding?.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null


    }

}