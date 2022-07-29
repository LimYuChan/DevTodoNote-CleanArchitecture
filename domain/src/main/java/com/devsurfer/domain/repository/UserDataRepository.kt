package com.devsurfer.domain.repository

import com.devsurfer.domain.model.User
import com.devsurfer.domain.state.ResourceState

interface UserDataRepository {
    suspend fun getUserData(): ResourceState<User>
}