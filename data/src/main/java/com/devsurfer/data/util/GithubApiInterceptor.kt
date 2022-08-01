package com.devsurfer.data.util

import android.util.Log
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.domain.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GithubApiInterceptor @Inject constructor(
    val preferenceManager: PreferenceManager
): Interceptor {

    private var accessToken: String

    init {
        accessToken = try{
            preferenceManager.getAccessToken()
        }catch (e: UnsupportedOperationException){
            ""
        }
        Log.d(TAG, ": $accessToken")
    }
    override fun intercept(chain: Interceptor.Chain): Response{
        accessToken = try{
            preferenceManager.getAccessToken()
        }catch (e: UnsupportedOperationException){
            ""
        }
        return if(accessToken.isBlank()){
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


    companion object{
        private const val TAG = "GithubApiInterceptor"
    }
}