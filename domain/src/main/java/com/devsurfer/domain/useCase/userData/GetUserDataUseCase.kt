package com.devsurfer.domain.useCase.userData

import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.repository.userData.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
){
    suspend operator fun invoke(): User = repository.getUserData()
}