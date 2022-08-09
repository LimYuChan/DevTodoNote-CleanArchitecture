package com.devsurfer.data.repository.userData.dataSource

import com.devsurfer.data.di.RetrofitModule
import com.devsurfer.data.mapper.userData.toModel
import com.devsurfer.data.service.userData.UserDataService
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import javax.inject.Inject

class UserDataRemoteDataSource @Inject constructor(
    @RetrofitModule.Api private val service: UserDataService
){
    suspend fun getUserData(): User = service.getUserData().toModel()

    suspend fun getUserRepositories(): List<UserRepository> = service.getUserRepos().map { it.toModel() }

    suspend fun getRepositoryEvents(owner: String, repo: String): List<RepositoryEvent> =
        service.getUserRepositoryEvents(owner, repo).map { it.toModel() }
}