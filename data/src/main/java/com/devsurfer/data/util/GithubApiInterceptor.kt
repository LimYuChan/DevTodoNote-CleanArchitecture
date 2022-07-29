package com.devsurfer.data.util

import com.devsurfer.data.manager.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GithubApiInterceptor @Inject constructor(
    preferenceManager: PreferenceManager
): Interceptor {

    private val accessToken: String
    init {
        accessToken = try{
            preferenceManager.getAccessToken()
        }catch (e: UnsupportedOperationException){
            ""
        }
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