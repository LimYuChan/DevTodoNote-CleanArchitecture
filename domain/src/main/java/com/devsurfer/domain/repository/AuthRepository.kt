package com.devsurfer.domain.repository

import com.devsurfer.domain.model.AuthToken
import com.devsurfer.domain.state.ResourceState

interface AuthRepository {
    suspend fun getAccessToken(code: String): ResourceState<AuthToken>
}