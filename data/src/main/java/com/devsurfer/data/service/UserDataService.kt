package com.devsurfer.data.service

import com.devsurfer.data.model.UserRepositoryResponse
import com.devsurfer.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDataService {
    @GET("/user")
    suspend fun getUserData(): Response<UserResponse>

    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("visibility") visibility: String = "all",
        @Query("sort") sort: String = "updated"
    ): Response<List<UserRepositoryResponse>>
}