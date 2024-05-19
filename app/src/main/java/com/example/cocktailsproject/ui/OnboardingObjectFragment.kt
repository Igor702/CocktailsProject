package com.example.cocktailsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.cocktailsproject.R
import com.example.cocktailsproject.databinding.FragmentOnboardingObjectBinding
import com.example.cocktailsproject.models.Onboarding

const val ARG_OBJECT = "object"


class OnboardingObjectFragment(private val position: Int):Fragment() {

    private var _binding:FragmentOnboardingObjectBinding? = null
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding?.onboardingTitle?.text = getInt(ARG_OBJECT).toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object{
        val listOfOnboardings = listOf<Onboarding>(
            Onboarding(R.drawable.onboarding_favourites, R.string.onboarding_favourite),
            Onboarding(R.drawable.onboarding_swipe, R.string.onboarding_swipe),
            Onboarding(R.drawable.onboarding_error, R.string.onboarding_error)
        )
    }

}