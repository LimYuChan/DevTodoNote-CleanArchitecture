package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.User
import com.devsurfer.domain.repository.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
){
    suspend operator fun invoke(): ResourceState<User> = repository.getUserData()
}