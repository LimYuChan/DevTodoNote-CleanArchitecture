package com.devsurfer.domain.repository.userData

import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.state.ResourceState

interface UserDataRepository {
    suspend fun getUserData(): User
    suspend fun getUserRepositories(): List<UserRepository>
    suspend fun getUserRepositoryEvents(owner: String, repo: String): List<RepositoryEvent>
}