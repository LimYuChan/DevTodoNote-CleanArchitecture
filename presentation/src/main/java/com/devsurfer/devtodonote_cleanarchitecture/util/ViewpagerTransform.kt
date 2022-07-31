package com.devsurfer.devtodonote_cleanarchitecture.util

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ViewpagerTransform: ViewPager2.PageTransformer {
    companion object{
        const val MIN_SCALE = .6f
    }
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    alpha = 1 - position
                    translationX = pageWidth *- position
                    translationZ = -1f

                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}