package com.devsurfer.domain.useCase.auth

import com.devsurfer.domain.model.AuthToken
import com.devsurfer.domain.repository.AuthRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: AuthRepository
){
    operator fun invoke(code: String): Flow<ResourceState<AuthToken>> = flow{
        emit(ResourceState.Loading())
        emit(repository.getAccessToken(code))
    }
}