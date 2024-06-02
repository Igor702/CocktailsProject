package com.example.cocktailsproject

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.viewpager2.widget.ViewPager2
import com.example.cocktailsproject.models.Onboarding
import com.example.cocktailsproject.ui.TAG
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("ImageView drawable the same as drawable with id ")
    }

    override fun matchesSafely(item: View?): Boolean {

        item as ImageView

        val imageView = ImageView(item.context)
        imageView.setImageDrawable(item.context?.getDrawable(id))
        imageView.tag = id


        return item.tag == imageView.tag
    }

}

fun isSwipeToRefreshAvailable() = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Is swipeToRefresh available")
    }

    override fun matchesSafely(itemView: View?): Boolean {

        val item = itemView as AppBarLayout

        return item.isRefreshingAvailable
    }

}

fun withPagerData(onboarding: Onboarding) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Given onboarding item:$onboarding matches with drawable from pager")
    }

    override fun matchesSafely(item: View?): Boolean {
        val pager = item as ViewPager2

        val currentItem = PagingData.listOfOnboardings[pager.currentItem]

        Log.d(TAG, "currentItem: $currentItem")


        return currentItem.drawableInt == onboarding.drawableInt && currentItem.titleInt == onboarding.titleInt


    }

}

fun withSelectedTabPosition(tabPosition: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("matches tabLayout.selectedTabPosition with given tabPosition: $tabPosition")
    }

    override fun matchesSafely(item: View?): Boolean {
        val tabLayout = item as TabLayout

        return tabLayout.selectedTabPosition == tabPosition

    }

}


