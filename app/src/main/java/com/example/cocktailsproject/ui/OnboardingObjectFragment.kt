package com.example.cocktailsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.cocktailsproject.PagingData.listOfOnboardings
import com.example.cocktailsproject.databinding.FragmentOnboardingObjectBinding


class OnboardingObjectFragment(private val position: Int) : Fragment() {

    private var _binding: FragmentOnboardingObjectBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingObjectBinding.inflate(inflater, container, false)
        val onboarding = listOfOnboardings[position]
        binding?.apply {
            onboardingImage.load(onboarding.drawableInt)
            onboardingTitle.text = getString(onboarding.titleInt)
        }
        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}