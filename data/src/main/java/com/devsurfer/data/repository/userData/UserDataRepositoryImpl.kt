package com.devsurfer.data.repository.userData

import com.devsurfer.data.repository.userData.dataSource.UserDataRemoteDataSource
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.repository.userData.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataSource: UserDataRemoteDataSource
): UserDataRepository {
    override suspend fun getUserData(): User =
        dataSource.getUserData()

    override suspend fun getUserRepositories(): List<UserRepository> =
        dataSource.getUserRepositories()

    override suspend fun getUserRepositoryEvents(
        owner: String,
        repo: String
    ): List<RepositoryEvent> = dataSource.getRepositoryEvents(owner, repo)
}