package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.repository.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
){
    suspend operator fun invoke(): ResourceState<User> = repository.getUserData()
}