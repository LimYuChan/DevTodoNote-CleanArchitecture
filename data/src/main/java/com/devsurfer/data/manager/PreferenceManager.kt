package com.devsurfer.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.devsurfer.data.extension.Preference.get
import com.devsurfer.data.utils.Constants
import javax.inject.Inject

class PreferenceManager @Inject constructor(
    context: Context
) {
    private val preference: SharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getAccessToken(): String = preference[Constants.PREFERENCE_KEY_ACCESS_TOKEN, ""]
}