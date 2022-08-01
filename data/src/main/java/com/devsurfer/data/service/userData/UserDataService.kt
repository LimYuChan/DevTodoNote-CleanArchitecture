package com.devsurfer.data.service.userData

import com.devsurfer.data.model.userData.UserRepositoryResponse
import com.devsurfer.data.model.userData.UserResponse
import com.devsurfer.data.model.userData.repositoryEvent.RepositoryEventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserDataService {
    @GET("/user")
    suspend fun getUserData(): Response<UserResponse>

    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("visibility") visibility: String = "all",
        @Query("sort") sort: String = "updated"
    ): Response<List<UserRepositoryResponse>>

    @GET("/repos/{owner}/{repo}/events")
    suspend fun getUserRepositoryEvents(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<RepositoryEventResponse>>
}