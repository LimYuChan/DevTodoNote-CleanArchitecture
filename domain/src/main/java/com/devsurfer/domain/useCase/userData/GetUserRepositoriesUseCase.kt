package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.repository.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<ResourceState<List<UserRepository>>> = flow {
        emit(ResourceState.Loading())
        emit(repository.getUserRepositories())
    }
}