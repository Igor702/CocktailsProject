package com.example.cocktailsproject

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
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

object PreferenceProvider {

    private var isUnderTesting = false


    private var sharedPreferences: SharedPreferences? = null


    fun getBoolean(context: Context): Boolean {
        if (isUnderTesting) {

            return true
        }

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.onboarding_key_name),
                Context.MODE_PRIVATE
            )

        }


        return sharedPreferences!!.getBoolean(
            context.getString(R.string.onboarding_key_value),
            false
        )


    }

    fun putBoolean(value: Boolean, context: Context) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.onboarding_key_name),
                Context.MODE_PRIVATE
            )

        }


        sharedPreferences?.edit()
            ?.putBoolean(context.getString(R.string.onboarding_key_value), value)?.apply()
    }

    @VisibleForTesting
    fun setTestingMode(isTesting: Boolean) {
        isUnderTesting = isTesting
    }


}