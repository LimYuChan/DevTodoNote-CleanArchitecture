package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.repository.userData.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepositoryEventsUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(owner: String, repo: String): Flow<List<RepositoryEvent>> = flow {
        emit(repository.getUserRepositoryEvents(owner, repo))
    }
}