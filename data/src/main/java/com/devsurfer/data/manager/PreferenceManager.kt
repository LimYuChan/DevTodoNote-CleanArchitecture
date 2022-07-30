package com.devsurfer.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.devsurfer.data.extension.Preference.get
import com.devsurfer.data.extension.Preference.set
import com.devsurfer.domain.util.Constants
import javax.inject.Inject

class PreferenceManager @Inject constructor(
    context: Context
) {
    private val preference: SharedPreferences

    init {
        preference = context.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun getAccessToken(): String = preference[Constants.PREFERENCE_KEY_ACCESS_TOKEN, ""]

    fun updateAccessToken(accessToken: String): Boolean = preference.set(Constants.PREFERENCE_KEY_ACCESS_TOKEN, accessToken)

    fun clear():Boolean = preference.edit().clear().commit()
}