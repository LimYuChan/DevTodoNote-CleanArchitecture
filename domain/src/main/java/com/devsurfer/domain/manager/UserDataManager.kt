package com.devsurfer.domain.manager

import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.userData.GetUserDataUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserDataManager @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) {
    private var user: User? = null

    fun getUser(): User? = user ?: getUserData()

    fun getUserWithUpdate(): User?{
        user = getUserData()
        return user
    }

    private fun getUserData(): User? = runBlocking {
        var resultUserData: User? = null
        val getUserDataResult = async {
            getUserDataUseCase()
        }
        resultUserData = getUserDataResult.await()
        return@runBlocking resultUserData
    }
}