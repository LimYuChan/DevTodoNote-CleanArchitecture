package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.repository.userData.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<List<UserRepository>> = flow {
        emit(repository.getUserRepositories())
    }
}