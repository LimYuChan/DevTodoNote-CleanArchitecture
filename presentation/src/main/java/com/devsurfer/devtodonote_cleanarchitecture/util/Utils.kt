package com.devsurfer.devtodonote_cleanarchitecture.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.OpenWebViewBottomSheet

object Utils {
    fun openProfileBottomSheet(fragmentManager: FragmentManager, userProfileUrl: String?){
        userProfileUrl?.let {
            val bottomSheet = OpenWebViewBottomSheet(userProfileUrl)
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
    fun getBitmapFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(window, Rect(locationOfViewInWindow[0], locationOfViewInWindow[1], locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height), bitmap, { copyResult ->
                    if (copyResult == PixelCopy.SUCCESS) {
                        callback(bitmap)
                    }
                }, Handler())
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Log 를 Debug 에만 남기는 습관을 들여야 할 것 같습니다!
     */
    fun logDebug(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    fun logError(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
    }

    fun logInfo(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.i(tag, message)
    }
}