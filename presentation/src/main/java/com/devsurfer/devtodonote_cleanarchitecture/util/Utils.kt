package com.devsurfer.devtodonote_cleanarchitecture.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.OpenUserProfileBottomSheet

object Utils {
    fun openProfileBottomSheet(fragmentManager: FragmentManager, userProfileUrl: String?){
        userProfileUrl?.let {
            val bottomSheet = OpenUserProfileBottomSheet(userProfileUrl)
            bottomSheet.show(fragmentManager, "userProfileSheet")
        }
    }
    fun getScreenWidth(activity: Activity): Int {
        val windowManager = activity.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        }else{
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }
}