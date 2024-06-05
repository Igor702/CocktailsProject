package com.example.cocktailsproject

import com.example.cocktailsproject.models.Onboarding
import com.google.android.material.appbar.AppBarLayout

object AppBarCollapsed {
    private var isCollapsed: Boolean = false

    fun getRefreshing(): Boolean {
        return isCollapsed
    }

    fun setRefreshing(isNotCollapsed: Boolean) {
        this.isCollapsed = isNotCollapsed
    }

}

var AppBarLayout.isRefreshingAvailable: Boolean
    set(value) = AppBarCollapsed.setRefreshing(value)
    get() = AppBarCollapsed.getRefreshing()


object PagingData {
    val listOfOnboardings = listOf<Onboarding>(
        Onboarding(R.drawable.onboarding_favourites, R.string.onboarding_favourite),
        Onboarding(R.drawable.onboarding_swipe, R.string.onboarding_swipe),
        Onboarding(R.drawable.onboarding_error, R.string.onboarding_error)
    )
}

