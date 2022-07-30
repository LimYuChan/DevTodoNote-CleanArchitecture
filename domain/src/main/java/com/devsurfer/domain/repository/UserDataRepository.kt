package com.devsurfer.domain.repository

import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.state.ResourceState

interface UserDataRepository {
    suspend fun getUserData(): ResourceState<User>
    suspend fun getUserRepositories(): ResourceState<List<UserRepository>>
}