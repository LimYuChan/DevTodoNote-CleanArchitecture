package com.devsurfer.data.repository.auth

import com.devsurfer.data.repository.auth.dataSource.AuthRemoteDataSource
import com.devsurfer.domain.model.auth.AuthToken
import com.devsurfer.domain.repository.AuthRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSource
): AuthRepository{
    override suspend fun getAccessToken(code: String): ResourceState<AuthToken> =
        dataSource.getAccessToken(code)
}