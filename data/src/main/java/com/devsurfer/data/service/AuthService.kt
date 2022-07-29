package com.devsurfer.data.service

import com.devsurfer.data.BuildConfig
import com.devsurfer.data.model.AuthTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String = BuildConfig.GITHUB_CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.GITHUB_SECRET_KEY,
        @Field("code") code: String
    ): Response<AuthTokenResponse>
}