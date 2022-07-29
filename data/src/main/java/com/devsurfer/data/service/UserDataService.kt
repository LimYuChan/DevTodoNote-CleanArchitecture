package com.devsurfer.data.service

import com.devsurfer.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserDataService {
    @GET("/user")
    suspend fun getUserData(): Response<UserResponse>
}