package com.example.cocktailsproject

import com.google.android.material.appbar.AppBarLayout

object AppBarCollapsed{
    private var isCollapsed: Boolean = false

    fun getRefreshing():Boolean{
        return isCollapsed
    }

    fun setRefreshing(isNotCollapsed:Boolean){
        this.isCollapsed = isNotCollapsed
    }

}

var AppBarLayout.isRefreshingAvailable: Boolean
    set(value) = AppBarCollapsed.setRefreshing(value)
    get() = AppBarCollapsed.getRefreshing()
