package com.example.cocktailsproject

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.cocktailsproject.ui.TAG
import com.google.android.material.appbar.AppBarLayout
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

        Log.d(TAG, "item.drawable: ${item.tag}, imageView.drawable: ${imageView.tag}")


        return   item.tag == imageView.tag
    }

}

fun isSwipeToRefreshAvailable()  = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Is swipeToRefresh available")
    }

    override fun matchesSafely(itemView: View?): Boolean {

        val item = itemView as AppBarLayout

        return item.isRefreshingAvailable
    }

}


