package com.devsurfer.devtodonote_cleanarchitecture.util

import androidx.fragment.app.FragmentManager
import com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet.OpenUserProfileBottomSheet

object Utils {
    fun openProfileBottomSheet(fragmentManager: FragmentManager, userProfileUrl: String?){
        userProfileUrl?.let {
            val bottomSheet = OpenUserProfileBottomSheet(userProfileUrl)
            bottomSheet.show(fragmentManager, "userProfileSheet")
        }
    }
}