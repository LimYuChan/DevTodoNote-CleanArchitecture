package com.devsurfer.data.utils

import android.content.Context
import com.devsurfer.data.extension.Preference.get
import com.devsurfer.data.manager.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GithubApiInterceptor @Inject constructor(
    preferenceManager: PreferenceManager
): Interceptor {

    private val accessToken: String
    init {
        accessToken = preferenceManager.getAccessToken()
    }
    override fun intercept(chain: Interceptor.Chain): Response =
        if(accessToken.isBlank()){
            val request = chain.request()
            chain.proceed(request)
        }else{
            val request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "token $accessToken")
                .build()
            chain.proceed(request)
        }
}