package com.devsurfer.domain.repository.auth

import com.devsurfer.domain.model.auth.AuthToken
import com.devsurfer.domain.state.ResourceState

interface AuthRepository {
    suspend fun getAccessToken(code: String): ResourceState<AuthToken>
}