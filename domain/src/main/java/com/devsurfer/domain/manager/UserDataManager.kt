package com.devsurfer.domain.manager

import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.useCase.userData.GetUserDataUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserDataManager @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) {
    private var user: User? = null

    suspend fun getUser(): User = user ?: getUserData()

    suspend fun getUserWithUpdate(): User?{
        user = getUserData()
        return user
    }

    private suspend fun getUserData(): User = coroutineScope {
        var resultUserData: User? = null
        val getUserDataResult = async {
            getUserDataUseCase()
        }
        getUserDataResult.await()
    }
}